/*
 * generated by Xtext 2.17.1
 */
package fr.n7.services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.List;
import org.eclipse.xtext.Alternatives;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.EnumLiteralDeclaration;
import org.eclipse.xtext.EnumRule;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.Group;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.TerminalRule;
import org.eclipse.xtext.common.services.TerminalsGrammarAccess;
import org.eclipse.xtext.service.AbstractElementFinder.AbstractEnumRuleElementFinder;
import org.eclipse.xtext.service.AbstractElementFinder.AbstractGrammarElementFinder;
import org.eclipse.xtext.service.GrammarProvider;

@Singleton
public class PDL3GrammarAccess extends AbstractGrammarElementFinder {
	
	public class ProcessElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "fr.n7.PDL3.Process");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cProcessKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cNameAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cNameIDTerminalRuleCall_1_0 = (RuleCall)cNameAssignment_1.eContents().get(0);
		private final Assignment cProcessElementsAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cProcessElementsProcessElementParserRuleCall_2_0 = (RuleCall)cProcessElementsAssignment_2.eContents().get(0);
		
		//Process:
		//	'process : ' name=ID
		//	processElements+=ProcessElement*;
		@Override public ParserRule getRule() { return rule; }
		
		//'process : ' name=ID processElements+=ProcessElement*
		public Group getGroup() { return cGroup; }
		
		//'process : '
		public Keyword getProcessKeyword_0() { return cProcessKeyword_0; }
		
		//name=ID
		public Assignment getNameAssignment_1() { return cNameAssignment_1; }
		
		//ID
		public RuleCall getNameIDTerminalRuleCall_1_0() { return cNameIDTerminalRuleCall_1_0; }
		
		//processElements+=ProcessElement*
		public Assignment getProcessElementsAssignment_2() { return cProcessElementsAssignment_2; }
		
		//ProcessElement
		public RuleCall getProcessElementsProcessElementParserRuleCall_2_0() { return cProcessElementsProcessElementParserRuleCall_2_0; }
	}
	public class ProcessElementElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "fr.n7.PDL3.ProcessElement");
		private final Alternatives cAlternatives = (Alternatives)rule.eContents().get(1);
		private final Group cGroup_0 = (Group)cAlternatives.eContents().get(0);
		private final Keyword cWorkdefinitionsKeyword_0_0 = (Keyword)cGroup_0.eContents().get(0);
		private final Assignment cWorkDefinitionAssignment_0_1 = (Assignment)cGroup_0.eContents().get(1);
		private final RuleCall cWorkDefinitionWorkDefinitionParserRuleCall_0_1_0 = (RuleCall)cWorkDefinitionAssignment_0_1.eContents().get(0);
		private final Group cGroup_1 = (Group)cAlternatives.eContents().get(1);
		private final Keyword cWorksequencesKeyword_1_0 = (Keyword)cGroup_1.eContents().get(0);
		private final Assignment cWorkSequenceAssignment_1_1 = (Assignment)cGroup_1.eContents().get(1);
		private final RuleCall cWorkSequenceWorkSequenceParserRuleCall_1_1_0 = (RuleCall)cWorkSequenceAssignment_1_1.eContents().get(0);
		
		//ProcessElement:
		//	'workdefinitions : ' workDefinition+=WorkDefinition+ | 'worksequences : ' workSequence+=WorkSequence+;
		@Override public ParserRule getRule() { return rule; }
		
		//'workdefinitions : ' workDefinition+=WorkDefinition+ | 'worksequences : ' workSequence+=WorkSequence+
		public Alternatives getAlternatives() { return cAlternatives; }
		
		//'workdefinitions : ' workDefinition+=WorkDefinition+
		public Group getGroup_0() { return cGroup_0; }
		
		//'workdefinitions : '
		public Keyword getWorkdefinitionsKeyword_0_0() { return cWorkdefinitionsKeyword_0_0; }
		
		//workDefinition+=WorkDefinition+
		public Assignment getWorkDefinitionAssignment_0_1() { return cWorkDefinitionAssignment_0_1; }
		
		//WorkDefinition
		public RuleCall getWorkDefinitionWorkDefinitionParserRuleCall_0_1_0() { return cWorkDefinitionWorkDefinitionParserRuleCall_0_1_0; }
		
		//'worksequences : ' workSequence+=WorkSequence+
		public Group getGroup_1() { return cGroup_1; }
		
		//'worksequences : '
		public Keyword getWorksequencesKeyword_1_0() { return cWorksequencesKeyword_1_0; }
		
		//workSequence+=WorkSequence+
		public Assignment getWorkSequenceAssignment_1_1() { return cWorkSequenceAssignment_1_1; }
		
		//WorkSequence
		public RuleCall getWorkSequenceWorkSequenceParserRuleCall_1_1_0() { return cWorkSequenceWorkSequenceParserRuleCall_1_1_0; }
	}
	public class WorkDefinitionElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "fr.n7.PDL3.WorkDefinition");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Assignment cNameAssignment_0 = (Assignment)cGroup.eContents().get(0);
		private final RuleCall cNameIDTerminalRuleCall_0_0 = (RuleCall)cNameAssignment_0.eContents().get(0);
		private final Keyword cSemicolonSpaceKeyword_1 = (Keyword)cGroup.eContents().get(1);
		
		//WorkDefinition:
		//	name=ID '; ';
		@Override public ParserRule getRule() { return rule; }
		
		//name=ID '; '
		public Group getGroup() { return cGroup; }
		
		//name=ID
		public Assignment getNameAssignment_0() { return cNameAssignment_0; }
		
		//ID
		public RuleCall getNameIDTerminalRuleCall_0_0() { return cNameIDTerminalRuleCall_0_0; }
		
		//'; '
		public Keyword getSemicolonSpaceKeyword_1() { return cSemicolonSpaceKeyword_1; }
	}
	public class WorkSequenceElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "fr.n7.PDL3.WorkSequence");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Assignment cPredecessorAssignment_0 = (Assignment)cGroup.eContents().get(0);
		private final CrossReference cPredecessorWorkDefinitionCrossReference_0_0 = (CrossReference)cPredecessorAssignment_0.eContents().get(0);
		private final RuleCall cPredecessorWorkDefinitionIDTerminalRuleCall_0_0_1 = (RuleCall)cPredecessorWorkDefinitionCrossReference_0_0.eContents().get(1);
		private final Assignment cLinkTypeAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cLinkTypeWorkSequenceTypeEnumRuleCall_1_0 = (RuleCall)cLinkTypeAssignment_1.eContents().get(0);
		private final Assignment cSuccessorAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final CrossReference cSuccessorWorkDefinitionCrossReference_2_0 = (CrossReference)cSuccessorAssignment_2.eContents().get(0);
		private final RuleCall cSuccessorWorkDefinitionIDTerminalRuleCall_2_0_1 = (RuleCall)cSuccessorWorkDefinitionCrossReference_2_0.eContents().get(1);
		private final Keyword cSemicolonSpaceKeyword_3 = (Keyword)cGroup.eContents().get(3);
		
		//WorkSequence:
		//	predecessor=[WorkDefinition] linkType=WorkSequenceType successor=[WorkDefinition] '; ';
		@Override public ParserRule getRule() { return rule; }
		
		//predecessor=[WorkDefinition] linkType=WorkSequenceType successor=[WorkDefinition] '; '
		public Group getGroup() { return cGroup; }
		
		//predecessor=[WorkDefinition]
		public Assignment getPredecessorAssignment_0() { return cPredecessorAssignment_0; }
		
		//[WorkDefinition]
		public CrossReference getPredecessorWorkDefinitionCrossReference_0_0() { return cPredecessorWorkDefinitionCrossReference_0_0; }
		
		//ID
		public RuleCall getPredecessorWorkDefinitionIDTerminalRuleCall_0_0_1() { return cPredecessorWorkDefinitionIDTerminalRuleCall_0_0_1; }
		
		//linkType=WorkSequenceType
		public Assignment getLinkTypeAssignment_1() { return cLinkTypeAssignment_1; }
		
		//WorkSequenceType
		public RuleCall getLinkTypeWorkSequenceTypeEnumRuleCall_1_0() { return cLinkTypeWorkSequenceTypeEnumRuleCall_1_0; }
		
		//successor=[WorkDefinition]
		public Assignment getSuccessorAssignment_2() { return cSuccessorAssignment_2; }
		
		//[WorkDefinition]
		public CrossReference getSuccessorWorkDefinitionCrossReference_2_0() { return cSuccessorWorkDefinitionCrossReference_2_0; }
		
		//ID
		public RuleCall getSuccessorWorkDefinitionIDTerminalRuleCall_2_0_1() { return cSuccessorWorkDefinitionIDTerminalRuleCall_2_0_1; }
		
		//'; '
		public Keyword getSemicolonSpaceKeyword_3() { return cSemicolonSpaceKeyword_3; }
	}
	
	public class WorkSequenceTypeElements extends AbstractEnumRuleElementFinder {
		private final EnumRule rule = (EnumRule) GrammarUtil.findRuleForName(getGrammar(), "fr.n7.PDL3.WorkSequenceType");
		private final Alternatives cAlternatives = (Alternatives)rule.eContents().get(1);
		private final EnumLiteralDeclaration cStart2startEnumLiteralDeclaration_0 = (EnumLiteralDeclaration)cAlternatives.eContents().get(0);
		private final Keyword cStart2startS2sKeyword_0_0 = (Keyword)cStart2startEnumLiteralDeclaration_0.eContents().get(0);
		private final EnumLiteralDeclaration cFinish2startEnumLiteralDeclaration_1 = (EnumLiteralDeclaration)cAlternatives.eContents().get(1);
		private final Keyword cFinish2startF2sKeyword_1_0 = (Keyword)cFinish2startEnumLiteralDeclaration_1.eContents().get(0);
		private final EnumLiteralDeclaration cStart2finishEnumLiteralDeclaration_2 = (EnumLiteralDeclaration)cAlternatives.eContents().get(2);
		private final Keyword cStart2finishS2fKeyword_2_0 = (Keyword)cStart2finishEnumLiteralDeclaration_2.eContents().get(0);
		private final EnumLiteralDeclaration cFinish2finishEnumLiteralDeclaration_3 = (EnumLiteralDeclaration)cAlternatives.eContents().get(3);
		private final Keyword cFinish2finishF2fKeyword_3_0 = (Keyword)cFinish2finishEnumLiteralDeclaration_3.eContents().get(0);
		
		//enum WorkSequenceType:
		//	start2start=' s2s '
		//	| finish2start=' f2s '
		//	| start2finish=' s2f '
		//	| finish2finish=' f2f ';
		public EnumRule getRule() { return rule; }
		
		//start2start=' s2s ' | finish2start=' f2s ' | start2finish=' s2f ' | finish2finish=' f2f '
		public Alternatives getAlternatives() { return cAlternatives; }
		
		//start2start=' s2s '
		public EnumLiteralDeclaration getStart2startEnumLiteralDeclaration_0() { return cStart2startEnumLiteralDeclaration_0; }
		
		//' s2s '
		public Keyword getStart2startS2sKeyword_0_0() { return cStart2startS2sKeyword_0_0; }
		
		//finish2start=' f2s '
		public EnumLiteralDeclaration getFinish2startEnumLiteralDeclaration_1() { return cFinish2startEnumLiteralDeclaration_1; }
		
		//' f2s '
		public Keyword getFinish2startF2sKeyword_1_0() { return cFinish2startF2sKeyword_1_0; }
		
		//start2finish=' s2f '
		public EnumLiteralDeclaration getStart2finishEnumLiteralDeclaration_2() { return cStart2finishEnumLiteralDeclaration_2; }
		
		//' s2f '
		public Keyword getStart2finishS2fKeyword_2_0() { return cStart2finishS2fKeyword_2_0; }
		
		//finish2finish=' f2f '
		public EnumLiteralDeclaration getFinish2finishEnumLiteralDeclaration_3() { return cFinish2finishEnumLiteralDeclaration_3; }
		
		//' f2f '
		public Keyword getFinish2finishF2fKeyword_3_0() { return cFinish2finishF2fKeyword_3_0; }
	}
	
	private final ProcessElements pProcess;
	private final ProcessElementElements pProcessElement;
	private final WorkDefinitionElements pWorkDefinition;
	private final WorkSequenceElements pWorkSequence;
	private final WorkSequenceTypeElements eWorkSequenceType;
	
	private final Grammar grammar;
	
	private final TerminalsGrammarAccess gaTerminals;

	@Inject
	public PDL3GrammarAccess(GrammarProvider grammarProvider,
			TerminalsGrammarAccess gaTerminals) {
		this.grammar = internalFindGrammar(grammarProvider);
		this.gaTerminals = gaTerminals;
		this.pProcess = new ProcessElements();
		this.pProcessElement = new ProcessElementElements();
		this.pWorkDefinition = new WorkDefinitionElements();
		this.pWorkSequence = new WorkSequenceElements();
		this.eWorkSequenceType = new WorkSequenceTypeElements();
	}
	
	protected Grammar internalFindGrammar(GrammarProvider grammarProvider) {
		Grammar grammar = grammarProvider.getGrammar(this);
		while (grammar != null) {
			if ("fr.n7.PDL3".equals(grammar.getName())) {
				return grammar;
			}
			List<Grammar> grammars = grammar.getUsedGrammars();
			if (!grammars.isEmpty()) {
				grammar = grammars.iterator().next();
			} else {
				return null;
			}
		}
		return grammar;
	}
	
	@Override
	public Grammar getGrammar() {
		return grammar;
	}
	
	
	public TerminalsGrammarAccess getTerminalsGrammarAccess() {
		return gaTerminals;
	}

	
	//Process:
	//	'process : ' name=ID
	//	processElements+=ProcessElement*;
	public ProcessElements getProcessAccess() {
		return pProcess;
	}
	
	public ParserRule getProcessRule() {
		return getProcessAccess().getRule();
	}
	
	//ProcessElement:
	//	'workdefinitions : ' workDefinition+=WorkDefinition+ | 'worksequences : ' workSequence+=WorkSequence+;
	public ProcessElementElements getProcessElementAccess() {
		return pProcessElement;
	}
	
	public ParserRule getProcessElementRule() {
		return getProcessElementAccess().getRule();
	}
	
	//WorkDefinition:
	//	name=ID '; ';
	public WorkDefinitionElements getWorkDefinitionAccess() {
		return pWorkDefinition;
	}
	
	public ParserRule getWorkDefinitionRule() {
		return getWorkDefinitionAccess().getRule();
	}
	
	//WorkSequence:
	//	predecessor=[WorkDefinition] linkType=WorkSequenceType successor=[WorkDefinition] '; ';
	public WorkSequenceElements getWorkSequenceAccess() {
		return pWorkSequence;
	}
	
	public ParserRule getWorkSequenceRule() {
		return getWorkSequenceAccess().getRule();
	}
	
	//enum WorkSequenceType:
	//	start2start=' s2s '
	//	| finish2start=' f2s '
	//	| start2finish=' s2f '
	//	| finish2finish=' f2f ';
	public WorkSequenceTypeElements getWorkSequenceTypeAccess() {
		return eWorkSequenceType;
	}
	
	public EnumRule getWorkSequenceTypeRule() {
		return getWorkSequenceTypeAccess().getRule();
	}
	
	//terminal ID:
	//	'^'? ('a'..'z' | 'A'..'Z' | '_') ('a'..'z' | 'A'..'Z' | '_' | '0'..'9')*;
	public TerminalRule getIDRule() {
		return gaTerminals.getIDRule();
	}
	
	//terminal INT returns ecore::EInt:
	//	'0'..'9'+;
	public TerminalRule getINTRule() {
		return gaTerminals.getINTRule();
	}
	
	//terminal STRING:
	//	'"' ('\\' . | !('\\' | '"'))* '"' |
	//	"'" ('\\' . | !('\\' | "'"))* "'";
	public TerminalRule getSTRINGRule() {
		return gaTerminals.getSTRINGRule();
	}
	
	//terminal ML_COMMENT:
	//	'/*'->'*/';
	public TerminalRule getML_COMMENTRule() {
		return gaTerminals.getML_COMMENTRule();
	}
	
	//terminal SL_COMMENT:
	//	'//' !('\n' | '\r')* ('\r'? '\n')?;
	public TerminalRule getSL_COMMENTRule() {
		return gaTerminals.getSL_COMMENTRule();
	}
	
	//terminal WS:
	//	' ' | '\t' | '\r' | '\n'+;
	public TerminalRule getWSRule() {
		return gaTerminals.getWSRule();
	}
	
	//terminal ANY_OTHER:
	//	.;
	public TerminalRule getANY_OTHERRule() {
		return gaTerminals.getANY_OTHERRule();
	}
}
