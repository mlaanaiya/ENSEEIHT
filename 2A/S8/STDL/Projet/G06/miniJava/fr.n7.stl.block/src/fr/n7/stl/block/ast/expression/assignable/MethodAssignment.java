package fr.n7.stl.block.ast.expression.assignable;

import java.util.List;

import fr.n7.stl.block.ast.expression.AbstractMethod;
import fr.n7.stl.block.ast.expression.Expression;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.TAMFactory;

public class MethodAssignment extends AbstractMethod implements AssignableExpression {

    

    public MethodAssignment(Expression _record, String _name, List<Expression> _parameters) {
        super(_record, _name, _parameters);
        //TODO Auto-generated constructor stub
    }

    @Override
    public Fragment getCode(TAMFactory _factory) {
        return null;
    }

}
