package fr.n7.stl.block.ast.classElement;

import java.util.Iterator;
import java.util.List;

import fr.n7.stl.block.ast.instruction.declaration.ParameterDeclaration;
import fr.n7.stl.block.ast.type.PartialType;
import fr.n7.stl.block.ast.type.Type;
import fr.n7.stl.util.Pair;

public class Signature {

    protected Type type;

    protected Pair<String, PartialType> identifiant;

    protected List<ParameterDeclaration> parameters;

    public Signature(Type _type, Pair<String, PartialType> _identifiant, List<ParameterDeclaration> _parameters) {
        this.type = _type;
        this.identifiant = _identifiant;
        this.parameters = _parameters;
    }

    public String toString() {
        String _result = type.toString() + " " + this.identifiant.getLeft() + "(";
        if (parameters != null) {
            Iterator<ParameterDeclaration> _iter = this.parameters.iterator();
            if (_iter.hasNext()) {
                _result += _iter.next();
                while (_iter.hasNext()) {
                    _result += " ," + _iter.next();
                }
            }
        }
        return _result + ")";
    }

    public Type getType() {
        return this.type;
    }

    public String getName() {
        return this.identifiant.getLeft();
    }

    public List<ParameterDeclaration> getParameters() {
        return this.parameters;
    }

}
