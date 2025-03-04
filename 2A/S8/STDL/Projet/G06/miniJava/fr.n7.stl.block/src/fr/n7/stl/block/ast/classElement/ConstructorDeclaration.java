package fr.n7.stl.block.ast.classElement;

import java.util.Iterator;
import java.util.List;

import fr.n7.stl.block.ast.Block;
import fr.n7.stl.block.ast.instruction.declaration.ParameterDeclaration;
import fr.n7.stl.block.ast.scope.Declaration;
import fr.n7.stl.block.ast.scope.HierarchicalScope;
import fr.n7.stl.block.ast.scope.SymbolTable;
import fr.n7.stl.block.ast.type.AtomicType;
import fr.n7.stl.block.ast.type.Type;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.Register;
import fr.n7.stl.tam.ast.TAMFactory;
import fr.n7.stl.util.Logger;

public class ConstructorDeclaration implements ClassElement {

    protected String identifiant;

    protected List<ParameterDeclaration> parameters;

    protected Block corps;

    protected SymbolTable parametersTable;

    protected int offset;

    public ConstructorDeclaration(String _identifiant, List<ParameterDeclaration> _parameters, Block _corps) {
        this.identifiant = _identifiant;
        this.parameters = _parameters;
        this.corps = _corps;
    }

    public String toString() {
        String _result = "public " + this.identifiant + "(";
        if (parameters != null) {
            Iterator<ParameterDeclaration> _iter = this.parameters.iterator();
            if (_iter.hasNext()) {
                _result += _iter.next();
                while (_iter.hasNext()) {
                    _result += " ," + _iter.next();
                }
            }
        }
        return _result + ") " + corps;
    }

    public List<ParameterDeclaration> getParameters() {
        return this.parameters;
    }

    @Override
    public boolean collectAndBackwardResolve(HierarchicalScope<Declaration> _scope) {
        if (this.parameters != null) {
            for (ParameterDeclaration p : this.parameters) {
                this.identifiant += p.getType();
            }
        }
        if (!(this.identifiant.contains(SymbolTable.classDeclaration.getName()))) {
            Logger.error("The constructor identifier " + this.identifiant + " is not the same as the class identifier.");
            return false;
        }
        
        if (((HierarchicalScope<Declaration>) _scope).accepts(this)) {
            _scope.register(this);
            SymbolTable tableParametres = new SymbolTable(_scope);
            boolean result = true;
            if (this.parameters != null) {
                for (ParameterDeclaration d : this.parameters) {
                    tableParametres.register(d);
                }
            }
            this.parametersTable = tableParametres;
            result = this.corps.collect(tableParametres);
            return result;
        } else {
            Logger.error("The constructor identifier " + this.getName() + " is already defined.");
            return false;
        }
    }

    @Override
    public boolean fullResolve(HierarchicalScope<Declaration> _scope) {
        return this.corps.resolve(this.parametersTable);
    }

    @Override
    public boolean checkType() {
        boolean _result = true;
        if (this.parameters != null) {
            for(ParameterDeclaration parameterDeclaration : this.parameters) {
                if (parameterDeclaration.getType().equalsTo(AtomicType.ErrorType)) {
                    Logger.error(parameterDeclaration + " is not compatible with parameters type.");
                    _result = false;
                }
            }
        }
		_result = _result && corps.checkType();
        return _result;
    }

    @Override
    public int allocateMemory(Register _register, int _offset) {
        this.offset = _offset;
        return 0;
    }

    @Override
    public Fragment getCode(TAMFactory _factory) {
        Fragment _result = _factory.createFragment();
		_result.append(this.corps.getCode(_factory));
		_result.addPrefix("begin:" + this.identifiant);
		_result.addSuffix("end:" + this.identifiant);
        _result.add(_factory.createReturn(0, this.offset));
		return _result;

    }

    @Override
    public String getName() {
        return this.identifiant;
    }

    @Override
    public Type getType() {
        return null;
    }

}
