package fr.n7.stl.block.ast.classElement;

public enum AccessRight {

    Private,

    Protected,

    Public;

    public String toString() {
        switch (this) {
            case Private:
                return "private";
            case Protected:
                return "protected";
            case Public:
                return "public";
            default:
                throw new IllegalArgumentException("The default case should never be triggered.");
        }
    }

}
