package fr.n7.stl.block.ast.type;

import java.util.Iterator;
import java.util.List;

import javax.swing.text.html.HTMLDocument.BlockElement;

import fr.n7.stl.block.ast.classElement.ClassElement;
import fr.n7.stl.block.ast.classElement.ConstructorDeclaration;
import fr.n7.stl.block.ast.classElement.MethodDeclaration;
import fr.n7.stl.block.ast.SemanticsUndefinedException;
import fr.n7.stl.block.ast.classElement.AttributeDeclaration;
import fr.n7.stl.block.ast.element.ClassDeclaration;
import fr.n7.stl.block.ast.scope.Declaration;
import fr.n7.stl.block.ast.scope.HierarchicalScope;
import fr.n7.stl.block.ast.scope.Scope;
import fr.n7.stl.block.ast.scope.SymbolTable;
import fr.n7.stl.util.Logger;

public class Instance implements Type, Scope<ClassElement> {

    private String name;

    private ClassDeclaration classDeclaration;

    public Instance(String _name) {
        this.name = _name;
        this.classDeclaration = null;
    }

    public Instance(String _name, ClassDeclaration _classDeclaration) {
        this.name = _name;
        this.classDeclaration = _classDeclaration;
    }

    public ClassDeclaration getClassDeclaration() {
        return this.classDeclaration;
    }

    public void setClassDeclaration(ClassDeclaration d) {
        this.classDeclaration = d;
    }

    public List<ClassElement> getAllElements() {
        return this.classDeclaration.getClassElements();
    }

    public List<AttributeDeclaration> getAttributes() {
        return this.classDeclaration.getClassAttributes();
    }

    public List<MethodDeclaration> getMethods() {
        return this.classDeclaration.getClassMethods();
    }

    public List<ConstructorDeclaration> getConstructors() {
        return this.classDeclaration.getClassConstructors();
    }

    @Override
    public boolean equalsTo(Type _other) {
        boolean _result = true;
        if (_other instanceof Instance) {
            for (ClassDeclaration c : SymbolTable.classesDeclaration) {
                if (c.getName().equals(this.name)) {
                    if (_other.length() == length()) {
                        for (int i = 0; i < length(); i++) {
                            if (!this.getAllElements().get(i).equals(c.getClassElements().get(i))) {
                                _result = false;
                            }
                        }
                        _result = true;
                    } else {
                        _result = false;
                    }
                }
            }
        } else {
            _result = false;
        }
        return _result;
    }

    @Override
    public boolean compatibleWith(Type _other) {
        return equalsTo(_other);
    }

    @Override
    public Type merge(Type _other) {
        Type _result;
        if (_other instanceof Instance) {
            _result = this;
        } else {
            Logger.error(_other + " has not the same type of " + this + ".");
            _result = AtomicType.ErrorType;
        }
        return _result;
    }

    @Override
    public int length() {
        int _result = 0;
        for (ClassDeclaration c : SymbolTable.classesDeclaration) {
            if (c.getName().equals(this.name)) {
                /**for (MethodDeclaration m : c.getClassMethods()) {
                  _result += m.getType().length();
                }*/
                for (AttributeDeclaration a : c.getClassAttributes()) {
                    _result += a.getType().length();
                }
            }
        }
        return _result;
    }

    @Override
    public boolean resolve(HierarchicalScope<Declaration> _scope) {
        Declaration d = _scope.get(this.name);
        boolean _result = true;
        if (d instanceof ClassDeclaration) {
            this.classDeclaration = (ClassDeclaration) d;
        } else {
            Logger.error("The class identifier " + this.name + " is not defined.");
            return false;
        }
        return _result;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public ClassElement get(String _name) {
        boolean _found = false;
        Iterator<ClassElement> _iter = this.getAllElements().iterator();
        ClassElement _current = null;
        while (_iter.hasNext() && (!_found)) {
            _current = _iter.next();
            _found = _found || _current.getName().contentEquals(_name);
        }
        if (_found) {
            return _current;
        } else {
            return null;
        }
    }

    @Override
    public boolean contains(String _name) {
        boolean _result = false;
        Iterator<ClassElement> _iter = this.classDeclaration.getClassElements().iterator();
        while (_iter.hasNext() && (!_result)) {
            _result = _result || _iter.next().getName().contentEquals(_name);
        }
        return _result;
    }

    @Override
    public boolean accepts(ClassElement _declaration) {
        return !this.contains(_declaration.getName());
    }

    @Override
    public void register(ClassElement _declaration) {
        throw new SemanticsUndefinedException("register undifined");
    }

}
