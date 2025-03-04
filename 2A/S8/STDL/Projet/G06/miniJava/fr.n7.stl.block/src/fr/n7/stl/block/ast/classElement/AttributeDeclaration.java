package fr.n7.stl.block.ast.classElement;

import fr.n7.stl.block.ast.expression.Expression;
import fr.n7.stl.block.ast.scope.Declaration;
import fr.n7.stl.block.ast.scope.HierarchicalScope;
import fr.n7.stl.block.ast.type.PartialType;
import fr.n7.stl.block.ast.type.Type;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.Register;
import fr.n7.stl.tam.ast.TAMFactory;
import fr.n7.stl.util.Logger;
import fr.n7.stl.util.Pair;

public class AttributeDeclaration implements ClassElement, Declaration {

    protected AccessRight accessRight;

    protected ElementState state;

    protected Type type;

    protected Pair<String, PartialType> identifiant;

    protected Expression valeur;

    Register register ;
    
    int offset;

    public AttributeDeclaration(AccessRight _accessRight, ElementState _state, Type _type,
            Pair<String, PartialType> _identifiant, Expression _valeur) {
        this.accessRight = _accessRight;
        this.state = _state;
        this.type = _type;
        this.identifiant = _identifiant;
        this.valeur = _valeur;
    }

    public AttributeDeclaration(Type _type) {
        this.type = _type;
    }

    public String toString() {
        String _result = this.accessRight.toString() + " " + this.state.toString() + this.type.toString() + " "
                + this.identifiant.getLeft();
        if (this.valeur != null) {
            _result += " = " + this.valeur.toString();
        }
        return _result + ";\n";
    }

    public AccessRight getAccessRight() {
        return this.accessRight;
    }

    public ElementState getState() {
        return this.state;
    }

    @Override
    public String getName() {
        return this.identifiant.getLeft();
    }

    @Override
    public Type getType() {
        return this.type;
    }

    @Override
    public boolean collectAndBackwardResolve(HierarchicalScope<Declaration> _scope) {
        if (((HierarchicalScope<Declaration>) _scope).accepts(this)) {
            _scope.register(this);
            if (this.valeur != null) {
                return this.valeur.collectAndBackwardResolve(_scope);
            }
            return true;
        } else {
            Logger.error("The identifier " + this.identifiant.getLeft() + " is already defined.");
            return false;
        }
    }

    @Override
    public boolean fullResolve(HierarchicalScope<Declaration> _scope) {
        if (this.valeur != null) {
            return this.type.resolve(_scope) && this.valeur.fullResolve(_scope);
        }
        return this.type.resolve(_scope);
    }

    @Override
    public boolean checkType() {
        if (valeur != null) {
            if (type.compatibleWith(this.valeur.getType()) || this.valeur.getType().compatibleWith(type)) {
                return true;
            } else {
                Logger.error("The type of the attribute " + this.identifiant.getLeft() + " is incompatible.");
                return false;
            }
        } else {
            return true;
        }
        
    }

    @Override
    public int allocateMemory(Register _register, int _offset) {
        int _result;
        this.register = _register;
        this.offset = _offset;
        _result = this.type.length();
        return _result;
    }

    @Override
    public Fragment getCode(TAMFactory _factory) {
        Fragment _result = _factory.createFragment();
        if (this.valeur != null) {
            _result.append(this.valeur.getCode(_factory));
            _result.add(_factory.createStore(this.register, this.offset, this.type.length()));
        } else {
            _result.add(_factory.createPush(this.type.length()));
        }
        _result.addPrefix("begin:" + this.identifiant.getLeft());
        _result.addSuffix("end:" + this.identifiant.getLeft());
		return _result;
    }

}
