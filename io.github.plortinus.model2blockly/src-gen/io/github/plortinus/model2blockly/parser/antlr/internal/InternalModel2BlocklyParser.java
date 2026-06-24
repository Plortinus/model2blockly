package io.github.plortinus.model2blockly.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import io.github.plortinus.model2blockly.services.Model2BlocklyGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalModel2BlocklyParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_STRING", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'domain'", "'codeLanguage'", "'codeFileExtension'", "'runtimeKind'", "'category'", "'label'", "'colour'", "'{'", "'}'", "'abstract'", "'output'", "'as'", "'class'", "'extends'", "'message0'", "'tooltip'", "'helpUrl'", "'inputsInline'", "'inline'", "'code'", "'attribute'", "':'", "'default'", "'min'", "'max'", "'src'", "'width'", "'height'", "'alt'", "'required'", "'modelId'", "'unique'", "'nonUnique'", "'ordered'", "'unordered'", "'contains'", "'['", "'..'", "']'", "'reference'", "'opposite'", "'value'", "'shadow'", "'widget'", "'uiLabel'", "'help'", "'placeholder'", "'group'", "'order'", "'readonly'", "'hidden'", "'variant'", "'referenceLabelField'", "'enum'", "','", "'='", "'constraint'", "'on'", "'must'", "'follow'", "'validation'", "'errorMessage'", "'workspace'", "'text'", "'textarea'", "'number'", "'slider'", "'switch'", "'checkbox'", "'select'", "'radio'", "'color'", "'angle'", "'image'", "'reference-select'", "'slot'", "'expression-slot'", "'compact'", "'prominent'", "'string'", "'int'", "'boolean'", "'float'", "'expression'", "'condition'", "'js'", "'ocl'", "'true'", "'false'"
    };
    public static final int T__50=50;
    public static final int T__59=59;
    public static final int T__55=55;
    public static final int T__56=56;
    public static final int T__57=57;
    public static final int T__58=58;
    public static final int T__51=51;
    public static final int T__52=52;
    public static final int T__53=53;
    public static final int T__54=54;
    public static final int T__60=60;
    public static final int T__61=61;
    public static final int RULE_ID=4;
    public static final int RULE_INT=6;
    public static final int T__66=66;
    public static final int RULE_ML_COMMENT=7;
    public static final int T__67=67;
    public static final int T__68=68;
    public static final int T__69=69;
    public static final int T__62=62;
    public static final int T__63=63;
    public static final int T__64=64;
    public static final int T__65=65;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int T__48=48;
    public static final int T__49=49;
    public static final int T__44=44;
    public static final int T__45=45;
    public static final int T__46=46;
    public static final int T__47=47;
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int T__42=42;
    public static final int T__43=43;
    public static final int T__91=91;
    public static final int T__92=92;
    public static final int T__93=93;
    public static final int T__94=94;
    public static final int T__90=90;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__11=11;
    public static final int T__99=99;
    public static final int T__12=12;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int T__95=95;
    public static final int T__96=96;
    public static final int T__97=97;
    public static final int T__98=98;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int T__29=29;
    public static final int T__22=22;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__20=20;
    public static final int T__21=21;
    public static final int T__70=70;
    public static final int T__71=71;
    public static final int T__72=72;
    public static final int RULE_STRING=5;
    public static final int RULE_SL_COMMENT=8;
    public static final int T__77=77;
    public static final int T__78=78;
    public static final int T__79=79;
    public static final int T__73=73;
    public static final int EOF=-1;
    public static final int T__74=74;
    public static final int T__75=75;
    public static final int T__76=76;
    public static final int T__80=80;
    public static final int T__81=81;
    public static final int T__82=82;
    public static final int T__83=83;
    public static final int RULE_WS=9;
    public static final int RULE_ANY_OTHER=10;
    public static final int T__88=88;
    public static final int T__89=89;
    public static final int T__84=84;
    public static final int T__85=85;
    public static final int T__86=86;
    public static final int T__87=87;

    // delegates
    // delegators


        public InternalModel2BlocklyParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalModel2BlocklyParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalModel2BlocklyParser.tokenNames; }
    public String getGrammarFileName() { return "InternalModel2Blockly.g"; }



     	private Model2BlocklyGrammarAccess grammarAccess;

        public InternalModel2BlocklyParser(TokenStream input, Model2BlocklyGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }

        @Override
        protected String getFirstRuleName() {
        	return "DomainModel";
       	}

       	@Override
       	protected Model2BlocklyGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}




    // $ANTLR start "entryRuleDomainModel"
    // InternalModel2Blockly.g:65:1: entryRuleDomainModel returns [EObject current=null] : iv_ruleDomainModel= ruleDomainModel EOF ;
    public final EObject entryRuleDomainModel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDomainModel = null;


        try {
            // InternalModel2Blockly.g:65:52: (iv_ruleDomainModel= ruleDomainModel EOF )
            // InternalModel2Blockly.g:66:2: iv_ruleDomainModel= ruleDomainModel EOF
            {
             newCompositeNode(grammarAccess.getDomainModelRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleDomainModel=ruleDomainModel();

            state._fsp--;

             current =iv_ruleDomainModel; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleDomainModel"


    // $ANTLR start "ruleDomainModel"
    // InternalModel2Blockly.g:72:1: ruleDomainModel returns [EObject current=null] : (otherlv_0= 'domain' ( (lv_name_1_0= RULE_ID ) ) (otherlv_2= 'codeLanguage' ( (lv_codeLanguage_3_0= RULE_STRING ) ) )? (otherlv_4= 'codeFileExtension' ( (lv_codeFileExtension_5_0= RULE_STRING ) ) )? (otherlv_6= 'runtimeKind' ( (lv_runtimeKind_7_0= RULE_STRING ) ) )? ( (lv_workspace_8_0= ruleWorkspaceConfig ) )? ( (lv_categories_9_0= ruleCategoryDef ) )* ( (lv_classes_10_0= ruleClassDef ) )* ( (lv_constraints_11_0= ruleConstraintDef ) )* ( (lv_validations_12_0= ruleValidationDef ) )* ) ;
    public final EObject ruleDomainModel() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_2=null;
        Token lv_codeLanguage_3_0=null;
        Token otherlv_4=null;
        Token lv_codeFileExtension_5_0=null;
        Token otherlv_6=null;
        Token lv_runtimeKind_7_0=null;
        EObject lv_workspace_8_0 = null;

        EObject lv_categories_9_0 = null;

        EObject lv_classes_10_0 = null;

        EObject lv_constraints_11_0 = null;

        EObject lv_validations_12_0 = null;



        	enterRule();

        try {
            // InternalModel2Blockly.g:78:2: ( (otherlv_0= 'domain' ( (lv_name_1_0= RULE_ID ) ) (otherlv_2= 'codeLanguage' ( (lv_codeLanguage_3_0= RULE_STRING ) ) )? (otherlv_4= 'codeFileExtension' ( (lv_codeFileExtension_5_0= RULE_STRING ) ) )? (otherlv_6= 'runtimeKind' ( (lv_runtimeKind_7_0= RULE_STRING ) ) )? ( (lv_workspace_8_0= ruleWorkspaceConfig ) )? ( (lv_categories_9_0= ruleCategoryDef ) )* ( (lv_classes_10_0= ruleClassDef ) )* ( (lv_constraints_11_0= ruleConstraintDef ) )* ( (lv_validations_12_0= ruleValidationDef ) )* ) )
            // InternalModel2Blockly.g:79:2: (otherlv_0= 'domain' ( (lv_name_1_0= RULE_ID ) ) (otherlv_2= 'codeLanguage' ( (lv_codeLanguage_3_0= RULE_STRING ) ) )? (otherlv_4= 'codeFileExtension' ( (lv_codeFileExtension_5_0= RULE_STRING ) ) )? (otherlv_6= 'runtimeKind' ( (lv_runtimeKind_7_0= RULE_STRING ) ) )? ( (lv_workspace_8_0= ruleWorkspaceConfig ) )? ( (lv_categories_9_0= ruleCategoryDef ) )* ( (lv_classes_10_0= ruleClassDef ) )* ( (lv_constraints_11_0= ruleConstraintDef ) )* ( (lv_validations_12_0= ruleValidationDef ) )* )
            {
            // InternalModel2Blockly.g:79:2: (otherlv_0= 'domain' ( (lv_name_1_0= RULE_ID ) ) (otherlv_2= 'codeLanguage' ( (lv_codeLanguage_3_0= RULE_STRING ) ) )? (otherlv_4= 'codeFileExtension' ( (lv_codeFileExtension_5_0= RULE_STRING ) ) )? (otherlv_6= 'runtimeKind' ( (lv_runtimeKind_7_0= RULE_STRING ) ) )? ( (lv_workspace_8_0= ruleWorkspaceConfig ) )? ( (lv_categories_9_0= ruleCategoryDef ) )* ( (lv_classes_10_0= ruleClassDef ) )* ( (lv_constraints_11_0= ruleConstraintDef ) )* ( (lv_validations_12_0= ruleValidationDef ) )* )
            // InternalModel2Blockly.g:80:3: otherlv_0= 'domain' ( (lv_name_1_0= RULE_ID ) ) (otherlv_2= 'codeLanguage' ( (lv_codeLanguage_3_0= RULE_STRING ) ) )? (otherlv_4= 'codeFileExtension' ( (lv_codeFileExtension_5_0= RULE_STRING ) ) )? (otherlv_6= 'runtimeKind' ( (lv_runtimeKind_7_0= RULE_STRING ) ) )? ( (lv_workspace_8_0= ruleWorkspaceConfig ) )? ( (lv_categories_9_0= ruleCategoryDef ) )* ( (lv_classes_10_0= ruleClassDef ) )* ( (lv_constraints_11_0= ruleConstraintDef ) )* ( (lv_validations_12_0= ruleValidationDef ) )*
            {
            otherlv_0=(Token)match(input,11,FOLLOW_3); 

            			newLeafNode(otherlv_0, grammarAccess.getDomainModelAccess().getDomainKeyword_0());
            		
            // InternalModel2Blockly.g:84:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalModel2Blockly.g:85:4: (lv_name_1_0= RULE_ID )
            {
            // InternalModel2Blockly.g:85:4: (lv_name_1_0= RULE_ID )
            // InternalModel2Blockly.g:86:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_4); 

            					newLeafNode(lv_name_1_0, grammarAccess.getDomainModelAccess().getNameIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getDomainModelRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            // InternalModel2Blockly.g:102:3: (otherlv_2= 'codeLanguage' ( (lv_codeLanguage_3_0= RULE_STRING ) ) )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==12) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // InternalModel2Blockly.g:103:4: otherlv_2= 'codeLanguage' ( (lv_codeLanguage_3_0= RULE_STRING ) )
                    {
                    otherlv_2=(Token)match(input,12,FOLLOW_5); 

                    				newLeafNode(otherlv_2, grammarAccess.getDomainModelAccess().getCodeLanguageKeyword_2_0());
                    			
                    // InternalModel2Blockly.g:107:4: ( (lv_codeLanguage_3_0= RULE_STRING ) )
                    // InternalModel2Blockly.g:108:5: (lv_codeLanguage_3_0= RULE_STRING )
                    {
                    // InternalModel2Blockly.g:108:5: (lv_codeLanguage_3_0= RULE_STRING )
                    // InternalModel2Blockly.g:109:6: lv_codeLanguage_3_0= RULE_STRING
                    {
                    lv_codeLanguage_3_0=(Token)match(input,RULE_STRING,FOLLOW_6); 

                    						newLeafNode(lv_codeLanguage_3_0, grammarAccess.getDomainModelAccess().getCodeLanguageSTRINGTerminalRuleCall_2_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getDomainModelRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"codeLanguage",
                    							lv_codeLanguage_3_0,
                    							"org.eclipse.xtext.common.Terminals.STRING");
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalModel2Blockly.g:126:3: (otherlv_4= 'codeFileExtension' ( (lv_codeFileExtension_5_0= RULE_STRING ) ) )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==13) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // InternalModel2Blockly.g:127:4: otherlv_4= 'codeFileExtension' ( (lv_codeFileExtension_5_0= RULE_STRING ) )
                    {
                    otherlv_4=(Token)match(input,13,FOLLOW_5); 

                    				newLeafNode(otherlv_4, grammarAccess.getDomainModelAccess().getCodeFileExtensionKeyword_3_0());
                    			
                    // InternalModel2Blockly.g:131:4: ( (lv_codeFileExtension_5_0= RULE_STRING ) )
                    // InternalModel2Blockly.g:132:5: (lv_codeFileExtension_5_0= RULE_STRING )
                    {
                    // InternalModel2Blockly.g:132:5: (lv_codeFileExtension_5_0= RULE_STRING )
                    // InternalModel2Blockly.g:133:6: lv_codeFileExtension_5_0= RULE_STRING
                    {
                    lv_codeFileExtension_5_0=(Token)match(input,RULE_STRING,FOLLOW_7); 

                    						newLeafNode(lv_codeFileExtension_5_0, grammarAccess.getDomainModelAccess().getCodeFileExtensionSTRINGTerminalRuleCall_3_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getDomainModelRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"codeFileExtension",
                    							lv_codeFileExtension_5_0,
                    							"org.eclipse.xtext.common.Terminals.STRING");
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalModel2Blockly.g:150:3: (otherlv_6= 'runtimeKind' ( (lv_runtimeKind_7_0= RULE_STRING ) ) )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==14) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // InternalModel2Blockly.g:151:4: otherlv_6= 'runtimeKind' ( (lv_runtimeKind_7_0= RULE_STRING ) )
                    {
                    otherlv_6=(Token)match(input,14,FOLLOW_5); 

                    				newLeafNode(otherlv_6, grammarAccess.getDomainModelAccess().getRuntimeKindKeyword_4_0());
                    			
                    // InternalModel2Blockly.g:155:4: ( (lv_runtimeKind_7_0= RULE_STRING ) )
                    // InternalModel2Blockly.g:156:5: (lv_runtimeKind_7_0= RULE_STRING )
                    {
                    // InternalModel2Blockly.g:156:5: (lv_runtimeKind_7_0= RULE_STRING )
                    // InternalModel2Blockly.g:157:6: lv_runtimeKind_7_0= RULE_STRING
                    {
                    lv_runtimeKind_7_0=(Token)match(input,RULE_STRING,FOLLOW_8); 

                    						newLeafNode(lv_runtimeKind_7_0, grammarAccess.getDomainModelAccess().getRuntimeKindSTRINGTerminalRuleCall_4_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getDomainModelRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"runtimeKind",
                    							lv_runtimeKind_7_0,
                    							"org.eclipse.xtext.common.Terminals.STRING");
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalModel2Blockly.g:174:3: ( (lv_workspace_8_0= ruleWorkspaceConfig ) )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==73) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // InternalModel2Blockly.g:175:4: (lv_workspace_8_0= ruleWorkspaceConfig )
                    {
                    // InternalModel2Blockly.g:175:4: (lv_workspace_8_0= ruleWorkspaceConfig )
                    // InternalModel2Blockly.g:176:5: lv_workspace_8_0= ruleWorkspaceConfig
                    {

                    					newCompositeNode(grammarAccess.getDomainModelAccess().getWorkspaceWorkspaceConfigParserRuleCall_5_0());
                    				
                    pushFollow(FOLLOW_9);
                    lv_workspace_8_0=ruleWorkspaceConfig();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getDomainModelRule());
                    					}
                    					set(
                    						current,
                    						"workspace",
                    						lv_workspace_8_0,
                    						"io.github.plortinus.model2blockly.Model2Blockly.WorkspaceConfig");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            // InternalModel2Blockly.g:193:3: ( (lv_categories_9_0= ruleCategoryDef ) )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==15) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // InternalModel2Blockly.g:194:4: (lv_categories_9_0= ruleCategoryDef )
            	    {
            	    // InternalModel2Blockly.g:194:4: (lv_categories_9_0= ruleCategoryDef )
            	    // InternalModel2Blockly.g:195:5: lv_categories_9_0= ruleCategoryDef
            	    {

            	    					newCompositeNode(grammarAccess.getDomainModelAccess().getCategoriesCategoryDefParserRuleCall_6_0());
            	    				
            	    pushFollow(FOLLOW_9);
            	    lv_categories_9_0=ruleCategoryDef();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getDomainModelRule());
            	    					}
            	    					add(
            	    						current,
            	    						"categories",
            	    						lv_categories_9_0,
            	    						"io.github.plortinus.model2blockly.Model2Blockly.CategoryDef");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

            // InternalModel2Blockly.g:212:3: ( (lv_classes_10_0= ruleClassDef ) )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( ((LA6_0>=20 && LA6_0<=21)||LA6_0==23) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // InternalModel2Blockly.g:213:4: (lv_classes_10_0= ruleClassDef )
            	    {
            	    // InternalModel2Blockly.g:213:4: (lv_classes_10_0= ruleClassDef )
            	    // InternalModel2Blockly.g:214:5: lv_classes_10_0= ruleClassDef
            	    {

            	    					newCompositeNode(grammarAccess.getDomainModelAccess().getClassesClassDefParserRuleCall_7_0());
            	    				
            	    pushFollow(FOLLOW_10);
            	    lv_classes_10_0=ruleClassDef();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getDomainModelRule());
            	    					}
            	    					add(
            	    						current,
            	    						"classes",
            	    						lv_classes_10_0,
            	    						"io.github.plortinus.model2blockly.Model2Blockly.ClassDef");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);

            // InternalModel2Blockly.g:231:3: ( (lv_constraints_11_0= ruleConstraintDef ) )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==67) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // InternalModel2Blockly.g:232:4: (lv_constraints_11_0= ruleConstraintDef )
            	    {
            	    // InternalModel2Blockly.g:232:4: (lv_constraints_11_0= ruleConstraintDef )
            	    // InternalModel2Blockly.g:233:5: lv_constraints_11_0= ruleConstraintDef
            	    {

            	    					newCompositeNode(grammarAccess.getDomainModelAccess().getConstraintsConstraintDefParserRuleCall_8_0());
            	    				
            	    pushFollow(FOLLOW_11);
            	    lv_constraints_11_0=ruleConstraintDef();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getDomainModelRule());
            	    					}
            	    					add(
            	    						current,
            	    						"constraints",
            	    						lv_constraints_11_0,
            	    						"io.github.plortinus.model2blockly.Model2Blockly.ConstraintDef");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

            // InternalModel2Blockly.g:250:3: ( (lv_validations_12_0= ruleValidationDef ) )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==71) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // InternalModel2Blockly.g:251:4: (lv_validations_12_0= ruleValidationDef )
            	    {
            	    // InternalModel2Blockly.g:251:4: (lv_validations_12_0= ruleValidationDef )
            	    // InternalModel2Blockly.g:252:5: lv_validations_12_0= ruleValidationDef
            	    {

            	    					newCompositeNode(grammarAccess.getDomainModelAccess().getValidationsValidationDefParserRuleCall_9_0());
            	    				
            	    pushFollow(FOLLOW_12);
            	    lv_validations_12_0=ruleValidationDef();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getDomainModelRule());
            	    					}
            	    					add(
            	    						current,
            	    						"validations",
            	    						lv_validations_12_0,
            	    						"io.github.plortinus.model2blockly.Model2Blockly.ValidationDef");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleDomainModel"


    // $ANTLR start "entryRuleCategoryDef"
    // InternalModel2Blockly.g:273:1: entryRuleCategoryDef returns [EObject current=null] : iv_ruleCategoryDef= ruleCategoryDef EOF ;
    public final EObject entryRuleCategoryDef() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCategoryDef = null;


        try {
            // InternalModel2Blockly.g:273:52: (iv_ruleCategoryDef= ruleCategoryDef EOF )
            // InternalModel2Blockly.g:274:2: iv_ruleCategoryDef= ruleCategoryDef EOF
            {
             newCompositeNode(grammarAccess.getCategoryDefRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleCategoryDef=ruleCategoryDef();

            state._fsp--;

             current =iv_ruleCategoryDef; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleCategoryDef"


    // $ANTLR start "ruleCategoryDef"
    // InternalModel2Blockly.g:280:1: ruleCategoryDef returns [EObject current=null] : (otherlv_0= 'category' ( (lv_name_1_0= RULE_ID ) ) (otherlv_2= 'label' ( (lv_label_3_0= RULE_STRING ) ) )? (otherlv_4= 'colour' ( (lv_colour_5_0= RULE_INT ) ) )? (otherlv_6= '{' ( (lv_subcategories_7_0= ruleCategoryDef ) )* otherlv_8= '}' )? ) ;
    public final EObject ruleCategoryDef() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_2=null;
        Token lv_label_3_0=null;
        Token otherlv_4=null;
        Token lv_colour_5_0=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        EObject lv_subcategories_7_0 = null;



        	enterRule();

        try {
            // InternalModel2Blockly.g:286:2: ( (otherlv_0= 'category' ( (lv_name_1_0= RULE_ID ) ) (otherlv_2= 'label' ( (lv_label_3_0= RULE_STRING ) ) )? (otherlv_4= 'colour' ( (lv_colour_5_0= RULE_INT ) ) )? (otherlv_6= '{' ( (lv_subcategories_7_0= ruleCategoryDef ) )* otherlv_8= '}' )? ) )
            // InternalModel2Blockly.g:287:2: (otherlv_0= 'category' ( (lv_name_1_0= RULE_ID ) ) (otherlv_2= 'label' ( (lv_label_3_0= RULE_STRING ) ) )? (otherlv_4= 'colour' ( (lv_colour_5_0= RULE_INT ) ) )? (otherlv_6= '{' ( (lv_subcategories_7_0= ruleCategoryDef ) )* otherlv_8= '}' )? )
            {
            // InternalModel2Blockly.g:287:2: (otherlv_0= 'category' ( (lv_name_1_0= RULE_ID ) ) (otherlv_2= 'label' ( (lv_label_3_0= RULE_STRING ) ) )? (otherlv_4= 'colour' ( (lv_colour_5_0= RULE_INT ) ) )? (otherlv_6= '{' ( (lv_subcategories_7_0= ruleCategoryDef ) )* otherlv_8= '}' )? )
            // InternalModel2Blockly.g:288:3: otherlv_0= 'category' ( (lv_name_1_0= RULE_ID ) ) (otherlv_2= 'label' ( (lv_label_3_0= RULE_STRING ) ) )? (otherlv_4= 'colour' ( (lv_colour_5_0= RULE_INT ) ) )? (otherlv_6= '{' ( (lv_subcategories_7_0= ruleCategoryDef ) )* otherlv_8= '}' )?
            {
            otherlv_0=(Token)match(input,15,FOLLOW_3); 

            			newLeafNode(otherlv_0, grammarAccess.getCategoryDefAccess().getCategoryKeyword_0());
            		
            // InternalModel2Blockly.g:292:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalModel2Blockly.g:293:4: (lv_name_1_0= RULE_ID )
            {
            // InternalModel2Blockly.g:293:4: (lv_name_1_0= RULE_ID )
            // InternalModel2Blockly.g:294:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_13); 

            					newLeafNode(lv_name_1_0, grammarAccess.getCategoryDefAccess().getNameIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getCategoryDefRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            // InternalModel2Blockly.g:310:3: (otherlv_2= 'label' ( (lv_label_3_0= RULE_STRING ) ) )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==16) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // InternalModel2Blockly.g:311:4: otherlv_2= 'label' ( (lv_label_3_0= RULE_STRING ) )
                    {
                    otherlv_2=(Token)match(input,16,FOLLOW_5); 

                    				newLeafNode(otherlv_2, grammarAccess.getCategoryDefAccess().getLabelKeyword_2_0());
                    			
                    // InternalModel2Blockly.g:315:4: ( (lv_label_3_0= RULE_STRING ) )
                    // InternalModel2Blockly.g:316:5: (lv_label_3_0= RULE_STRING )
                    {
                    // InternalModel2Blockly.g:316:5: (lv_label_3_0= RULE_STRING )
                    // InternalModel2Blockly.g:317:6: lv_label_3_0= RULE_STRING
                    {
                    lv_label_3_0=(Token)match(input,RULE_STRING,FOLLOW_14); 

                    						newLeafNode(lv_label_3_0, grammarAccess.getCategoryDefAccess().getLabelSTRINGTerminalRuleCall_2_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getCategoryDefRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"label",
                    							lv_label_3_0,
                    							"org.eclipse.xtext.common.Terminals.STRING");
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalModel2Blockly.g:334:3: (otherlv_4= 'colour' ( (lv_colour_5_0= RULE_INT ) ) )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==17) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // InternalModel2Blockly.g:335:4: otherlv_4= 'colour' ( (lv_colour_5_0= RULE_INT ) )
                    {
                    otherlv_4=(Token)match(input,17,FOLLOW_15); 

                    				newLeafNode(otherlv_4, grammarAccess.getCategoryDefAccess().getColourKeyword_3_0());
                    			
                    // InternalModel2Blockly.g:339:4: ( (lv_colour_5_0= RULE_INT ) )
                    // InternalModel2Blockly.g:340:5: (lv_colour_5_0= RULE_INT )
                    {
                    // InternalModel2Blockly.g:340:5: (lv_colour_5_0= RULE_INT )
                    // InternalModel2Blockly.g:341:6: lv_colour_5_0= RULE_INT
                    {
                    lv_colour_5_0=(Token)match(input,RULE_INT,FOLLOW_16); 

                    						newLeafNode(lv_colour_5_0, grammarAccess.getCategoryDefAccess().getColourINTTerminalRuleCall_3_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getCategoryDefRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"colour",
                    							lv_colour_5_0,
                    							"org.eclipse.xtext.common.Terminals.INT");
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalModel2Blockly.g:358:3: (otherlv_6= '{' ( (lv_subcategories_7_0= ruleCategoryDef ) )* otherlv_8= '}' )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==18) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // InternalModel2Blockly.g:359:4: otherlv_6= '{' ( (lv_subcategories_7_0= ruleCategoryDef ) )* otherlv_8= '}'
                    {
                    otherlv_6=(Token)match(input,18,FOLLOW_17); 

                    				newLeafNode(otherlv_6, grammarAccess.getCategoryDefAccess().getLeftCurlyBracketKeyword_4_0());
                    			
                    // InternalModel2Blockly.g:363:4: ( (lv_subcategories_7_0= ruleCategoryDef ) )*
                    loop11:
                    do {
                        int alt11=2;
                        int LA11_0 = input.LA(1);

                        if ( (LA11_0==15) ) {
                            alt11=1;
                        }


                        switch (alt11) {
                    	case 1 :
                    	    // InternalModel2Blockly.g:364:5: (lv_subcategories_7_0= ruleCategoryDef )
                    	    {
                    	    // InternalModel2Blockly.g:364:5: (lv_subcategories_7_0= ruleCategoryDef )
                    	    // InternalModel2Blockly.g:365:6: lv_subcategories_7_0= ruleCategoryDef
                    	    {

                    	    						newCompositeNode(grammarAccess.getCategoryDefAccess().getSubcategoriesCategoryDefParserRuleCall_4_1_0());
                    	    					
                    	    pushFollow(FOLLOW_17);
                    	    lv_subcategories_7_0=ruleCategoryDef();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getCategoryDefRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"subcategories",
                    	    							lv_subcategories_7_0,
                    	    							"io.github.plortinus.model2blockly.Model2Blockly.CategoryDef");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop11;
                        }
                    } while (true);

                    otherlv_8=(Token)match(input,19,FOLLOW_2); 

                    				newLeafNode(otherlv_8, grammarAccess.getCategoryDefAccess().getRightCurlyBracketKeyword_4_2());
                    			

                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleCategoryDef"


    // $ANTLR start "entryRuleClassDef"
    // InternalModel2Blockly.g:391:1: entryRuleClassDef returns [EObject current=null] : iv_ruleClassDef= ruleClassDef EOF ;
    public final EObject entryRuleClassDef() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleClassDef = null;


        try {
            // InternalModel2Blockly.g:391:49: (iv_ruleClassDef= ruleClassDef EOF )
            // InternalModel2Blockly.g:392:2: iv_ruleClassDef= ruleClassDef EOF
            {
             newCompositeNode(grammarAccess.getClassDefRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleClassDef=ruleClassDef();

            state._fsp--;

             current =iv_ruleClassDef; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleClassDef"


    // $ANTLR start "ruleClassDef"
    // InternalModel2Blockly.g:398:1: ruleClassDef returns [EObject current=null] : ( ( (lv_abstract_0_0= 'abstract' ) )? ( ( (lv_output_1_0= 'output' ) ) (otherlv_2= 'as' ( (otherlv_3= RULE_ID ) ) )? )? otherlv_4= 'class' ( (lv_name_5_0= RULE_ID ) ) (otherlv_6= 'extends' ( (otherlv_7= RULE_ID ) ) )? (otherlv_8= 'category' ( (otherlv_9= RULE_ID ) ) )? (otherlv_10= 'colour' ( (lv_colour_11_0= RULE_INT ) ) )? (otherlv_12= 'label' ( (lv_label_13_0= RULE_STRING ) ) )? (otherlv_14= 'message0' ( (lv_message0_15_0= RULE_STRING ) ) )? (otherlv_16= 'tooltip' ( (lv_tooltip_17_0= RULE_STRING ) ) )? (otherlv_18= 'helpUrl' ( (lv_helpUrl_19_0= RULE_STRING ) ) )? (otherlv_20= 'inputsInline' ( (lv_inputsInline_21_0= ruleBoolVal ) ) )? ( (lv_inline_22_0= 'inline' ) )? (otherlv_23= 'code' ( (lv_codeTemplate_24_0= RULE_STRING ) ) )? otherlv_25= '{' ( (lv_features_26_0= ruleFeature ) )* otherlv_27= '}' ) ;
    public final EObject ruleClassDef() throws RecognitionException {
        EObject current = null;

        Token lv_abstract_0_0=null;
        Token lv_output_1_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token lv_name_5_0=null;
        Token otherlv_6=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        Token otherlv_10=null;
        Token lv_colour_11_0=null;
        Token otherlv_12=null;
        Token lv_label_13_0=null;
        Token otherlv_14=null;
        Token lv_message0_15_0=null;
        Token otherlv_16=null;
        Token lv_tooltip_17_0=null;
        Token otherlv_18=null;
        Token lv_helpUrl_19_0=null;
        Token otherlv_20=null;
        Token lv_inline_22_0=null;
        Token otherlv_23=null;
        Token lv_codeTemplate_24_0=null;
        Token otherlv_25=null;
        Token otherlv_27=null;
        Enumerator lv_inputsInline_21_0 = null;

        EObject lv_features_26_0 = null;



        	enterRule();

        try {
            // InternalModel2Blockly.g:404:2: ( ( ( (lv_abstract_0_0= 'abstract' ) )? ( ( (lv_output_1_0= 'output' ) ) (otherlv_2= 'as' ( (otherlv_3= RULE_ID ) ) )? )? otherlv_4= 'class' ( (lv_name_5_0= RULE_ID ) ) (otherlv_6= 'extends' ( (otherlv_7= RULE_ID ) ) )? (otherlv_8= 'category' ( (otherlv_9= RULE_ID ) ) )? (otherlv_10= 'colour' ( (lv_colour_11_0= RULE_INT ) ) )? (otherlv_12= 'label' ( (lv_label_13_0= RULE_STRING ) ) )? (otherlv_14= 'message0' ( (lv_message0_15_0= RULE_STRING ) ) )? (otherlv_16= 'tooltip' ( (lv_tooltip_17_0= RULE_STRING ) ) )? (otherlv_18= 'helpUrl' ( (lv_helpUrl_19_0= RULE_STRING ) ) )? (otherlv_20= 'inputsInline' ( (lv_inputsInline_21_0= ruleBoolVal ) ) )? ( (lv_inline_22_0= 'inline' ) )? (otherlv_23= 'code' ( (lv_codeTemplate_24_0= RULE_STRING ) ) )? otherlv_25= '{' ( (lv_features_26_0= ruleFeature ) )* otherlv_27= '}' ) )
            // InternalModel2Blockly.g:405:2: ( ( (lv_abstract_0_0= 'abstract' ) )? ( ( (lv_output_1_0= 'output' ) ) (otherlv_2= 'as' ( (otherlv_3= RULE_ID ) ) )? )? otherlv_4= 'class' ( (lv_name_5_0= RULE_ID ) ) (otherlv_6= 'extends' ( (otherlv_7= RULE_ID ) ) )? (otherlv_8= 'category' ( (otherlv_9= RULE_ID ) ) )? (otherlv_10= 'colour' ( (lv_colour_11_0= RULE_INT ) ) )? (otherlv_12= 'label' ( (lv_label_13_0= RULE_STRING ) ) )? (otherlv_14= 'message0' ( (lv_message0_15_0= RULE_STRING ) ) )? (otherlv_16= 'tooltip' ( (lv_tooltip_17_0= RULE_STRING ) ) )? (otherlv_18= 'helpUrl' ( (lv_helpUrl_19_0= RULE_STRING ) ) )? (otherlv_20= 'inputsInline' ( (lv_inputsInline_21_0= ruleBoolVal ) ) )? ( (lv_inline_22_0= 'inline' ) )? (otherlv_23= 'code' ( (lv_codeTemplate_24_0= RULE_STRING ) ) )? otherlv_25= '{' ( (lv_features_26_0= ruleFeature ) )* otherlv_27= '}' )
            {
            // InternalModel2Blockly.g:405:2: ( ( (lv_abstract_0_0= 'abstract' ) )? ( ( (lv_output_1_0= 'output' ) ) (otherlv_2= 'as' ( (otherlv_3= RULE_ID ) ) )? )? otherlv_4= 'class' ( (lv_name_5_0= RULE_ID ) ) (otherlv_6= 'extends' ( (otherlv_7= RULE_ID ) ) )? (otherlv_8= 'category' ( (otherlv_9= RULE_ID ) ) )? (otherlv_10= 'colour' ( (lv_colour_11_0= RULE_INT ) ) )? (otherlv_12= 'label' ( (lv_label_13_0= RULE_STRING ) ) )? (otherlv_14= 'message0' ( (lv_message0_15_0= RULE_STRING ) ) )? (otherlv_16= 'tooltip' ( (lv_tooltip_17_0= RULE_STRING ) ) )? (otherlv_18= 'helpUrl' ( (lv_helpUrl_19_0= RULE_STRING ) ) )? (otherlv_20= 'inputsInline' ( (lv_inputsInline_21_0= ruleBoolVal ) ) )? ( (lv_inline_22_0= 'inline' ) )? (otherlv_23= 'code' ( (lv_codeTemplate_24_0= RULE_STRING ) ) )? otherlv_25= '{' ( (lv_features_26_0= ruleFeature ) )* otherlv_27= '}' )
            // InternalModel2Blockly.g:406:3: ( (lv_abstract_0_0= 'abstract' ) )? ( ( (lv_output_1_0= 'output' ) ) (otherlv_2= 'as' ( (otherlv_3= RULE_ID ) ) )? )? otherlv_4= 'class' ( (lv_name_5_0= RULE_ID ) ) (otherlv_6= 'extends' ( (otherlv_7= RULE_ID ) ) )? (otherlv_8= 'category' ( (otherlv_9= RULE_ID ) ) )? (otherlv_10= 'colour' ( (lv_colour_11_0= RULE_INT ) ) )? (otherlv_12= 'label' ( (lv_label_13_0= RULE_STRING ) ) )? (otherlv_14= 'message0' ( (lv_message0_15_0= RULE_STRING ) ) )? (otherlv_16= 'tooltip' ( (lv_tooltip_17_0= RULE_STRING ) ) )? (otherlv_18= 'helpUrl' ( (lv_helpUrl_19_0= RULE_STRING ) ) )? (otherlv_20= 'inputsInline' ( (lv_inputsInline_21_0= ruleBoolVal ) ) )? ( (lv_inline_22_0= 'inline' ) )? (otherlv_23= 'code' ( (lv_codeTemplate_24_0= RULE_STRING ) ) )? otherlv_25= '{' ( (lv_features_26_0= ruleFeature ) )* otherlv_27= '}'
            {
            // InternalModel2Blockly.g:406:3: ( (lv_abstract_0_0= 'abstract' ) )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==20) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // InternalModel2Blockly.g:407:4: (lv_abstract_0_0= 'abstract' )
                    {
                    // InternalModel2Blockly.g:407:4: (lv_abstract_0_0= 'abstract' )
                    // InternalModel2Blockly.g:408:5: lv_abstract_0_0= 'abstract'
                    {
                    lv_abstract_0_0=(Token)match(input,20,FOLLOW_18); 

                    					newLeafNode(lv_abstract_0_0, grammarAccess.getClassDefAccess().getAbstractAbstractKeyword_0_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getClassDefRule());
                    					}
                    					setWithLastConsumed(current, "abstract", lv_abstract_0_0 != null, "abstract");
                    				

                    }


                    }
                    break;

            }

            // InternalModel2Blockly.g:420:3: ( ( (lv_output_1_0= 'output' ) ) (otherlv_2= 'as' ( (otherlv_3= RULE_ID ) ) )? )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==21) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // InternalModel2Blockly.g:421:4: ( (lv_output_1_0= 'output' ) ) (otherlv_2= 'as' ( (otherlv_3= RULE_ID ) ) )?
                    {
                    // InternalModel2Blockly.g:421:4: ( (lv_output_1_0= 'output' ) )
                    // InternalModel2Blockly.g:422:5: (lv_output_1_0= 'output' )
                    {
                    // InternalModel2Blockly.g:422:5: (lv_output_1_0= 'output' )
                    // InternalModel2Blockly.g:423:6: lv_output_1_0= 'output'
                    {
                    lv_output_1_0=(Token)match(input,21,FOLLOW_19); 

                    						newLeafNode(lv_output_1_0, grammarAccess.getClassDefAccess().getOutputOutputKeyword_1_0_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getClassDefRule());
                    						}
                    						setWithLastConsumed(current, "output", lv_output_1_0 != null, "output");
                    					

                    }


                    }

                    // InternalModel2Blockly.g:435:4: (otherlv_2= 'as' ( (otherlv_3= RULE_ID ) ) )?
                    int alt14=2;
                    int LA14_0 = input.LA(1);

                    if ( (LA14_0==22) ) {
                        alt14=1;
                    }
                    switch (alt14) {
                        case 1 :
                            // InternalModel2Blockly.g:436:5: otherlv_2= 'as' ( (otherlv_3= RULE_ID ) )
                            {
                            otherlv_2=(Token)match(input,22,FOLLOW_3); 

                            					newLeafNode(otherlv_2, grammarAccess.getClassDefAccess().getAsKeyword_1_1_0());
                            				
                            // InternalModel2Blockly.g:440:5: ( (otherlv_3= RULE_ID ) )
                            // InternalModel2Blockly.g:441:6: (otherlv_3= RULE_ID )
                            {
                            // InternalModel2Blockly.g:441:6: (otherlv_3= RULE_ID )
                            // InternalModel2Blockly.g:442:7: otherlv_3= RULE_ID
                            {

                            							if (current==null) {
                            								current = createModelElement(grammarAccess.getClassDefRule());
                            							}
                            						
                            otherlv_3=(Token)match(input,RULE_ID,FOLLOW_20); 

                            							newLeafNode(otherlv_3, grammarAccess.getClassDefAccess().getOutputTypeClassDefCrossReference_1_1_1_0());
                            						

                            }


                            }


                            }
                            break;

                    }


                    }
                    break;

            }

            otherlv_4=(Token)match(input,23,FOLLOW_3); 

            			newLeafNode(otherlv_4, grammarAccess.getClassDefAccess().getClassKeyword_2());
            		
            // InternalModel2Blockly.g:459:3: ( (lv_name_5_0= RULE_ID ) )
            // InternalModel2Blockly.g:460:4: (lv_name_5_0= RULE_ID )
            {
            // InternalModel2Blockly.g:460:4: (lv_name_5_0= RULE_ID )
            // InternalModel2Blockly.g:461:5: lv_name_5_0= RULE_ID
            {
            lv_name_5_0=(Token)match(input,RULE_ID,FOLLOW_21); 

            					newLeafNode(lv_name_5_0, grammarAccess.getClassDefAccess().getNameIDTerminalRuleCall_3_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getClassDefRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_5_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            // InternalModel2Blockly.g:477:3: (otherlv_6= 'extends' ( (otherlv_7= RULE_ID ) ) )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==24) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // InternalModel2Blockly.g:478:4: otherlv_6= 'extends' ( (otherlv_7= RULE_ID ) )
                    {
                    otherlv_6=(Token)match(input,24,FOLLOW_3); 

                    				newLeafNode(otherlv_6, grammarAccess.getClassDefAccess().getExtendsKeyword_4_0());
                    			
                    // InternalModel2Blockly.g:482:4: ( (otherlv_7= RULE_ID ) )
                    // InternalModel2Blockly.g:483:5: (otherlv_7= RULE_ID )
                    {
                    // InternalModel2Blockly.g:483:5: (otherlv_7= RULE_ID )
                    // InternalModel2Blockly.g:484:6: otherlv_7= RULE_ID
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getClassDefRule());
                    						}
                    					
                    otherlv_7=(Token)match(input,RULE_ID,FOLLOW_22); 

                    						newLeafNode(otherlv_7, grammarAccess.getClassDefAccess().getSuperClassClassDefCrossReference_4_1_0());
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalModel2Blockly.g:496:3: (otherlv_8= 'category' ( (otherlv_9= RULE_ID ) ) )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==15) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // InternalModel2Blockly.g:497:4: otherlv_8= 'category' ( (otherlv_9= RULE_ID ) )
                    {
                    otherlv_8=(Token)match(input,15,FOLLOW_3); 

                    				newLeafNode(otherlv_8, grammarAccess.getClassDefAccess().getCategoryKeyword_5_0());
                    			
                    // InternalModel2Blockly.g:501:4: ( (otherlv_9= RULE_ID ) )
                    // InternalModel2Blockly.g:502:5: (otherlv_9= RULE_ID )
                    {
                    // InternalModel2Blockly.g:502:5: (otherlv_9= RULE_ID )
                    // InternalModel2Blockly.g:503:6: otherlv_9= RULE_ID
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getClassDefRule());
                    						}
                    					
                    otherlv_9=(Token)match(input,RULE_ID,FOLLOW_23); 

                    						newLeafNode(otherlv_9, grammarAccess.getClassDefAccess().getCategoryCategoryDefCrossReference_5_1_0());
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalModel2Blockly.g:515:3: (otherlv_10= 'colour' ( (lv_colour_11_0= RULE_INT ) ) )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==17) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // InternalModel2Blockly.g:516:4: otherlv_10= 'colour' ( (lv_colour_11_0= RULE_INT ) )
                    {
                    otherlv_10=(Token)match(input,17,FOLLOW_15); 

                    				newLeafNode(otherlv_10, grammarAccess.getClassDefAccess().getColourKeyword_6_0());
                    			
                    // InternalModel2Blockly.g:520:4: ( (lv_colour_11_0= RULE_INT ) )
                    // InternalModel2Blockly.g:521:5: (lv_colour_11_0= RULE_INT )
                    {
                    // InternalModel2Blockly.g:521:5: (lv_colour_11_0= RULE_INT )
                    // InternalModel2Blockly.g:522:6: lv_colour_11_0= RULE_INT
                    {
                    lv_colour_11_0=(Token)match(input,RULE_INT,FOLLOW_24); 

                    						newLeafNode(lv_colour_11_0, grammarAccess.getClassDefAccess().getColourINTTerminalRuleCall_6_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getClassDefRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"colour",
                    							lv_colour_11_0,
                    							"org.eclipse.xtext.common.Terminals.INT");
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalModel2Blockly.g:539:3: (otherlv_12= 'label' ( (lv_label_13_0= RULE_STRING ) ) )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==16) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // InternalModel2Blockly.g:540:4: otherlv_12= 'label' ( (lv_label_13_0= RULE_STRING ) )
                    {
                    otherlv_12=(Token)match(input,16,FOLLOW_5); 

                    				newLeafNode(otherlv_12, grammarAccess.getClassDefAccess().getLabelKeyword_7_0());
                    			
                    // InternalModel2Blockly.g:544:4: ( (lv_label_13_0= RULE_STRING ) )
                    // InternalModel2Blockly.g:545:5: (lv_label_13_0= RULE_STRING )
                    {
                    // InternalModel2Blockly.g:545:5: (lv_label_13_0= RULE_STRING )
                    // InternalModel2Blockly.g:546:6: lv_label_13_0= RULE_STRING
                    {
                    lv_label_13_0=(Token)match(input,RULE_STRING,FOLLOW_25); 

                    						newLeafNode(lv_label_13_0, grammarAccess.getClassDefAccess().getLabelSTRINGTerminalRuleCall_7_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getClassDefRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"label",
                    							lv_label_13_0,
                    							"org.eclipse.xtext.common.Terminals.STRING");
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalModel2Blockly.g:563:3: (otherlv_14= 'message0' ( (lv_message0_15_0= RULE_STRING ) ) )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==25) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // InternalModel2Blockly.g:564:4: otherlv_14= 'message0' ( (lv_message0_15_0= RULE_STRING ) )
                    {
                    otherlv_14=(Token)match(input,25,FOLLOW_5); 

                    				newLeafNode(otherlv_14, grammarAccess.getClassDefAccess().getMessage0Keyword_8_0());
                    			
                    // InternalModel2Blockly.g:568:4: ( (lv_message0_15_0= RULE_STRING ) )
                    // InternalModel2Blockly.g:569:5: (lv_message0_15_0= RULE_STRING )
                    {
                    // InternalModel2Blockly.g:569:5: (lv_message0_15_0= RULE_STRING )
                    // InternalModel2Blockly.g:570:6: lv_message0_15_0= RULE_STRING
                    {
                    lv_message0_15_0=(Token)match(input,RULE_STRING,FOLLOW_26); 

                    						newLeafNode(lv_message0_15_0, grammarAccess.getClassDefAccess().getMessage0STRINGTerminalRuleCall_8_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getClassDefRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"message0",
                    							lv_message0_15_0,
                    							"org.eclipse.xtext.common.Terminals.STRING");
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalModel2Blockly.g:587:3: (otherlv_16= 'tooltip' ( (lv_tooltip_17_0= RULE_STRING ) ) )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==26) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // InternalModel2Blockly.g:588:4: otherlv_16= 'tooltip' ( (lv_tooltip_17_0= RULE_STRING ) )
                    {
                    otherlv_16=(Token)match(input,26,FOLLOW_5); 

                    				newLeafNode(otherlv_16, grammarAccess.getClassDefAccess().getTooltipKeyword_9_0());
                    			
                    // InternalModel2Blockly.g:592:4: ( (lv_tooltip_17_0= RULE_STRING ) )
                    // InternalModel2Blockly.g:593:5: (lv_tooltip_17_0= RULE_STRING )
                    {
                    // InternalModel2Blockly.g:593:5: (lv_tooltip_17_0= RULE_STRING )
                    // InternalModel2Blockly.g:594:6: lv_tooltip_17_0= RULE_STRING
                    {
                    lv_tooltip_17_0=(Token)match(input,RULE_STRING,FOLLOW_27); 

                    						newLeafNode(lv_tooltip_17_0, grammarAccess.getClassDefAccess().getTooltipSTRINGTerminalRuleCall_9_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getClassDefRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"tooltip",
                    							lv_tooltip_17_0,
                    							"org.eclipse.xtext.common.Terminals.STRING");
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalModel2Blockly.g:611:3: (otherlv_18= 'helpUrl' ( (lv_helpUrl_19_0= RULE_STRING ) ) )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==27) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // InternalModel2Blockly.g:612:4: otherlv_18= 'helpUrl' ( (lv_helpUrl_19_0= RULE_STRING ) )
                    {
                    otherlv_18=(Token)match(input,27,FOLLOW_5); 

                    				newLeafNode(otherlv_18, grammarAccess.getClassDefAccess().getHelpUrlKeyword_10_0());
                    			
                    // InternalModel2Blockly.g:616:4: ( (lv_helpUrl_19_0= RULE_STRING ) )
                    // InternalModel2Blockly.g:617:5: (lv_helpUrl_19_0= RULE_STRING )
                    {
                    // InternalModel2Blockly.g:617:5: (lv_helpUrl_19_0= RULE_STRING )
                    // InternalModel2Blockly.g:618:6: lv_helpUrl_19_0= RULE_STRING
                    {
                    lv_helpUrl_19_0=(Token)match(input,RULE_STRING,FOLLOW_28); 

                    						newLeafNode(lv_helpUrl_19_0, grammarAccess.getClassDefAccess().getHelpUrlSTRINGTerminalRuleCall_10_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getClassDefRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"helpUrl",
                    							lv_helpUrl_19_0,
                    							"org.eclipse.xtext.common.Terminals.STRING");
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalModel2Blockly.g:635:3: (otherlv_20= 'inputsInline' ( (lv_inputsInline_21_0= ruleBoolVal ) ) )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==28) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // InternalModel2Blockly.g:636:4: otherlv_20= 'inputsInline' ( (lv_inputsInline_21_0= ruleBoolVal ) )
                    {
                    otherlv_20=(Token)match(input,28,FOLLOW_29); 

                    				newLeafNode(otherlv_20, grammarAccess.getClassDefAccess().getInputsInlineKeyword_11_0());
                    			
                    // InternalModel2Blockly.g:640:4: ( (lv_inputsInline_21_0= ruleBoolVal ) )
                    // InternalModel2Blockly.g:641:5: (lv_inputsInline_21_0= ruleBoolVal )
                    {
                    // InternalModel2Blockly.g:641:5: (lv_inputsInline_21_0= ruleBoolVal )
                    // InternalModel2Blockly.g:642:6: lv_inputsInline_21_0= ruleBoolVal
                    {

                    						newCompositeNode(grammarAccess.getClassDefAccess().getInputsInlineBoolValEnumRuleCall_11_1_0());
                    					
                    pushFollow(FOLLOW_30);
                    lv_inputsInline_21_0=ruleBoolVal();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getClassDefRule());
                    						}
                    						set(
                    							current,
                    							"inputsInline",
                    							lv_inputsInline_21_0,
                    							"io.github.plortinus.model2blockly.Model2Blockly.BoolVal");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalModel2Blockly.g:660:3: ( (lv_inline_22_0= 'inline' ) )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==29) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // InternalModel2Blockly.g:661:4: (lv_inline_22_0= 'inline' )
                    {
                    // InternalModel2Blockly.g:661:4: (lv_inline_22_0= 'inline' )
                    // InternalModel2Blockly.g:662:5: lv_inline_22_0= 'inline'
                    {
                    lv_inline_22_0=(Token)match(input,29,FOLLOW_31); 

                    					newLeafNode(lv_inline_22_0, grammarAccess.getClassDefAccess().getInlineInlineKeyword_12_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getClassDefRule());
                    					}
                    					setWithLastConsumed(current, "inline", lv_inline_22_0 != null, "inline");
                    				

                    }


                    }
                    break;

            }

            // InternalModel2Blockly.g:674:3: (otherlv_23= 'code' ( (lv_codeTemplate_24_0= RULE_STRING ) ) )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==30) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // InternalModel2Blockly.g:675:4: otherlv_23= 'code' ( (lv_codeTemplate_24_0= RULE_STRING ) )
                    {
                    otherlv_23=(Token)match(input,30,FOLLOW_5); 

                    				newLeafNode(otherlv_23, grammarAccess.getClassDefAccess().getCodeKeyword_13_0());
                    			
                    // InternalModel2Blockly.g:679:4: ( (lv_codeTemplate_24_0= RULE_STRING ) )
                    // InternalModel2Blockly.g:680:5: (lv_codeTemplate_24_0= RULE_STRING )
                    {
                    // InternalModel2Blockly.g:680:5: (lv_codeTemplate_24_0= RULE_STRING )
                    // InternalModel2Blockly.g:681:6: lv_codeTemplate_24_0= RULE_STRING
                    {
                    lv_codeTemplate_24_0=(Token)match(input,RULE_STRING,FOLLOW_32); 

                    						newLeafNode(lv_codeTemplate_24_0, grammarAccess.getClassDefAccess().getCodeTemplateSTRINGTerminalRuleCall_13_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getClassDefRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"codeTemplate",
                    							lv_codeTemplate_24_0,
                    							"org.eclipse.xtext.common.Terminals.STRING");
                    					

                    }


                    }


                    }
                    break;

            }

            otherlv_25=(Token)match(input,18,FOLLOW_33); 

            			newLeafNode(otherlv_25, grammarAccess.getClassDefAccess().getLeftCurlyBracketKeyword_14());
            		
            // InternalModel2Blockly.g:702:3: ( (lv_features_26_0= ruleFeature ) )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( (LA26_0==31||LA26_0==46||LA26_0==50||LA26_0==52) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // InternalModel2Blockly.g:703:4: (lv_features_26_0= ruleFeature )
            	    {
            	    // InternalModel2Blockly.g:703:4: (lv_features_26_0= ruleFeature )
            	    // InternalModel2Blockly.g:704:5: lv_features_26_0= ruleFeature
            	    {

            	    					newCompositeNode(grammarAccess.getClassDefAccess().getFeaturesFeatureParserRuleCall_15_0());
            	    				
            	    pushFollow(FOLLOW_33);
            	    lv_features_26_0=ruleFeature();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getClassDefRule());
            	    					}
            	    					add(
            	    						current,
            	    						"features",
            	    						lv_features_26_0,
            	    						"io.github.plortinus.model2blockly.Model2Blockly.Feature");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop26;
                }
            } while (true);

            otherlv_27=(Token)match(input,19,FOLLOW_2); 

            			newLeafNode(otherlv_27, grammarAccess.getClassDefAccess().getRightCurlyBracketKeyword_16());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleClassDef"


    // $ANTLR start "entryRuleFeature"
    // InternalModel2Blockly.g:729:1: entryRuleFeature returns [EObject current=null] : iv_ruleFeature= ruleFeature EOF ;
    public final EObject entryRuleFeature() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFeature = null;


        try {
            // InternalModel2Blockly.g:729:48: (iv_ruleFeature= ruleFeature EOF )
            // InternalModel2Blockly.g:730:2: iv_ruleFeature= ruleFeature EOF
            {
             newCompositeNode(grammarAccess.getFeatureRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFeature=ruleFeature();

            state._fsp--;

             current =iv_ruleFeature; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFeature"


    // $ANTLR start "ruleFeature"
    // InternalModel2Blockly.g:736:1: ruleFeature returns [EObject current=null] : (this_Attribute_0= ruleAttribute | this_Containment_1= ruleContainment | this_Reference_2= ruleReference | this_ValueInput_3= ruleValueInput ) ;
    public final EObject ruleFeature() throws RecognitionException {
        EObject current = null;

        EObject this_Attribute_0 = null;

        EObject this_Containment_1 = null;

        EObject this_Reference_2 = null;

        EObject this_ValueInput_3 = null;



        	enterRule();

        try {
            // InternalModel2Blockly.g:742:2: ( (this_Attribute_0= ruleAttribute | this_Containment_1= ruleContainment | this_Reference_2= ruleReference | this_ValueInput_3= ruleValueInput ) )
            // InternalModel2Blockly.g:743:2: (this_Attribute_0= ruleAttribute | this_Containment_1= ruleContainment | this_Reference_2= ruleReference | this_ValueInput_3= ruleValueInput )
            {
            // InternalModel2Blockly.g:743:2: (this_Attribute_0= ruleAttribute | this_Containment_1= ruleContainment | this_Reference_2= ruleReference | this_ValueInput_3= ruleValueInput )
            int alt27=4;
            switch ( input.LA(1) ) {
            case 31:
                {
                alt27=1;
                }
                break;
            case 46:
                {
                alt27=2;
                }
                break;
            case 50:
                {
                alt27=3;
                }
                break;
            case 52:
                {
                alt27=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 27, 0, input);

                throw nvae;
            }

            switch (alt27) {
                case 1 :
                    // InternalModel2Blockly.g:744:3: this_Attribute_0= ruleAttribute
                    {

                    			newCompositeNode(grammarAccess.getFeatureAccess().getAttributeParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_Attribute_0=ruleAttribute();

                    state._fsp--;


                    			current = this_Attribute_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalModel2Blockly.g:753:3: this_Containment_1= ruleContainment
                    {

                    			newCompositeNode(grammarAccess.getFeatureAccess().getContainmentParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_Containment_1=ruleContainment();

                    state._fsp--;


                    			current = this_Containment_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 3 :
                    // InternalModel2Blockly.g:762:3: this_Reference_2= ruleReference
                    {

                    			newCompositeNode(grammarAccess.getFeatureAccess().getReferenceParserRuleCall_2());
                    		
                    pushFollow(FOLLOW_2);
                    this_Reference_2=ruleReference();

                    state._fsp--;


                    			current = this_Reference_2;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 4 :
                    // InternalModel2Blockly.g:771:3: this_ValueInput_3= ruleValueInput
                    {

                    			newCompositeNode(grammarAccess.getFeatureAccess().getValueInputParserRuleCall_3());
                    		
                    pushFollow(FOLLOW_2);
                    this_ValueInput_3=ruleValueInput();

                    state._fsp--;


                    			current = this_ValueInput_3;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFeature"


    // $ANTLR start "entryRuleAttribute"
    // InternalModel2Blockly.g:783:1: entryRuleAttribute returns [EObject current=null] : iv_ruleAttribute= ruleAttribute EOF ;
    public final EObject entryRuleAttribute() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAttribute = null;


        try {
            // InternalModel2Blockly.g:783:50: (iv_ruleAttribute= ruleAttribute EOF )
            // InternalModel2Blockly.g:784:2: iv_ruleAttribute= ruleAttribute EOF
            {
             newCompositeNode(grammarAccess.getAttributeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAttribute=ruleAttribute();

            state._fsp--;

             current =iv_ruleAttribute; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAttribute"


    // $ANTLR start "ruleAttribute"
    // InternalModel2Blockly.g:790:1: ruleAttribute returns [EObject current=null] : (otherlv_0= 'attribute' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ':' ( (lv_type_3_0= ruleAttributeType ) ) ( (lv_cardinality_4_0= ruleCardinality ) )? (otherlv_5= 'default' ( (lv_defaultValue_6_0= RULE_STRING ) ) )? (otherlv_7= 'min' ( (lv_min_8_0= RULE_STRING ) ) )? (otherlv_9= 'max' ( (lv_max_10_0= RULE_STRING ) ) )? (otherlv_11= 'src' ( (lv_imageUrl_12_0= RULE_STRING ) ) )? (otherlv_13= 'width' ( (lv_imageWidth_14_0= RULE_INT ) ) )? (otherlv_15= 'height' ( (lv_imageHeight_16_0= RULE_INT ) ) )? (otherlv_17= 'alt' ( (lv_imageAlt_18_0= RULE_STRING ) ) )? ( (lv_required_19_0= 'required' ) )? ( (lv_id_20_0= 'modelId' ) )? ( (lv_unique_21_0= 'unique' ) )? ( (lv_nonUnique_22_0= 'nonUnique' ) )? ( (lv_ordered_23_0= 'ordered' ) )? ( (lv_unordered_24_0= 'unordered' ) )? ( (lv_ui_25_0= ruleUiOptions ) )? ) ;
    public final EObject ruleAttribute() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_2=null;
        Token otherlv_5=null;
        Token lv_defaultValue_6_0=null;
        Token otherlv_7=null;
        Token lv_min_8_0=null;
        Token otherlv_9=null;
        Token lv_max_10_0=null;
        Token otherlv_11=null;
        Token lv_imageUrl_12_0=null;
        Token otherlv_13=null;
        Token lv_imageWidth_14_0=null;
        Token otherlv_15=null;
        Token lv_imageHeight_16_0=null;
        Token otherlv_17=null;
        Token lv_imageAlt_18_0=null;
        Token lv_required_19_0=null;
        Token lv_id_20_0=null;
        Token lv_unique_21_0=null;
        Token lv_nonUnique_22_0=null;
        Token lv_ordered_23_0=null;
        Token lv_unordered_24_0=null;
        EObject lv_type_3_0 = null;

        EObject lv_cardinality_4_0 = null;

        EObject lv_ui_25_0 = null;



        	enterRule();

        try {
            // InternalModel2Blockly.g:796:2: ( (otherlv_0= 'attribute' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ':' ( (lv_type_3_0= ruleAttributeType ) ) ( (lv_cardinality_4_0= ruleCardinality ) )? (otherlv_5= 'default' ( (lv_defaultValue_6_0= RULE_STRING ) ) )? (otherlv_7= 'min' ( (lv_min_8_0= RULE_STRING ) ) )? (otherlv_9= 'max' ( (lv_max_10_0= RULE_STRING ) ) )? (otherlv_11= 'src' ( (lv_imageUrl_12_0= RULE_STRING ) ) )? (otherlv_13= 'width' ( (lv_imageWidth_14_0= RULE_INT ) ) )? (otherlv_15= 'height' ( (lv_imageHeight_16_0= RULE_INT ) ) )? (otherlv_17= 'alt' ( (lv_imageAlt_18_0= RULE_STRING ) ) )? ( (lv_required_19_0= 'required' ) )? ( (lv_id_20_0= 'modelId' ) )? ( (lv_unique_21_0= 'unique' ) )? ( (lv_nonUnique_22_0= 'nonUnique' ) )? ( (lv_ordered_23_0= 'ordered' ) )? ( (lv_unordered_24_0= 'unordered' ) )? ( (lv_ui_25_0= ruleUiOptions ) )? ) )
            // InternalModel2Blockly.g:797:2: (otherlv_0= 'attribute' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ':' ( (lv_type_3_0= ruleAttributeType ) ) ( (lv_cardinality_4_0= ruleCardinality ) )? (otherlv_5= 'default' ( (lv_defaultValue_6_0= RULE_STRING ) ) )? (otherlv_7= 'min' ( (lv_min_8_0= RULE_STRING ) ) )? (otherlv_9= 'max' ( (lv_max_10_0= RULE_STRING ) ) )? (otherlv_11= 'src' ( (lv_imageUrl_12_0= RULE_STRING ) ) )? (otherlv_13= 'width' ( (lv_imageWidth_14_0= RULE_INT ) ) )? (otherlv_15= 'height' ( (lv_imageHeight_16_0= RULE_INT ) ) )? (otherlv_17= 'alt' ( (lv_imageAlt_18_0= RULE_STRING ) ) )? ( (lv_required_19_0= 'required' ) )? ( (lv_id_20_0= 'modelId' ) )? ( (lv_unique_21_0= 'unique' ) )? ( (lv_nonUnique_22_0= 'nonUnique' ) )? ( (lv_ordered_23_0= 'ordered' ) )? ( (lv_unordered_24_0= 'unordered' ) )? ( (lv_ui_25_0= ruleUiOptions ) )? )
            {
            // InternalModel2Blockly.g:797:2: (otherlv_0= 'attribute' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ':' ( (lv_type_3_0= ruleAttributeType ) ) ( (lv_cardinality_4_0= ruleCardinality ) )? (otherlv_5= 'default' ( (lv_defaultValue_6_0= RULE_STRING ) ) )? (otherlv_7= 'min' ( (lv_min_8_0= RULE_STRING ) ) )? (otherlv_9= 'max' ( (lv_max_10_0= RULE_STRING ) ) )? (otherlv_11= 'src' ( (lv_imageUrl_12_0= RULE_STRING ) ) )? (otherlv_13= 'width' ( (lv_imageWidth_14_0= RULE_INT ) ) )? (otherlv_15= 'height' ( (lv_imageHeight_16_0= RULE_INT ) ) )? (otherlv_17= 'alt' ( (lv_imageAlt_18_0= RULE_STRING ) ) )? ( (lv_required_19_0= 'required' ) )? ( (lv_id_20_0= 'modelId' ) )? ( (lv_unique_21_0= 'unique' ) )? ( (lv_nonUnique_22_0= 'nonUnique' ) )? ( (lv_ordered_23_0= 'ordered' ) )? ( (lv_unordered_24_0= 'unordered' ) )? ( (lv_ui_25_0= ruleUiOptions ) )? )
            // InternalModel2Blockly.g:798:3: otherlv_0= 'attribute' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ':' ( (lv_type_3_0= ruleAttributeType ) ) ( (lv_cardinality_4_0= ruleCardinality ) )? (otherlv_5= 'default' ( (lv_defaultValue_6_0= RULE_STRING ) ) )? (otherlv_7= 'min' ( (lv_min_8_0= RULE_STRING ) ) )? (otherlv_9= 'max' ( (lv_max_10_0= RULE_STRING ) ) )? (otherlv_11= 'src' ( (lv_imageUrl_12_0= RULE_STRING ) ) )? (otherlv_13= 'width' ( (lv_imageWidth_14_0= RULE_INT ) ) )? (otherlv_15= 'height' ( (lv_imageHeight_16_0= RULE_INT ) ) )? (otherlv_17= 'alt' ( (lv_imageAlt_18_0= RULE_STRING ) ) )? ( (lv_required_19_0= 'required' ) )? ( (lv_id_20_0= 'modelId' ) )? ( (lv_unique_21_0= 'unique' ) )? ( (lv_nonUnique_22_0= 'nonUnique' ) )? ( (lv_ordered_23_0= 'ordered' ) )? ( (lv_unordered_24_0= 'unordered' ) )? ( (lv_ui_25_0= ruleUiOptions ) )?
            {
            otherlv_0=(Token)match(input,31,FOLLOW_3); 

            			newLeafNode(otherlv_0, grammarAccess.getAttributeAccess().getAttributeKeyword_0());
            		
            // InternalModel2Blockly.g:802:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalModel2Blockly.g:803:4: (lv_name_1_0= RULE_ID )
            {
            // InternalModel2Blockly.g:803:4: (lv_name_1_0= RULE_ID )
            // InternalModel2Blockly.g:804:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_34); 

            					newLeafNode(lv_name_1_0, grammarAccess.getAttributeAccess().getNameIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getAttributeRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_2=(Token)match(input,32,FOLLOW_35); 

            			newLeafNode(otherlv_2, grammarAccess.getAttributeAccess().getColonKeyword_2());
            		
            // InternalModel2Blockly.g:824:3: ( (lv_type_3_0= ruleAttributeType ) )
            // InternalModel2Blockly.g:825:4: (lv_type_3_0= ruleAttributeType )
            {
            // InternalModel2Blockly.g:825:4: (lv_type_3_0= ruleAttributeType )
            // InternalModel2Blockly.g:826:5: lv_type_3_0= ruleAttributeType
            {

            					newCompositeNode(grammarAccess.getAttributeAccess().getTypeAttributeTypeParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_36);
            lv_type_3_0=ruleAttributeType();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getAttributeRule());
            					}
            					set(
            						current,
            						"type",
            						lv_type_3_0,
            						"io.github.plortinus.model2blockly.Model2Blockly.AttributeType");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalModel2Blockly.g:843:3: ( (lv_cardinality_4_0= ruleCardinality ) )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==47) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // InternalModel2Blockly.g:844:4: (lv_cardinality_4_0= ruleCardinality )
                    {
                    // InternalModel2Blockly.g:844:4: (lv_cardinality_4_0= ruleCardinality )
                    // InternalModel2Blockly.g:845:5: lv_cardinality_4_0= ruleCardinality
                    {

                    					newCompositeNode(grammarAccess.getAttributeAccess().getCardinalityCardinalityParserRuleCall_4_0());
                    				
                    pushFollow(FOLLOW_37);
                    lv_cardinality_4_0=ruleCardinality();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getAttributeRule());
                    					}
                    					set(
                    						current,
                    						"cardinality",
                    						lv_cardinality_4_0,
                    						"io.github.plortinus.model2blockly.Model2Blockly.Cardinality");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            // InternalModel2Blockly.g:862:3: (otherlv_5= 'default' ( (lv_defaultValue_6_0= RULE_STRING ) ) )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==33) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // InternalModel2Blockly.g:863:4: otherlv_5= 'default' ( (lv_defaultValue_6_0= RULE_STRING ) )
                    {
                    otherlv_5=(Token)match(input,33,FOLLOW_5); 

                    				newLeafNode(otherlv_5, grammarAccess.getAttributeAccess().getDefaultKeyword_5_0());
                    			
                    // InternalModel2Blockly.g:867:4: ( (lv_defaultValue_6_0= RULE_STRING ) )
                    // InternalModel2Blockly.g:868:5: (lv_defaultValue_6_0= RULE_STRING )
                    {
                    // InternalModel2Blockly.g:868:5: (lv_defaultValue_6_0= RULE_STRING )
                    // InternalModel2Blockly.g:869:6: lv_defaultValue_6_0= RULE_STRING
                    {
                    lv_defaultValue_6_0=(Token)match(input,RULE_STRING,FOLLOW_38); 

                    						newLeafNode(lv_defaultValue_6_0, grammarAccess.getAttributeAccess().getDefaultValueSTRINGTerminalRuleCall_5_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getAttributeRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"defaultValue",
                    							lv_defaultValue_6_0,
                    							"org.eclipse.xtext.common.Terminals.STRING");
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalModel2Blockly.g:886:3: (otherlv_7= 'min' ( (lv_min_8_0= RULE_STRING ) ) )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==34) ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // InternalModel2Blockly.g:887:4: otherlv_7= 'min' ( (lv_min_8_0= RULE_STRING ) )
                    {
                    otherlv_7=(Token)match(input,34,FOLLOW_5); 

                    				newLeafNode(otherlv_7, grammarAccess.getAttributeAccess().getMinKeyword_6_0());
                    			
                    // InternalModel2Blockly.g:891:4: ( (lv_min_8_0= RULE_STRING ) )
                    // InternalModel2Blockly.g:892:5: (lv_min_8_0= RULE_STRING )
                    {
                    // InternalModel2Blockly.g:892:5: (lv_min_8_0= RULE_STRING )
                    // InternalModel2Blockly.g:893:6: lv_min_8_0= RULE_STRING
                    {
                    lv_min_8_0=(Token)match(input,RULE_STRING,FOLLOW_39); 

                    						newLeafNode(lv_min_8_0, grammarAccess.getAttributeAccess().getMinSTRINGTerminalRuleCall_6_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getAttributeRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"min",
                    							lv_min_8_0,
                    							"org.eclipse.xtext.common.Terminals.STRING");
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalModel2Blockly.g:910:3: (otherlv_9= 'max' ( (lv_max_10_0= RULE_STRING ) ) )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==35) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // InternalModel2Blockly.g:911:4: otherlv_9= 'max' ( (lv_max_10_0= RULE_STRING ) )
                    {
                    otherlv_9=(Token)match(input,35,FOLLOW_5); 

                    				newLeafNode(otherlv_9, grammarAccess.getAttributeAccess().getMaxKeyword_7_0());
                    			
                    // InternalModel2Blockly.g:915:4: ( (lv_max_10_0= RULE_STRING ) )
                    // InternalModel2Blockly.g:916:5: (lv_max_10_0= RULE_STRING )
                    {
                    // InternalModel2Blockly.g:916:5: (lv_max_10_0= RULE_STRING )
                    // InternalModel2Blockly.g:917:6: lv_max_10_0= RULE_STRING
                    {
                    lv_max_10_0=(Token)match(input,RULE_STRING,FOLLOW_40); 

                    						newLeafNode(lv_max_10_0, grammarAccess.getAttributeAccess().getMaxSTRINGTerminalRuleCall_7_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getAttributeRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"max",
                    							lv_max_10_0,
                    							"org.eclipse.xtext.common.Terminals.STRING");
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalModel2Blockly.g:934:3: (otherlv_11= 'src' ( (lv_imageUrl_12_0= RULE_STRING ) ) )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==36) ) {
                alt32=1;
            }
            switch (alt32) {
                case 1 :
                    // InternalModel2Blockly.g:935:4: otherlv_11= 'src' ( (lv_imageUrl_12_0= RULE_STRING ) )
                    {
                    otherlv_11=(Token)match(input,36,FOLLOW_5); 

                    				newLeafNode(otherlv_11, grammarAccess.getAttributeAccess().getSrcKeyword_8_0());
                    			
                    // InternalModel2Blockly.g:939:4: ( (lv_imageUrl_12_0= RULE_STRING ) )
                    // InternalModel2Blockly.g:940:5: (lv_imageUrl_12_0= RULE_STRING )
                    {
                    // InternalModel2Blockly.g:940:5: (lv_imageUrl_12_0= RULE_STRING )
                    // InternalModel2Blockly.g:941:6: lv_imageUrl_12_0= RULE_STRING
                    {
                    lv_imageUrl_12_0=(Token)match(input,RULE_STRING,FOLLOW_41); 

                    						newLeafNode(lv_imageUrl_12_0, grammarAccess.getAttributeAccess().getImageUrlSTRINGTerminalRuleCall_8_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getAttributeRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"imageUrl",
                    							lv_imageUrl_12_0,
                    							"org.eclipse.xtext.common.Terminals.STRING");
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalModel2Blockly.g:958:3: (otherlv_13= 'width' ( (lv_imageWidth_14_0= RULE_INT ) ) )?
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==37) ) {
                alt33=1;
            }
            switch (alt33) {
                case 1 :
                    // InternalModel2Blockly.g:959:4: otherlv_13= 'width' ( (lv_imageWidth_14_0= RULE_INT ) )
                    {
                    otherlv_13=(Token)match(input,37,FOLLOW_15); 

                    				newLeafNode(otherlv_13, grammarAccess.getAttributeAccess().getWidthKeyword_9_0());
                    			
                    // InternalModel2Blockly.g:963:4: ( (lv_imageWidth_14_0= RULE_INT ) )
                    // InternalModel2Blockly.g:964:5: (lv_imageWidth_14_0= RULE_INT )
                    {
                    // InternalModel2Blockly.g:964:5: (lv_imageWidth_14_0= RULE_INT )
                    // InternalModel2Blockly.g:965:6: lv_imageWidth_14_0= RULE_INT
                    {
                    lv_imageWidth_14_0=(Token)match(input,RULE_INT,FOLLOW_42); 

                    						newLeafNode(lv_imageWidth_14_0, grammarAccess.getAttributeAccess().getImageWidthINTTerminalRuleCall_9_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getAttributeRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"imageWidth",
                    							lv_imageWidth_14_0,
                    							"org.eclipse.xtext.common.Terminals.INT");
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalModel2Blockly.g:982:3: (otherlv_15= 'height' ( (lv_imageHeight_16_0= RULE_INT ) ) )?
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==38) ) {
                alt34=1;
            }
            switch (alt34) {
                case 1 :
                    // InternalModel2Blockly.g:983:4: otherlv_15= 'height' ( (lv_imageHeight_16_0= RULE_INT ) )
                    {
                    otherlv_15=(Token)match(input,38,FOLLOW_15); 

                    				newLeafNode(otherlv_15, grammarAccess.getAttributeAccess().getHeightKeyword_10_0());
                    			
                    // InternalModel2Blockly.g:987:4: ( (lv_imageHeight_16_0= RULE_INT ) )
                    // InternalModel2Blockly.g:988:5: (lv_imageHeight_16_0= RULE_INT )
                    {
                    // InternalModel2Blockly.g:988:5: (lv_imageHeight_16_0= RULE_INT )
                    // InternalModel2Blockly.g:989:6: lv_imageHeight_16_0= RULE_INT
                    {
                    lv_imageHeight_16_0=(Token)match(input,RULE_INT,FOLLOW_43); 

                    						newLeafNode(lv_imageHeight_16_0, grammarAccess.getAttributeAccess().getImageHeightINTTerminalRuleCall_10_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getAttributeRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"imageHeight",
                    							lv_imageHeight_16_0,
                    							"org.eclipse.xtext.common.Terminals.INT");
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalModel2Blockly.g:1006:3: (otherlv_17= 'alt' ( (lv_imageAlt_18_0= RULE_STRING ) ) )?
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==39) ) {
                alt35=1;
            }
            switch (alt35) {
                case 1 :
                    // InternalModel2Blockly.g:1007:4: otherlv_17= 'alt' ( (lv_imageAlt_18_0= RULE_STRING ) )
                    {
                    otherlv_17=(Token)match(input,39,FOLLOW_5); 

                    				newLeafNode(otherlv_17, grammarAccess.getAttributeAccess().getAltKeyword_11_0());
                    			
                    // InternalModel2Blockly.g:1011:4: ( (lv_imageAlt_18_0= RULE_STRING ) )
                    // InternalModel2Blockly.g:1012:5: (lv_imageAlt_18_0= RULE_STRING )
                    {
                    // InternalModel2Blockly.g:1012:5: (lv_imageAlt_18_0= RULE_STRING )
                    // InternalModel2Blockly.g:1013:6: lv_imageAlt_18_0= RULE_STRING
                    {
                    lv_imageAlt_18_0=(Token)match(input,RULE_STRING,FOLLOW_44); 

                    						newLeafNode(lv_imageAlt_18_0, grammarAccess.getAttributeAccess().getImageAltSTRINGTerminalRuleCall_11_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getAttributeRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"imageAlt",
                    							lv_imageAlt_18_0,
                    							"org.eclipse.xtext.common.Terminals.STRING");
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalModel2Blockly.g:1030:3: ( (lv_required_19_0= 'required' ) )?
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( (LA36_0==40) ) {
                alt36=1;
            }
            switch (alt36) {
                case 1 :
                    // InternalModel2Blockly.g:1031:4: (lv_required_19_0= 'required' )
                    {
                    // InternalModel2Blockly.g:1031:4: (lv_required_19_0= 'required' )
                    // InternalModel2Blockly.g:1032:5: lv_required_19_0= 'required'
                    {
                    lv_required_19_0=(Token)match(input,40,FOLLOW_45); 

                    					newLeafNode(lv_required_19_0, grammarAccess.getAttributeAccess().getRequiredRequiredKeyword_12_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getAttributeRule());
                    					}
                    					setWithLastConsumed(current, "required", lv_required_19_0 != null, "required");
                    				

                    }


                    }
                    break;

            }

            // InternalModel2Blockly.g:1044:3: ( (lv_id_20_0= 'modelId' ) )?
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( (LA37_0==41) ) {
                alt37=1;
            }
            switch (alt37) {
                case 1 :
                    // InternalModel2Blockly.g:1045:4: (lv_id_20_0= 'modelId' )
                    {
                    // InternalModel2Blockly.g:1045:4: (lv_id_20_0= 'modelId' )
                    // InternalModel2Blockly.g:1046:5: lv_id_20_0= 'modelId'
                    {
                    lv_id_20_0=(Token)match(input,41,FOLLOW_46); 

                    					newLeafNode(lv_id_20_0, grammarAccess.getAttributeAccess().getIdModelIdKeyword_13_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getAttributeRule());
                    					}
                    					setWithLastConsumed(current, "id", lv_id_20_0 != null, "modelId");
                    				

                    }


                    }
                    break;

            }

            // InternalModel2Blockly.g:1058:3: ( (lv_unique_21_0= 'unique' ) )?
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==42) ) {
                alt38=1;
            }
            switch (alt38) {
                case 1 :
                    // InternalModel2Blockly.g:1059:4: (lv_unique_21_0= 'unique' )
                    {
                    // InternalModel2Blockly.g:1059:4: (lv_unique_21_0= 'unique' )
                    // InternalModel2Blockly.g:1060:5: lv_unique_21_0= 'unique'
                    {
                    lv_unique_21_0=(Token)match(input,42,FOLLOW_47); 

                    					newLeafNode(lv_unique_21_0, grammarAccess.getAttributeAccess().getUniqueUniqueKeyword_14_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getAttributeRule());
                    					}
                    					setWithLastConsumed(current, "unique", lv_unique_21_0 != null, "unique");
                    				

                    }


                    }
                    break;

            }

            // InternalModel2Blockly.g:1072:3: ( (lv_nonUnique_22_0= 'nonUnique' ) )?
            int alt39=2;
            int LA39_0 = input.LA(1);

            if ( (LA39_0==43) ) {
                alt39=1;
            }
            switch (alt39) {
                case 1 :
                    // InternalModel2Blockly.g:1073:4: (lv_nonUnique_22_0= 'nonUnique' )
                    {
                    // InternalModel2Blockly.g:1073:4: (lv_nonUnique_22_0= 'nonUnique' )
                    // InternalModel2Blockly.g:1074:5: lv_nonUnique_22_0= 'nonUnique'
                    {
                    lv_nonUnique_22_0=(Token)match(input,43,FOLLOW_48); 

                    					newLeafNode(lv_nonUnique_22_0, grammarAccess.getAttributeAccess().getNonUniqueNonUniqueKeyword_15_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getAttributeRule());
                    					}
                    					setWithLastConsumed(current, "nonUnique", lv_nonUnique_22_0 != null, "nonUnique");
                    				

                    }


                    }
                    break;

            }

            // InternalModel2Blockly.g:1086:3: ( (lv_ordered_23_0= 'ordered' ) )?
            int alt40=2;
            int LA40_0 = input.LA(1);

            if ( (LA40_0==44) ) {
                alt40=1;
            }
            switch (alt40) {
                case 1 :
                    // InternalModel2Blockly.g:1087:4: (lv_ordered_23_0= 'ordered' )
                    {
                    // InternalModel2Blockly.g:1087:4: (lv_ordered_23_0= 'ordered' )
                    // InternalModel2Blockly.g:1088:5: lv_ordered_23_0= 'ordered'
                    {
                    lv_ordered_23_0=(Token)match(input,44,FOLLOW_49); 

                    					newLeafNode(lv_ordered_23_0, grammarAccess.getAttributeAccess().getOrderedOrderedKeyword_16_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getAttributeRule());
                    					}
                    					setWithLastConsumed(current, "ordered", lv_ordered_23_0 != null, "ordered");
                    				

                    }


                    }
                    break;

            }

            // InternalModel2Blockly.g:1100:3: ( (lv_unordered_24_0= 'unordered' ) )?
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==45) ) {
                alt41=1;
            }
            switch (alt41) {
                case 1 :
                    // InternalModel2Blockly.g:1101:4: (lv_unordered_24_0= 'unordered' )
                    {
                    // InternalModel2Blockly.g:1101:4: (lv_unordered_24_0= 'unordered' )
                    // InternalModel2Blockly.g:1102:5: lv_unordered_24_0= 'unordered'
                    {
                    lv_unordered_24_0=(Token)match(input,45,FOLLOW_50); 

                    					newLeafNode(lv_unordered_24_0, grammarAccess.getAttributeAccess().getUnorderedUnorderedKeyword_17_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getAttributeRule());
                    					}
                    					setWithLastConsumed(current, "unordered", lv_unordered_24_0 != null, "unordered");
                    				

                    }


                    }
                    break;

            }

            // InternalModel2Blockly.g:1114:3: ( (lv_ui_25_0= ruleUiOptions ) )?
            int alt42=2;
            int LA42_0 = input.LA(1);

            if ( ((LA42_0>=54 && LA42_0<=63)) ) {
                alt42=1;
            }
            switch (alt42) {
                case 1 :
                    // InternalModel2Blockly.g:1115:4: (lv_ui_25_0= ruleUiOptions )
                    {
                    // InternalModel2Blockly.g:1115:4: (lv_ui_25_0= ruleUiOptions )
                    // InternalModel2Blockly.g:1116:5: lv_ui_25_0= ruleUiOptions
                    {

                    					newCompositeNode(grammarAccess.getAttributeAccess().getUiUiOptionsParserRuleCall_18_0());
                    				
                    pushFollow(FOLLOW_2);
                    lv_ui_25_0=ruleUiOptions();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getAttributeRule());
                    					}
                    					set(
                    						current,
                    						"ui",
                    						lv_ui_25_0,
                    						"io.github.plortinus.model2blockly.Model2Blockly.UiOptions");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAttribute"


    // $ANTLR start "entryRuleContainment"
    // InternalModel2Blockly.g:1137:1: entryRuleContainment returns [EObject current=null] : iv_ruleContainment= ruleContainment EOF ;
    public final EObject entryRuleContainment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleContainment = null;


        try {
            // InternalModel2Blockly.g:1137:52: (iv_ruleContainment= ruleContainment EOF )
            // InternalModel2Blockly.g:1138:2: iv_ruleContainment= ruleContainment EOF
            {
             newCompositeNode(grammarAccess.getContainmentRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleContainment=ruleContainment();

            state._fsp--;

             current =iv_ruleContainment; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleContainment"


    // $ANTLR start "ruleContainment"
    // InternalModel2Blockly.g:1144:1: ruleContainment returns [EObject current=null] : (otherlv_0= 'contains' ( (otherlv_1= RULE_ID ) ) ( (lv_name_2_0= RULE_ID ) ) (otherlv_3= '[' ( (lv_lower_4_0= RULE_INT ) ) otherlv_5= '..' ( (lv_upper_6_0= RULE_INT ) ) otherlv_7= ']' )? ( (lv_ui_8_0= ruleUiOptions ) )? ) ;
    public final EObject ruleContainment() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token lv_lower_4_0=null;
        Token otherlv_5=null;
        Token lv_upper_6_0=null;
        Token otherlv_7=null;
        EObject lv_ui_8_0 = null;



        	enterRule();

        try {
            // InternalModel2Blockly.g:1150:2: ( (otherlv_0= 'contains' ( (otherlv_1= RULE_ID ) ) ( (lv_name_2_0= RULE_ID ) ) (otherlv_3= '[' ( (lv_lower_4_0= RULE_INT ) ) otherlv_5= '..' ( (lv_upper_6_0= RULE_INT ) ) otherlv_7= ']' )? ( (lv_ui_8_0= ruleUiOptions ) )? ) )
            // InternalModel2Blockly.g:1151:2: (otherlv_0= 'contains' ( (otherlv_1= RULE_ID ) ) ( (lv_name_2_0= RULE_ID ) ) (otherlv_3= '[' ( (lv_lower_4_0= RULE_INT ) ) otherlv_5= '..' ( (lv_upper_6_0= RULE_INT ) ) otherlv_7= ']' )? ( (lv_ui_8_0= ruleUiOptions ) )? )
            {
            // InternalModel2Blockly.g:1151:2: (otherlv_0= 'contains' ( (otherlv_1= RULE_ID ) ) ( (lv_name_2_0= RULE_ID ) ) (otherlv_3= '[' ( (lv_lower_4_0= RULE_INT ) ) otherlv_5= '..' ( (lv_upper_6_0= RULE_INT ) ) otherlv_7= ']' )? ( (lv_ui_8_0= ruleUiOptions ) )? )
            // InternalModel2Blockly.g:1152:3: otherlv_0= 'contains' ( (otherlv_1= RULE_ID ) ) ( (lv_name_2_0= RULE_ID ) ) (otherlv_3= '[' ( (lv_lower_4_0= RULE_INT ) ) otherlv_5= '..' ( (lv_upper_6_0= RULE_INT ) ) otherlv_7= ']' )? ( (lv_ui_8_0= ruleUiOptions ) )?
            {
            otherlv_0=(Token)match(input,46,FOLLOW_3); 

            			newLeafNode(otherlv_0, grammarAccess.getContainmentAccess().getContainsKeyword_0());
            		
            // InternalModel2Blockly.g:1156:3: ( (otherlv_1= RULE_ID ) )
            // InternalModel2Blockly.g:1157:4: (otherlv_1= RULE_ID )
            {
            // InternalModel2Blockly.g:1157:4: (otherlv_1= RULE_ID )
            // InternalModel2Blockly.g:1158:5: otherlv_1= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getContainmentRule());
            					}
            				
            otherlv_1=(Token)match(input,RULE_ID,FOLLOW_3); 

            					newLeafNode(otherlv_1, grammarAccess.getContainmentAccess().getTypeClassDefCrossReference_1_0());
            				

            }


            }

            // InternalModel2Blockly.g:1169:3: ( (lv_name_2_0= RULE_ID ) )
            // InternalModel2Blockly.g:1170:4: (lv_name_2_0= RULE_ID )
            {
            // InternalModel2Blockly.g:1170:4: (lv_name_2_0= RULE_ID )
            // InternalModel2Blockly.g:1171:5: lv_name_2_0= RULE_ID
            {
            lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_51); 

            					newLeafNode(lv_name_2_0, grammarAccess.getContainmentAccess().getNameIDTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getContainmentRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            // InternalModel2Blockly.g:1187:3: (otherlv_3= '[' ( (lv_lower_4_0= RULE_INT ) ) otherlv_5= '..' ( (lv_upper_6_0= RULE_INT ) ) otherlv_7= ']' )?
            int alt43=2;
            int LA43_0 = input.LA(1);

            if ( (LA43_0==47) ) {
                alt43=1;
            }
            switch (alt43) {
                case 1 :
                    // InternalModel2Blockly.g:1188:4: otherlv_3= '[' ( (lv_lower_4_0= RULE_INT ) ) otherlv_5= '..' ( (lv_upper_6_0= RULE_INT ) ) otherlv_7= ']'
                    {
                    otherlv_3=(Token)match(input,47,FOLLOW_15); 

                    				newLeafNode(otherlv_3, grammarAccess.getContainmentAccess().getLeftSquareBracketKeyword_3_0());
                    			
                    // InternalModel2Blockly.g:1192:4: ( (lv_lower_4_0= RULE_INT ) )
                    // InternalModel2Blockly.g:1193:5: (lv_lower_4_0= RULE_INT )
                    {
                    // InternalModel2Blockly.g:1193:5: (lv_lower_4_0= RULE_INT )
                    // InternalModel2Blockly.g:1194:6: lv_lower_4_0= RULE_INT
                    {
                    lv_lower_4_0=(Token)match(input,RULE_INT,FOLLOW_52); 

                    						newLeafNode(lv_lower_4_0, grammarAccess.getContainmentAccess().getLowerINTTerminalRuleCall_3_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getContainmentRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"lower",
                    							lv_lower_4_0,
                    							"org.eclipse.xtext.common.Terminals.INT");
                    					

                    }


                    }

                    otherlv_5=(Token)match(input,48,FOLLOW_15); 

                    				newLeafNode(otherlv_5, grammarAccess.getContainmentAccess().getFullStopFullStopKeyword_3_2());
                    			
                    // InternalModel2Blockly.g:1214:4: ( (lv_upper_6_0= RULE_INT ) )
                    // InternalModel2Blockly.g:1215:5: (lv_upper_6_0= RULE_INT )
                    {
                    // InternalModel2Blockly.g:1215:5: (lv_upper_6_0= RULE_INT )
                    // InternalModel2Blockly.g:1216:6: lv_upper_6_0= RULE_INT
                    {
                    lv_upper_6_0=(Token)match(input,RULE_INT,FOLLOW_53); 

                    						newLeafNode(lv_upper_6_0, grammarAccess.getContainmentAccess().getUpperINTTerminalRuleCall_3_3_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getContainmentRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"upper",
                    							lv_upper_6_0,
                    							"org.eclipse.xtext.common.Terminals.INT");
                    					

                    }


                    }

                    otherlv_7=(Token)match(input,49,FOLLOW_50); 

                    				newLeafNode(otherlv_7, grammarAccess.getContainmentAccess().getRightSquareBracketKeyword_3_4());
                    			

                    }
                    break;

            }

            // InternalModel2Blockly.g:1237:3: ( (lv_ui_8_0= ruleUiOptions ) )?
            int alt44=2;
            int LA44_0 = input.LA(1);

            if ( ((LA44_0>=54 && LA44_0<=63)) ) {
                alt44=1;
            }
            switch (alt44) {
                case 1 :
                    // InternalModel2Blockly.g:1238:4: (lv_ui_8_0= ruleUiOptions )
                    {
                    // InternalModel2Blockly.g:1238:4: (lv_ui_8_0= ruleUiOptions )
                    // InternalModel2Blockly.g:1239:5: lv_ui_8_0= ruleUiOptions
                    {

                    					newCompositeNode(grammarAccess.getContainmentAccess().getUiUiOptionsParserRuleCall_4_0());
                    				
                    pushFollow(FOLLOW_2);
                    lv_ui_8_0=ruleUiOptions();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getContainmentRule());
                    					}
                    					set(
                    						current,
                    						"ui",
                    						lv_ui_8_0,
                    						"io.github.plortinus.model2blockly.Model2Blockly.UiOptions");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleContainment"


    // $ANTLR start "entryRuleReference"
    // InternalModel2Blockly.g:1260:1: entryRuleReference returns [EObject current=null] : iv_ruleReference= ruleReference EOF ;
    public final EObject entryRuleReference() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleReference = null;


        try {
            // InternalModel2Blockly.g:1260:50: (iv_ruleReference= ruleReference EOF )
            // InternalModel2Blockly.g:1261:2: iv_ruleReference= ruleReference EOF
            {
             newCompositeNode(grammarAccess.getReferenceRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleReference=ruleReference();

            state._fsp--;

             current =iv_ruleReference; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleReference"


    // $ANTLR start "ruleReference"
    // InternalModel2Blockly.g:1267:1: ruleReference returns [EObject current=null] : (otherlv_0= 'reference' ( (otherlv_1= RULE_ID ) ) ( (lv_name_2_0= RULE_ID ) ) ( (lv_cardinality_3_0= ruleCardinality ) )? (otherlv_4= 'opposite' ( (lv_oppositeName_5_0= RULE_ID ) ) )? ( (lv_required_6_0= 'required' ) )? ( (lv_unique_7_0= 'unique' ) )? ( (lv_nonUnique_8_0= 'nonUnique' ) )? ( (lv_ordered_9_0= 'ordered' ) )? ( (lv_unordered_10_0= 'unordered' ) )? ( (lv_ui_11_0= ruleUiOptions ) )? ) ;
    public final EObject ruleReference() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_4=null;
        Token lv_oppositeName_5_0=null;
        Token lv_required_6_0=null;
        Token lv_unique_7_0=null;
        Token lv_nonUnique_8_0=null;
        Token lv_ordered_9_0=null;
        Token lv_unordered_10_0=null;
        EObject lv_cardinality_3_0 = null;

        EObject lv_ui_11_0 = null;



        	enterRule();

        try {
            // InternalModel2Blockly.g:1273:2: ( (otherlv_0= 'reference' ( (otherlv_1= RULE_ID ) ) ( (lv_name_2_0= RULE_ID ) ) ( (lv_cardinality_3_0= ruleCardinality ) )? (otherlv_4= 'opposite' ( (lv_oppositeName_5_0= RULE_ID ) ) )? ( (lv_required_6_0= 'required' ) )? ( (lv_unique_7_0= 'unique' ) )? ( (lv_nonUnique_8_0= 'nonUnique' ) )? ( (lv_ordered_9_0= 'ordered' ) )? ( (lv_unordered_10_0= 'unordered' ) )? ( (lv_ui_11_0= ruleUiOptions ) )? ) )
            // InternalModel2Blockly.g:1274:2: (otherlv_0= 'reference' ( (otherlv_1= RULE_ID ) ) ( (lv_name_2_0= RULE_ID ) ) ( (lv_cardinality_3_0= ruleCardinality ) )? (otherlv_4= 'opposite' ( (lv_oppositeName_5_0= RULE_ID ) ) )? ( (lv_required_6_0= 'required' ) )? ( (lv_unique_7_0= 'unique' ) )? ( (lv_nonUnique_8_0= 'nonUnique' ) )? ( (lv_ordered_9_0= 'ordered' ) )? ( (lv_unordered_10_0= 'unordered' ) )? ( (lv_ui_11_0= ruleUiOptions ) )? )
            {
            // InternalModel2Blockly.g:1274:2: (otherlv_0= 'reference' ( (otherlv_1= RULE_ID ) ) ( (lv_name_2_0= RULE_ID ) ) ( (lv_cardinality_3_0= ruleCardinality ) )? (otherlv_4= 'opposite' ( (lv_oppositeName_5_0= RULE_ID ) ) )? ( (lv_required_6_0= 'required' ) )? ( (lv_unique_7_0= 'unique' ) )? ( (lv_nonUnique_8_0= 'nonUnique' ) )? ( (lv_ordered_9_0= 'ordered' ) )? ( (lv_unordered_10_0= 'unordered' ) )? ( (lv_ui_11_0= ruleUiOptions ) )? )
            // InternalModel2Blockly.g:1275:3: otherlv_0= 'reference' ( (otherlv_1= RULE_ID ) ) ( (lv_name_2_0= RULE_ID ) ) ( (lv_cardinality_3_0= ruleCardinality ) )? (otherlv_4= 'opposite' ( (lv_oppositeName_5_0= RULE_ID ) ) )? ( (lv_required_6_0= 'required' ) )? ( (lv_unique_7_0= 'unique' ) )? ( (lv_nonUnique_8_0= 'nonUnique' ) )? ( (lv_ordered_9_0= 'ordered' ) )? ( (lv_unordered_10_0= 'unordered' ) )? ( (lv_ui_11_0= ruleUiOptions ) )?
            {
            otherlv_0=(Token)match(input,50,FOLLOW_3); 

            			newLeafNode(otherlv_0, grammarAccess.getReferenceAccess().getReferenceKeyword_0());
            		
            // InternalModel2Blockly.g:1279:3: ( (otherlv_1= RULE_ID ) )
            // InternalModel2Blockly.g:1280:4: (otherlv_1= RULE_ID )
            {
            // InternalModel2Blockly.g:1280:4: (otherlv_1= RULE_ID )
            // InternalModel2Blockly.g:1281:5: otherlv_1= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getReferenceRule());
            					}
            				
            otherlv_1=(Token)match(input,RULE_ID,FOLLOW_3); 

            					newLeafNode(otherlv_1, grammarAccess.getReferenceAccess().getTypeClassDefCrossReference_1_0());
            				

            }


            }

            // InternalModel2Blockly.g:1292:3: ( (lv_name_2_0= RULE_ID ) )
            // InternalModel2Blockly.g:1293:4: (lv_name_2_0= RULE_ID )
            {
            // InternalModel2Blockly.g:1293:4: (lv_name_2_0= RULE_ID )
            // InternalModel2Blockly.g:1294:5: lv_name_2_0= RULE_ID
            {
            lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_54); 

            					newLeafNode(lv_name_2_0, grammarAccess.getReferenceAccess().getNameIDTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getReferenceRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            // InternalModel2Blockly.g:1310:3: ( (lv_cardinality_3_0= ruleCardinality ) )?
            int alt45=2;
            int LA45_0 = input.LA(1);

            if ( (LA45_0==47) ) {
                alt45=1;
            }
            switch (alt45) {
                case 1 :
                    // InternalModel2Blockly.g:1311:4: (lv_cardinality_3_0= ruleCardinality )
                    {
                    // InternalModel2Blockly.g:1311:4: (lv_cardinality_3_0= ruleCardinality )
                    // InternalModel2Blockly.g:1312:5: lv_cardinality_3_0= ruleCardinality
                    {

                    					newCompositeNode(grammarAccess.getReferenceAccess().getCardinalityCardinalityParserRuleCall_3_0());
                    				
                    pushFollow(FOLLOW_55);
                    lv_cardinality_3_0=ruleCardinality();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getReferenceRule());
                    					}
                    					set(
                    						current,
                    						"cardinality",
                    						lv_cardinality_3_0,
                    						"io.github.plortinus.model2blockly.Model2Blockly.Cardinality");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            // InternalModel2Blockly.g:1329:3: (otherlv_4= 'opposite' ( (lv_oppositeName_5_0= RULE_ID ) ) )?
            int alt46=2;
            int LA46_0 = input.LA(1);

            if ( (LA46_0==51) ) {
                alt46=1;
            }
            switch (alt46) {
                case 1 :
                    // InternalModel2Blockly.g:1330:4: otherlv_4= 'opposite' ( (lv_oppositeName_5_0= RULE_ID ) )
                    {
                    otherlv_4=(Token)match(input,51,FOLLOW_3); 

                    				newLeafNode(otherlv_4, grammarAccess.getReferenceAccess().getOppositeKeyword_4_0());
                    			
                    // InternalModel2Blockly.g:1334:4: ( (lv_oppositeName_5_0= RULE_ID ) )
                    // InternalModel2Blockly.g:1335:5: (lv_oppositeName_5_0= RULE_ID )
                    {
                    // InternalModel2Blockly.g:1335:5: (lv_oppositeName_5_0= RULE_ID )
                    // InternalModel2Blockly.g:1336:6: lv_oppositeName_5_0= RULE_ID
                    {
                    lv_oppositeName_5_0=(Token)match(input,RULE_ID,FOLLOW_56); 

                    						newLeafNode(lv_oppositeName_5_0, grammarAccess.getReferenceAccess().getOppositeNameIDTerminalRuleCall_4_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getReferenceRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"oppositeName",
                    							lv_oppositeName_5_0,
                    							"org.eclipse.xtext.common.Terminals.ID");
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalModel2Blockly.g:1353:3: ( (lv_required_6_0= 'required' ) )?
            int alt47=2;
            int LA47_0 = input.LA(1);

            if ( (LA47_0==40) ) {
                alt47=1;
            }
            switch (alt47) {
                case 1 :
                    // InternalModel2Blockly.g:1354:4: (lv_required_6_0= 'required' )
                    {
                    // InternalModel2Blockly.g:1354:4: (lv_required_6_0= 'required' )
                    // InternalModel2Blockly.g:1355:5: lv_required_6_0= 'required'
                    {
                    lv_required_6_0=(Token)match(input,40,FOLLOW_46); 

                    					newLeafNode(lv_required_6_0, grammarAccess.getReferenceAccess().getRequiredRequiredKeyword_5_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getReferenceRule());
                    					}
                    					setWithLastConsumed(current, "required", lv_required_6_0 != null, "required");
                    				

                    }


                    }
                    break;

            }

            // InternalModel2Blockly.g:1367:3: ( (lv_unique_7_0= 'unique' ) )?
            int alt48=2;
            int LA48_0 = input.LA(1);

            if ( (LA48_0==42) ) {
                alt48=1;
            }
            switch (alt48) {
                case 1 :
                    // InternalModel2Blockly.g:1368:4: (lv_unique_7_0= 'unique' )
                    {
                    // InternalModel2Blockly.g:1368:4: (lv_unique_7_0= 'unique' )
                    // InternalModel2Blockly.g:1369:5: lv_unique_7_0= 'unique'
                    {
                    lv_unique_7_0=(Token)match(input,42,FOLLOW_47); 

                    					newLeafNode(lv_unique_7_0, grammarAccess.getReferenceAccess().getUniqueUniqueKeyword_6_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getReferenceRule());
                    					}
                    					setWithLastConsumed(current, "unique", lv_unique_7_0 != null, "unique");
                    				

                    }


                    }
                    break;

            }

            // InternalModel2Blockly.g:1381:3: ( (lv_nonUnique_8_0= 'nonUnique' ) )?
            int alt49=2;
            int LA49_0 = input.LA(1);

            if ( (LA49_0==43) ) {
                alt49=1;
            }
            switch (alt49) {
                case 1 :
                    // InternalModel2Blockly.g:1382:4: (lv_nonUnique_8_0= 'nonUnique' )
                    {
                    // InternalModel2Blockly.g:1382:4: (lv_nonUnique_8_0= 'nonUnique' )
                    // InternalModel2Blockly.g:1383:5: lv_nonUnique_8_0= 'nonUnique'
                    {
                    lv_nonUnique_8_0=(Token)match(input,43,FOLLOW_48); 

                    					newLeafNode(lv_nonUnique_8_0, grammarAccess.getReferenceAccess().getNonUniqueNonUniqueKeyword_7_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getReferenceRule());
                    					}
                    					setWithLastConsumed(current, "nonUnique", lv_nonUnique_8_0 != null, "nonUnique");
                    				

                    }


                    }
                    break;

            }

            // InternalModel2Blockly.g:1395:3: ( (lv_ordered_9_0= 'ordered' ) )?
            int alt50=2;
            int LA50_0 = input.LA(1);

            if ( (LA50_0==44) ) {
                alt50=1;
            }
            switch (alt50) {
                case 1 :
                    // InternalModel2Blockly.g:1396:4: (lv_ordered_9_0= 'ordered' )
                    {
                    // InternalModel2Blockly.g:1396:4: (lv_ordered_9_0= 'ordered' )
                    // InternalModel2Blockly.g:1397:5: lv_ordered_9_0= 'ordered'
                    {
                    lv_ordered_9_0=(Token)match(input,44,FOLLOW_49); 

                    					newLeafNode(lv_ordered_9_0, grammarAccess.getReferenceAccess().getOrderedOrderedKeyword_8_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getReferenceRule());
                    					}
                    					setWithLastConsumed(current, "ordered", lv_ordered_9_0 != null, "ordered");
                    				

                    }


                    }
                    break;

            }

            // InternalModel2Blockly.g:1409:3: ( (lv_unordered_10_0= 'unordered' ) )?
            int alt51=2;
            int LA51_0 = input.LA(1);

            if ( (LA51_0==45) ) {
                alt51=1;
            }
            switch (alt51) {
                case 1 :
                    // InternalModel2Blockly.g:1410:4: (lv_unordered_10_0= 'unordered' )
                    {
                    // InternalModel2Blockly.g:1410:4: (lv_unordered_10_0= 'unordered' )
                    // InternalModel2Blockly.g:1411:5: lv_unordered_10_0= 'unordered'
                    {
                    lv_unordered_10_0=(Token)match(input,45,FOLLOW_50); 

                    					newLeafNode(lv_unordered_10_0, grammarAccess.getReferenceAccess().getUnorderedUnorderedKeyword_9_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getReferenceRule());
                    					}
                    					setWithLastConsumed(current, "unordered", lv_unordered_10_0 != null, "unordered");
                    				

                    }


                    }
                    break;

            }

            // InternalModel2Blockly.g:1423:3: ( (lv_ui_11_0= ruleUiOptions ) )?
            int alt52=2;
            int LA52_0 = input.LA(1);

            if ( ((LA52_0>=54 && LA52_0<=63)) ) {
                alt52=1;
            }
            switch (alt52) {
                case 1 :
                    // InternalModel2Blockly.g:1424:4: (lv_ui_11_0= ruleUiOptions )
                    {
                    // InternalModel2Blockly.g:1424:4: (lv_ui_11_0= ruleUiOptions )
                    // InternalModel2Blockly.g:1425:5: lv_ui_11_0= ruleUiOptions
                    {

                    					newCompositeNode(grammarAccess.getReferenceAccess().getUiUiOptionsParserRuleCall_10_0());
                    				
                    pushFollow(FOLLOW_2);
                    lv_ui_11_0=ruleUiOptions();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getReferenceRule());
                    					}
                    					set(
                    						current,
                    						"ui",
                    						lv_ui_11_0,
                    						"io.github.plortinus.model2blockly.Model2Blockly.UiOptions");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleReference"


    // $ANTLR start "entryRuleValueInput"
    // InternalModel2Blockly.g:1446:1: entryRuleValueInput returns [EObject current=null] : iv_ruleValueInput= ruleValueInput EOF ;
    public final EObject entryRuleValueInput() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleValueInput = null;


        try {
            // InternalModel2Blockly.g:1446:51: (iv_ruleValueInput= ruleValueInput EOF )
            // InternalModel2Blockly.g:1447:2: iv_ruleValueInput= ruleValueInput EOF
            {
             newCompositeNode(grammarAccess.getValueInputRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleValueInput=ruleValueInput();

            state._fsp--;

             current =iv_ruleValueInput; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleValueInput"


    // $ANTLR start "ruleValueInput"
    // InternalModel2Blockly.g:1453:1: ruleValueInput returns [EObject current=null] : (otherlv_0= 'value' ( (otherlv_1= RULE_ID ) ) ( (lv_name_2_0= RULE_ID ) ) (otherlv_3= 'shadow' ( (otherlv_4= RULE_ID ) ) )? ( (lv_ui_5_0= ruleUiOptions ) )? ) ;
    public final EObject ruleValueInput() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        EObject lv_ui_5_0 = null;



        	enterRule();

        try {
            // InternalModel2Blockly.g:1459:2: ( (otherlv_0= 'value' ( (otherlv_1= RULE_ID ) ) ( (lv_name_2_0= RULE_ID ) ) (otherlv_3= 'shadow' ( (otherlv_4= RULE_ID ) ) )? ( (lv_ui_5_0= ruleUiOptions ) )? ) )
            // InternalModel2Blockly.g:1460:2: (otherlv_0= 'value' ( (otherlv_1= RULE_ID ) ) ( (lv_name_2_0= RULE_ID ) ) (otherlv_3= 'shadow' ( (otherlv_4= RULE_ID ) ) )? ( (lv_ui_5_0= ruleUiOptions ) )? )
            {
            // InternalModel2Blockly.g:1460:2: (otherlv_0= 'value' ( (otherlv_1= RULE_ID ) ) ( (lv_name_2_0= RULE_ID ) ) (otherlv_3= 'shadow' ( (otherlv_4= RULE_ID ) ) )? ( (lv_ui_5_0= ruleUiOptions ) )? )
            // InternalModel2Blockly.g:1461:3: otherlv_0= 'value' ( (otherlv_1= RULE_ID ) ) ( (lv_name_2_0= RULE_ID ) ) (otherlv_3= 'shadow' ( (otherlv_4= RULE_ID ) ) )? ( (lv_ui_5_0= ruleUiOptions ) )?
            {
            otherlv_0=(Token)match(input,52,FOLLOW_3); 

            			newLeafNode(otherlv_0, grammarAccess.getValueInputAccess().getValueKeyword_0());
            		
            // InternalModel2Blockly.g:1465:3: ( (otherlv_1= RULE_ID ) )
            // InternalModel2Blockly.g:1466:4: (otherlv_1= RULE_ID )
            {
            // InternalModel2Blockly.g:1466:4: (otherlv_1= RULE_ID )
            // InternalModel2Blockly.g:1467:5: otherlv_1= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getValueInputRule());
            					}
            				
            otherlv_1=(Token)match(input,RULE_ID,FOLLOW_3); 

            					newLeafNode(otherlv_1, grammarAccess.getValueInputAccess().getTypeClassDefCrossReference_1_0());
            				

            }


            }

            // InternalModel2Blockly.g:1478:3: ( (lv_name_2_0= RULE_ID ) )
            // InternalModel2Blockly.g:1479:4: (lv_name_2_0= RULE_ID )
            {
            // InternalModel2Blockly.g:1479:4: (lv_name_2_0= RULE_ID )
            // InternalModel2Blockly.g:1480:5: lv_name_2_0= RULE_ID
            {
            lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_57); 

            					newLeafNode(lv_name_2_0, grammarAccess.getValueInputAccess().getNameIDTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getValueInputRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            // InternalModel2Blockly.g:1496:3: (otherlv_3= 'shadow' ( (otherlv_4= RULE_ID ) ) )?
            int alt53=2;
            int LA53_0 = input.LA(1);

            if ( (LA53_0==53) ) {
                alt53=1;
            }
            switch (alt53) {
                case 1 :
                    // InternalModel2Blockly.g:1497:4: otherlv_3= 'shadow' ( (otherlv_4= RULE_ID ) )
                    {
                    otherlv_3=(Token)match(input,53,FOLLOW_3); 

                    				newLeafNode(otherlv_3, grammarAccess.getValueInputAccess().getShadowKeyword_3_0());
                    			
                    // InternalModel2Blockly.g:1501:4: ( (otherlv_4= RULE_ID ) )
                    // InternalModel2Blockly.g:1502:5: (otherlv_4= RULE_ID )
                    {
                    // InternalModel2Blockly.g:1502:5: (otherlv_4= RULE_ID )
                    // InternalModel2Blockly.g:1503:6: otherlv_4= RULE_ID
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getValueInputRule());
                    						}
                    					
                    otherlv_4=(Token)match(input,RULE_ID,FOLLOW_50); 

                    						newLeafNode(otherlv_4, grammarAccess.getValueInputAccess().getShadowTypeClassDefCrossReference_3_1_0());
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalModel2Blockly.g:1515:3: ( (lv_ui_5_0= ruleUiOptions ) )?
            int alt54=2;
            int LA54_0 = input.LA(1);

            if ( ((LA54_0>=54 && LA54_0<=63)) ) {
                alt54=1;
            }
            switch (alt54) {
                case 1 :
                    // InternalModel2Blockly.g:1516:4: (lv_ui_5_0= ruleUiOptions )
                    {
                    // InternalModel2Blockly.g:1516:4: (lv_ui_5_0= ruleUiOptions )
                    // InternalModel2Blockly.g:1517:5: lv_ui_5_0= ruleUiOptions
                    {

                    					newCompositeNode(grammarAccess.getValueInputAccess().getUiUiOptionsParserRuleCall_4_0());
                    				
                    pushFollow(FOLLOW_2);
                    lv_ui_5_0=ruleUiOptions();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getValueInputRule());
                    					}
                    					set(
                    						current,
                    						"ui",
                    						lv_ui_5_0,
                    						"io.github.plortinus.model2blockly.Model2Blockly.UiOptions");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleValueInput"


    // $ANTLR start "entryRuleCardinality"
    // InternalModel2Blockly.g:1538:1: entryRuleCardinality returns [EObject current=null] : iv_ruleCardinality= ruleCardinality EOF ;
    public final EObject entryRuleCardinality() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCardinality = null;


        try {
            // InternalModel2Blockly.g:1538:52: (iv_ruleCardinality= ruleCardinality EOF )
            // InternalModel2Blockly.g:1539:2: iv_ruleCardinality= ruleCardinality EOF
            {
             newCompositeNode(grammarAccess.getCardinalityRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleCardinality=ruleCardinality();

            state._fsp--;

             current =iv_ruleCardinality; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleCardinality"


    // $ANTLR start "ruleCardinality"
    // InternalModel2Blockly.g:1545:1: ruleCardinality returns [EObject current=null] : (otherlv_0= '[' ( (lv_lower_1_0= RULE_INT ) ) otherlv_2= '..' ( (lv_upper_3_0= RULE_INT ) ) otherlv_4= ']' ) ;
    public final EObject ruleCardinality() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_lower_1_0=null;
        Token otherlv_2=null;
        Token lv_upper_3_0=null;
        Token otherlv_4=null;


        	enterRule();

        try {
            // InternalModel2Blockly.g:1551:2: ( (otherlv_0= '[' ( (lv_lower_1_0= RULE_INT ) ) otherlv_2= '..' ( (lv_upper_3_0= RULE_INT ) ) otherlv_4= ']' ) )
            // InternalModel2Blockly.g:1552:2: (otherlv_0= '[' ( (lv_lower_1_0= RULE_INT ) ) otherlv_2= '..' ( (lv_upper_3_0= RULE_INT ) ) otherlv_4= ']' )
            {
            // InternalModel2Blockly.g:1552:2: (otherlv_0= '[' ( (lv_lower_1_0= RULE_INT ) ) otherlv_2= '..' ( (lv_upper_3_0= RULE_INT ) ) otherlv_4= ']' )
            // InternalModel2Blockly.g:1553:3: otherlv_0= '[' ( (lv_lower_1_0= RULE_INT ) ) otherlv_2= '..' ( (lv_upper_3_0= RULE_INT ) ) otherlv_4= ']'
            {
            otherlv_0=(Token)match(input,47,FOLLOW_15); 

            			newLeafNode(otherlv_0, grammarAccess.getCardinalityAccess().getLeftSquareBracketKeyword_0());
            		
            // InternalModel2Blockly.g:1557:3: ( (lv_lower_1_0= RULE_INT ) )
            // InternalModel2Blockly.g:1558:4: (lv_lower_1_0= RULE_INT )
            {
            // InternalModel2Blockly.g:1558:4: (lv_lower_1_0= RULE_INT )
            // InternalModel2Blockly.g:1559:5: lv_lower_1_0= RULE_INT
            {
            lv_lower_1_0=(Token)match(input,RULE_INT,FOLLOW_52); 

            					newLeafNode(lv_lower_1_0, grammarAccess.getCardinalityAccess().getLowerINTTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getCardinalityRule());
            					}
            					setWithLastConsumed(
            						current,
            						"lower",
            						lv_lower_1_0,
            						"org.eclipse.xtext.common.Terminals.INT");
            				

            }


            }

            otherlv_2=(Token)match(input,48,FOLLOW_15); 

            			newLeafNode(otherlv_2, grammarAccess.getCardinalityAccess().getFullStopFullStopKeyword_2());
            		
            // InternalModel2Blockly.g:1579:3: ( (lv_upper_3_0= RULE_INT ) )
            // InternalModel2Blockly.g:1580:4: (lv_upper_3_0= RULE_INT )
            {
            // InternalModel2Blockly.g:1580:4: (lv_upper_3_0= RULE_INT )
            // InternalModel2Blockly.g:1581:5: lv_upper_3_0= RULE_INT
            {
            lv_upper_3_0=(Token)match(input,RULE_INT,FOLLOW_53); 

            					newLeafNode(lv_upper_3_0, grammarAccess.getCardinalityAccess().getUpperINTTerminalRuleCall_3_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getCardinalityRule());
            					}
            					setWithLastConsumed(
            						current,
            						"upper",
            						lv_upper_3_0,
            						"org.eclipse.xtext.common.Terminals.INT");
            				

            }


            }

            otherlv_4=(Token)match(input,49,FOLLOW_2); 

            			newLeafNode(otherlv_4, grammarAccess.getCardinalityAccess().getRightSquareBracketKeyword_4());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleCardinality"


    // $ANTLR start "entryRuleUiOptions"
    // InternalModel2Blockly.g:1605:1: entryRuleUiOptions returns [EObject current=null] : iv_ruleUiOptions= ruleUiOptions EOF ;
    public final EObject entryRuleUiOptions() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUiOptions = null;


        try {
            // InternalModel2Blockly.g:1605:50: (iv_ruleUiOptions= ruleUiOptions EOF )
            // InternalModel2Blockly.g:1606:2: iv_ruleUiOptions= ruleUiOptions EOF
            {
             newCompositeNode(grammarAccess.getUiOptionsRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleUiOptions=ruleUiOptions();

            state._fsp--;

             current =iv_ruleUiOptions; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleUiOptions"


    // $ANTLR start "ruleUiOptions"
    // InternalModel2Blockly.g:1612:1: ruleUiOptions returns [EObject current=null] : ( (otherlv_0= 'widget' ( (lv_widget_1_0= ruleUiWidget ) ) ) | (otherlv_2= 'uiLabel' ( (lv_uiLabel_3_0= RULE_STRING ) ) ) | (otherlv_4= 'help' ( (lv_help_5_0= RULE_STRING ) ) ) | (otherlv_6= 'placeholder' ( (lv_placeholder_7_0= RULE_STRING ) ) ) | (otherlv_8= 'group' ( (lv_group_9_0= RULE_STRING ) ) ) | (otherlv_10= 'order' ( (lv_order_11_0= RULE_INT ) ) ) | ( (lv_uiReadonly_12_0= 'readonly' ) ) | ( (lv_uiHidden_13_0= 'hidden' ) ) | (otherlv_14= 'variant' ( (lv_variant_15_0= ruleUiVariant ) ) ) | (otherlv_16= 'referenceLabelField' ( (lv_referenceLabelField_17_0= RULE_ID ) ) ) )+ ;
    public final EObject ruleUiOptions() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token lv_uiLabel_3_0=null;
        Token otherlv_4=null;
        Token lv_help_5_0=null;
        Token otherlv_6=null;
        Token lv_placeholder_7_0=null;
        Token otherlv_8=null;
        Token lv_group_9_0=null;
        Token otherlv_10=null;
        Token lv_order_11_0=null;
        Token lv_uiReadonly_12_0=null;
        Token lv_uiHidden_13_0=null;
        Token otherlv_14=null;
        Token otherlv_16=null;
        Token lv_referenceLabelField_17_0=null;
        Enumerator lv_widget_1_0 = null;

        Enumerator lv_variant_15_0 = null;



        	enterRule();

        try {
            // InternalModel2Blockly.g:1618:2: ( ( (otherlv_0= 'widget' ( (lv_widget_1_0= ruleUiWidget ) ) ) | (otherlv_2= 'uiLabel' ( (lv_uiLabel_3_0= RULE_STRING ) ) ) | (otherlv_4= 'help' ( (lv_help_5_0= RULE_STRING ) ) ) | (otherlv_6= 'placeholder' ( (lv_placeholder_7_0= RULE_STRING ) ) ) | (otherlv_8= 'group' ( (lv_group_9_0= RULE_STRING ) ) ) | (otherlv_10= 'order' ( (lv_order_11_0= RULE_INT ) ) ) | ( (lv_uiReadonly_12_0= 'readonly' ) ) | ( (lv_uiHidden_13_0= 'hidden' ) ) | (otherlv_14= 'variant' ( (lv_variant_15_0= ruleUiVariant ) ) ) | (otherlv_16= 'referenceLabelField' ( (lv_referenceLabelField_17_0= RULE_ID ) ) ) )+ )
            // InternalModel2Blockly.g:1619:2: ( (otherlv_0= 'widget' ( (lv_widget_1_0= ruleUiWidget ) ) ) | (otherlv_2= 'uiLabel' ( (lv_uiLabel_3_0= RULE_STRING ) ) ) | (otherlv_4= 'help' ( (lv_help_5_0= RULE_STRING ) ) ) | (otherlv_6= 'placeholder' ( (lv_placeholder_7_0= RULE_STRING ) ) ) | (otherlv_8= 'group' ( (lv_group_9_0= RULE_STRING ) ) ) | (otherlv_10= 'order' ( (lv_order_11_0= RULE_INT ) ) ) | ( (lv_uiReadonly_12_0= 'readonly' ) ) | ( (lv_uiHidden_13_0= 'hidden' ) ) | (otherlv_14= 'variant' ( (lv_variant_15_0= ruleUiVariant ) ) ) | (otherlv_16= 'referenceLabelField' ( (lv_referenceLabelField_17_0= RULE_ID ) ) ) )+
            {
            // InternalModel2Blockly.g:1619:2: ( (otherlv_0= 'widget' ( (lv_widget_1_0= ruleUiWidget ) ) ) | (otherlv_2= 'uiLabel' ( (lv_uiLabel_3_0= RULE_STRING ) ) ) | (otherlv_4= 'help' ( (lv_help_5_0= RULE_STRING ) ) ) | (otherlv_6= 'placeholder' ( (lv_placeholder_7_0= RULE_STRING ) ) ) | (otherlv_8= 'group' ( (lv_group_9_0= RULE_STRING ) ) ) | (otherlv_10= 'order' ( (lv_order_11_0= RULE_INT ) ) ) | ( (lv_uiReadonly_12_0= 'readonly' ) ) | ( (lv_uiHidden_13_0= 'hidden' ) ) | (otherlv_14= 'variant' ( (lv_variant_15_0= ruleUiVariant ) ) ) | (otherlv_16= 'referenceLabelField' ( (lv_referenceLabelField_17_0= RULE_ID ) ) ) )+
            int cnt55=0;
            loop55:
            do {
                int alt55=11;
                switch ( input.LA(1) ) {
                case 54:
                    {
                    alt55=1;
                    }
                    break;
                case 55:
                    {
                    alt55=2;
                    }
                    break;
                case 56:
                    {
                    alt55=3;
                    }
                    break;
                case 57:
                    {
                    alt55=4;
                    }
                    break;
                case 58:
                    {
                    alt55=5;
                    }
                    break;
                case 59:
                    {
                    alt55=6;
                    }
                    break;
                case 60:
                    {
                    alt55=7;
                    }
                    break;
                case 61:
                    {
                    alt55=8;
                    }
                    break;
                case 62:
                    {
                    alt55=9;
                    }
                    break;
                case 63:
                    {
                    alt55=10;
                    }
                    break;

                }

                switch (alt55) {
            	case 1 :
            	    // InternalModel2Blockly.g:1620:3: (otherlv_0= 'widget' ( (lv_widget_1_0= ruleUiWidget ) ) )
            	    {
            	    // InternalModel2Blockly.g:1620:3: (otherlv_0= 'widget' ( (lv_widget_1_0= ruleUiWidget ) ) )
            	    // InternalModel2Blockly.g:1621:4: otherlv_0= 'widget' ( (lv_widget_1_0= ruleUiWidget ) )
            	    {
            	    otherlv_0=(Token)match(input,54,FOLLOW_58); 

            	    				newLeafNode(otherlv_0, grammarAccess.getUiOptionsAccess().getWidgetKeyword_0_0());
            	    			
            	    // InternalModel2Blockly.g:1625:4: ( (lv_widget_1_0= ruleUiWidget ) )
            	    // InternalModel2Blockly.g:1626:5: (lv_widget_1_0= ruleUiWidget )
            	    {
            	    // InternalModel2Blockly.g:1626:5: (lv_widget_1_0= ruleUiWidget )
            	    // InternalModel2Blockly.g:1627:6: lv_widget_1_0= ruleUiWidget
            	    {

            	    						newCompositeNode(grammarAccess.getUiOptionsAccess().getWidgetUiWidgetEnumRuleCall_0_1_0());
            	    					
            	    pushFollow(FOLLOW_50);
            	    lv_widget_1_0=ruleUiWidget();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getUiOptionsRule());
            	    						}
            	    						set(
            	    							current,
            	    							"widget",
            	    							lv_widget_1_0,
            	    							"io.github.plortinus.model2blockly.Model2Blockly.UiWidget");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalModel2Blockly.g:1646:3: (otherlv_2= 'uiLabel' ( (lv_uiLabel_3_0= RULE_STRING ) ) )
            	    {
            	    // InternalModel2Blockly.g:1646:3: (otherlv_2= 'uiLabel' ( (lv_uiLabel_3_0= RULE_STRING ) ) )
            	    // InternalModel2Blockly.g:1647:4: otherlv_2= 'uiLabel' ( (lv_uiLabel_3_0= RULE_STRING ) )
            	    {
            	    otherlv_2=(Token)match(input,55,FOLLOW_5); 

            	    				newLeafNode(otherlv_2, grammarAccess.getUiOptionsAccess().getUiLabelKeyword_1_0());
            	    			
            	    // InternalModel2Blockly.g:1651:4: ( (lv_uiLabel_3_0= RULE_STRING ) )
            	    // InternalModel2Blockly.g:1652:5: (lv_uiLabel_3_0= RULE_STRING )
            	    {
            	    // InternalModel2Blockly.g:1652:5: (lv_uiLabel_3_0= RULE_STRING )
            	    // InternalModel2Blockly.g:1653:6: lv_uiLabel_3_0= RULE_STRING
            	    {
            	    lv_uiLabel_3_0=(Token)match(input,RULE_STRING,FOLLOW_50); 

            	    						newLeafNode(lv_uiLabel_3_0, grammarAccess.getUiOptionsAccess().getUiLabelSTRINGTerminalRuleCall_1_1_0());
            	    					

            	    						if (current==null) {
            	    							current = createModelElement(grammarAccess.getUiOptionsRule());
            	    						}
            	    						setWithLastConsumed(
            	    							current,
            	    							"uiLabel",
            	    							lv_uiLabel_3_0,
            	    							"org.eclipse.xtext.common.Terminals.STRING");
            	    					

            	    }


            	    }


            	    }


            	    }
            	    break;
            	case 3 :
            	    // InternalModel2Blockly.g:1671:3: (otherlv_4= 'help' ( (lv_help_5_0= RULE_STRING ) ) )
            	    {
            	    // InternalModel2Blockly.g:1671:3: (otherlv_4= 'help' ( (lv_help_5_0= RULE_STRING ) ) )
            	    // InternalModel2Blockly.g:1672:4: otherlv_4= 'help' ( (lv_help_5_0= RULE_STRING ) )
            	    {
            	    otherlv_4=(Token)match(input,56,FOLLOW_5); 

            	    				newLeafNode(otherlv_4, grammarAccess.getUiOptionsAccess().getHelpKeyword_2_0());
            	    			
            	    // InternalModel2Blockly.g:1676:4: ( (lv_help_5_0= RULE_STRING ) )
            	    // InternalModel2Blockly.g:1677:5: (lv_help_5_0= RULE_STRING )
            	    {
            	    // InternalModel2Blockly.g:1677:5: (lv_help_5_0= RULE_STRING )
            	    // InternalModel2Blockly.g:1678:6: lv_help_5_0= RULE_STRING
            	    {
            	    lv_help_5_0=(Token)match(input,RULE_STRING,FOLLOW_50); 

            	    						newLeafNode(lv_help_5_0, grammarAccess.getUiOptionsAccess().getHelpSTRINGTerminalRuleCall_2_1_0());
            	    					

            	    						if (current==null) {
            	    							current = createModelElement(grammarAccess.getUiOptionsRule());
            	    						}
            	    						setWithLastConsumed(
            	    							current,
            	    							"help",
            	    							lv_help_5_0,
            	    							"org.eclipse.xtext.common.Terminals.STRING");
            	    					

            	    }


            	    }


            	    }


            	    }
            	    break;
            	case 4 :
            	    // InternalModel2Blockly.g:1696:3: (otherlv_6= 'placeholder' ( (lv_placeholder_7_0= RULE_STRING ) ) )
            	    {
            	    // InternalModel2Blockly.g:1696:3: (otherlv_6= 'placeholder' ( (lv_placeholder_7_0= RULE_STRING ) ) )
            	    // InternalModel2Blockly.g:1697:4: otherlv_6= 'placeholder' ( (lv_placeholder_7_0= RULE_STRING ) )
            	    {
            	    otherlv_6=(Token)match(input,57,FOLLOW_5); 

            	    				newLeafNode(otherlv_6, grammarAccess.getUiOptionsAccess().getPlaceholderKeyword_3_0());
            	    			
            	    // InternalModel2Blockly.g:1701:4: ( (lv_placeholder_7_0= RULE_STRING ) )
            	    // InternalModel2Blockly.g:1702:5: (lv_placeholder_7_0= RULE_STRING )
            	    {
            	    // InternalModel2Blockly.g:1702:5: (lv_placeholder_7_0= RULE_STRING )
            	    // InternalModel2Blockly.g:1703:6: lv_placeholder_7_0= RULE_STRING
            	    {
            	    lv_placeholder_7_0=(Token)match(input,RULE_STRING,FOLLOW_50); 

            	    						newLeafNode(lv_placeholder_7_0, grammarAccess.getUiOptionsAccess().getPlaceholderSTRINGTerminalRuleCall_3_1_0());
            	    					

            	    						if (current==null) {
            	    							current = createModelElement(grammarAccess.getUiOptionsRule());
            	    						}
            	    						setWithLastConsumed(
            	    							current,
            	    							"placeholder",
            	    							lv_placeholder_7_0,
            	    							"org.eclipse.xtext.common.Terminals.STRING");
            	    					

            	    }


            	    }


            	    }


            	    }
            	    break;
            	case 5 :
            	    // InternalModel2Blockly.g:1721:3: (otherlv_8= 'group' ( (lv_group_9_0= RULE_STRING ) ) )
            	    {
            	    // InternalModel2Blockly.g:1721:3: (otherlv_8= 'group' ( (lv_group_9_0= RULE_STRING ) ) )
            	    // InternalModel2Blockly.g:1722:4: otherlv_8= 'group' ( (lv_group_9_0= RULE_STRING ) )
            	    {
            	    otherlv_8=(Token)match(input,58,FOLLOW_5); 

            	    				newLeafNode(otherlv_8, grammarAccess.getUiOptionsAccess().getGroupKeyword_4_0());
            	    			
            	    // InternalModel2Blockly.g:1726:4: ( (lv_group_9_0= RULE_STRING ) )
            	    // InternalModel2Blockly.g:1727:5: (lv_group_9_0= RULE_STRING )
            	    {
            	    // InternalModel2Blockly.g:1727:5: (lv_group_9_0= RULE_STRING )
            	    // InternalModel2Blockly.g:1728:6: lv_group_9_0= RULE_STRING
            	    {
            	    lv_group_9_0=(Token)match(input,RULE_STRING,FOLLOW_50); 

            	    						newLeafNode(lv_group_9_0, grammarAccess.getUiOptionsAccess().getGroupSTRINGTerminalRuleCall_4_1_0());
            	    					

            	    						if (current==null) {
            	    							current = createModelElement(grammarAccess.getUiOptionsRule());
            	    						}
            	    						setWithLastConsumed(
            	    							current,
            	    							"group",
            	    							lv_group_9_0,
            	    							"org.eclipse.xtext.common.Terminals.STRING");
            	    					

            	    }


            	    }


            	    }


            	    }
            	    break;
            	case 6 :
            	    // InternalModel2Blockly.g:1746:3: (otherlv_10= 'order' ( (lv_order_11_0= RULE_INT ) ) )
            	    {
            	    // InternalModel2Blockly.g:1746:3: (otherlv_10= 'order' ( (lv_order_11_0= RULE_INT ) ) )
            	    // InternalModel2Blockly.g:1747:4: otherlv_10= 'order' ( (lv_order_11_0= RULE_INT ) )
            	    {
            	    otherlv_10=(Token)match(input,59,FOLLOW_15); 

            	    				newLeafNode(otherlv_10, grammarAccess.getUiOptionsAccess().getOrderKeyword_5_0());
            	    			
            	    // InternalModel2Blockly.g:1751:4: ( (lv_order_11_0= RULE_INT ) )
            	    // InternalModel2Blockly.g:1752:5: (lv_order_11_0= RULE_INT )
            	    {
            	    // InternalModel2Blockly.g:1752:5: (lv_order_11_0= RULE_INT )
            	    // InternalModel2Blockly.g:1753:6: lv_order_11_0= RULE_INT
            	    {
            	    lv_order_11_0=(Token)match(input,RULE_INT,FOLLOW_50); 

            	    						newLeafNode(lv_order_11_0, grammarAccess.getUiOptionsAccess().getOrderINTTerminalRuleCall_5_1_0());
            	    					

            	    						if (current==null) {
            	    							current = createModelElement(grammarAccess.getUiOptionsRule());
            	    						}
            	    						setWithLastConsumed(
            	    							current,
            	    							"order",
            	    							lv_order_11_0,
            	    							"org.eclipse.xtext.common.Terminals.INT");
            	    					

            	    }


            	    }


            	    }


            	    }
            	    break;
            	case 7 :
            	    // InternalModel2Blockly.g:1771:3: ( (lv_uiReadonly_12_0= 'readonly' ) )
            	    {
            	    // InternalModel2Blockly.g:1771:3: ( (lv_uiReadonly_12_0= 'readonly' ) )
            	    // InternalModel2Blockly.g:1772:4: (lv_uiReadonly_12_0= 'readonly' )
            	    {
            	    // InternalModel2Blockly.g:1772:4: (lv_uiReadonly_12_0= 'readonly' )
            	    // InternalModel2Blockly.g:1773:5: lv_uiReadonly_12_0= 'readonly'
            	    {
            	    lv_uiReadonly_12_0=(Token)match(input,60,FOLLOW_50); 

            	    					newLeafNode(lv_uiReadonly_12_0, grammarAccess.getUiOptionsAccess().getUiReadonlyReadonlyKeyword_6_0());
            	    				

            	    					if (current==null) {
            	    						current = createModelElement(grammarAccess.getUiOptionsRule());
            	    					}
            	    					setWithLastConsumed(current, "uiReadonly", lv_uiReadonly_12_0 != null, "readonly");
            	    				

            	    }


            	    }


            	    }
            	    break;
            	case 8 :
            	    // InternalModel2Blockly.g:1786:3: ( (lv_uiHidden_13_0= 'hidden' ) )
            	    {
            	    // InternalModel2Blockly.g:1786:3: ( (lv_uiHidden_13_0= 'hidden' ) )
            	    // InternalModel2Blockly.g:1787:4: (lv_uiHidden_13_0= 'hidden' )
            	    {
            	    // InternalModel2Blockly.g:1787:4: (lv_uiHidden_13_0= 'hidden' )
            	    // InternalModel2Blockly.g:1788:5: lv_uiHidden_13_0= 'hidden'
            	    {
            	    lv_uiHidden_13_0=(Token)match(input,61,FOLLOW_50); 

            	    					newLeafNode(lv_uiHidden_13_0, grammarAccess.getUiOptionsAccess().getUiHiddenHiddenKeyword_7_0());
            	    				

            	    					if (current==null) {
            	    						current = createModelElement(grammarAccess.getUiOptionsRule());
            	    					}
            	    					setWithLastConsumed(current, "uiHidden", lv_uiHidden_13_0 != null, "hidden");
            	    				

            	    }


            	    }


            	    }
            	    break;
            	case 9 :
            	    // InternalModel2Blockly.g:1801:3: (otherlv_14= 'variant' ( (lv_variant_15_0= ruleUiVariant ) ) )
            	    {
            	    // InternalModel2Blockly.g:1801:3: (otherlv_14= 'variant' ( (lv_variant_15_0= ruleUiVariant ) ) )
            	    // InternalModel2Blockly.g:1802:4: otherlv_14= 'variant' ( (lv_variant_15_0= ruleUiVariant ) )
            	    {
            	    otherlv_14=(Token)match(input,62,FOLLOW_59); 

            	    				newLeafNode(otherlv_14, grammarAccess.getUiOptionsAccess().getVariantKeyword_8_0());
            	    			
            	    // InternalModel2Blockly.g:1806:4: ( (lv_variant_15_0= ruleUiVariant ) )
            	    // InternalModel2Blockly.g:1807:5: (lv_variant_15_0= ruleUiVariant )
            	    {
            	    // InternalModel2Blockly.g:1807:5: (lv_variant_15_0= ruleUiVariant )
            	    // InternalModel2Blockly.g:1808:6: lv_variant_15_0= ruleUiVariant
            	    {

            	    						newCompositeNode(grammarAccess.getUiOptionsAccess().getVariantUiVariantEnumRuleCall_8_1_0());
            	    					
            	    pushFollow(FOLLOW_50);
            	    lv_variant_15_0=ruleUiVariant();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getUiOptionsRule());
            	    						}
            	    						set(
            	    							current,
            	    							"variant",
            	    							lv_variant_15_0,
            	    							"io.github.plortinus.model2blockly.Model2Blockly.UiVariant");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }


            	    }
            	    break;
            	case 10 :
            	    // InternalModel2Blockly.g:1827:3: (otherlv_16= 'referenceLabelField' ( (lv_referenceLabelField_17_0= RULE_ID ) ) )
            	    {
            	    // InternalModel2Blockly.g:1827:3: (otherlv_16= 'referenceLabelField' ( (lv_referenceLabelField_17_0= RULE_ID ) ) )
            	    // InternalModel2Blockly.g:1828:4: otherlv_16= 'referenceLabelField' ( (lv_referenceLabelField_17_0= RULE_ID ) )
            	    {
            	    otherlv_16=(Token)match(input,63,FOLLOW_3); 

            	    				newLeafNode(otherlv_16, grammarAccess.getUiOptionsAccess().getReferenceLabelFieldKeyword_9_0());
            	    			
            	    // InternalModel2Blockly.g:1832:4: ( (lv_referenceLabelField_17_0= RULE_ID ) )
            	    // InternalModel2Blockly.g:1833:5: (lv_referenceLabelField_17_0= RULE_ID )
            	    {
            	    // InternalModel2Blockly.g:1833:5: (lv_referenceLabelField_17_0= RULE_ID )
            	    // InternalModel2Blockly.g:1834:6: lv_referenceLabelField_17_0= RULE_ID
            	    {
            	    lv_referenceLabelField_17_0=(Token)match(input,RULE_ID,FOLLOW_50); 

            	    						newLeafNode(lv_referenceLabelField_17_0, grammarAccess.getUiOptionsAccess().getReferenceLabelFieldIDTerminalRuleCall_9_1_0());
            	    					

            	    						if (current==null) {
            	    							current = createModelElement(grammarAccess.getUiOptionsRule());
            	    						}
            	    						setWithLastConsumed(
            	    							current,
            	    							"referenceLabelField",
            	    							lv_referenceLabelField_17_0,
            	    							"org.eclipse.xtext.common.Terminals.ID");
            	    					

            	    }


            	    }


            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt55 >= 1 ) break loop55;
                        EarlyExitException eee =
                            new EarlyExitException(55, input);
                        throw eee;
                }
                cnt55++;
            } while (true);


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleUiOptions"


    // $ANTLR start "entryRuleAttributeType"
    // InternalModel2Blockly.g:1855:1: entryRuleAttributeType returns [EObject current=null] : iv_ruleAttributeType= ruleAttributeType EOF ;
    public final EObject entryRuleAttributeType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAttributeType = null;


        try {
            // InternalModel2Blockly.g:1855:54: (iv_ruleAttributeType= ruleAttributeType EOF )
            // InternalModel2Blockly.g:1856:2: iv_ruleAttributeType= ruleAttributeType EOF
            {
             newCompositeNode(grammarAccess.getAttributeTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAttributeType=ruleAttributeType();

            state._fsp--;

             current =iv_ruleAttributeType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAttributeType"


    // $ANTLR start "ruleAttributeType"
    // InternalModel2Blockly.g:1862:1: ruleAttributeType returns [EObject current=null] : (this_SimpleType_0= ruleSimpleType | this_EnumType_1= ruleEnumType ) ;
    public final EObject ruleAttributeType() throws RecognitionException {
        EObject current = null;

        EObject this_SimpleType_0 = null;

        EObject this_EnumType_1 = null;



        	enterRule();

        try {
            // InternalModel2Blockly.g:1868:2: ( (this_SimpleType_0= ruleSimpleType | this_EnumType_1= ruleEnumType ) )
            // InternalModel2Blockly.g:1869:2: (this_SimpleType_0= ruleSimpleType | this_EnumType_1= ruleEnumType )
            {
            // InternalModel2Blockly.g:1869:2: (this_SimpleType_0= ruleSimpleType | this_EnumType_1= ruleEnumType )
            int alt56=2;
            int LA56_0 = input.LA(1);

            if ( ((LA56_0>=16 && LA56_0<=17)||(LA56_0>=83 && LA56_0<=84)||(LA56_0>=90 && LA56_0<=93)) ) {
                alt56=1;
            }
            else if ( (LA56_0==64) ) {
                alt56=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 56, 0, input);

                throw nvae;
            }
            switch (alt56) {
                case 1 :
                    // InternalModel2Blockly.g:1870:3: this_SimpleType_0= ruleSimpleType
                    {

                    			newCompositeNode(grammarAccess.getAttributeTypeAccess().getSimpleTypeParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_SimpleType_0=ruleSimpleType();

                    state._fsp--;


                    			current = this_SimpleType_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalModel2Blockly.g:1879:3: this_EnumType_1= ruleEnumType
                    {

                    			newCompositeNode(grammarAccess.getAttributeTypeAccess().getEnumTypeParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_EnumType_1=ruleEnumType();

                    state._fsp--;


                    			current = this_EnumType_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAttributeType"


    // $ANTLR start "entryRuleSimpleType"
    // InternalModel2Blockly.g:1891:1: entryRuleSimpleType returns [EObject current=null] : iv_ruleSimpleType= ruleSimpleType EOF ;
    public final EObject entryRuleSimpleType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSimpleType = null;


        try {
            // InternalModel2Blockly.g:1891:51: (iv_ruleSimpleType= ruleSimpleType EOF )
            // InternalModel2Blockly.g:1892:2: iv_ruleSimpleType= ruleSimpleType EOF
            {
             newCompositeNode(grammarAccess.getSimpleTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSimpleType=ruleSimpleType();

            state._fsp--;

             current =iv_ruleSimpleType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSimpleType"


    // $ANTLR start "ruleSimpleType"
    // InternalModel2Blockly.g:1898:1: ruleSimpleType returns [EObject current=null] : ( (lv_typeName_0_0= ruleSimpleTypeName ) ) ;
    public final EObject ruleSimpleType() throws RecognitionException {
        EObject current = null;

        Enumerator lv_typeName_0_0 = null;



        	enterRule();

        try {
            // InternalModel2Blockly.g:1904:2: ( ( (lv_typeName_0_0= ruleSimpleTypeName ) ) )
            // InternalModel2Blockly.g:1905:2: ( (lv_typeName_0_0= ruleSimpleTypeName ) )
            {
            // InternalModel2Blockly.g:1905:2: ( (lv_typeName_0_0= ruleSimpleTypeName ) )
            // InternalModel2Blockly.g:1906:3: (lv_typeName_0_0= ruleSimpleTypeName )
            {
            // InternalModel2Blockly.g:1906:3: (lv_typeName_0_0= ruleSimpleTypeName )
            // InternalModel2Blockly.g:1907:4: lv_typeName_0_0= ruleSimpleTypeName
            {

            				newCompositeNode(grammarAccess.getSimpleTypeAccess().getTypeNameSimpleTypeNameEnumRuleCall_0());
            			
            pushFollow(FOLLOW_2);
            lv_typeName_0_0=ruleSimpleTypeName();

            state._fsp--;


            				if (current==null) {
            					current = createModelElementForParent(grammarAccess.getSimpleTypeRule());
            				}
            				set(
            					current,
            					"typeName",
            					lv_typeName_0_0,
            					"io.github.plortinus.model2blockly.Model2Blockly.SimpleTypeName");
            				afterParserOrEnumRuleCall();
            			

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSimpleType"


    // $ANTLR start "entryRuleEnumType"
    // InternalModel2Blockly.g:1927:1: entryRuleEnumType returns [EObject current=null] : iv_ruleEnumType= ruleEnumType EOF ;
    public final EObject entryRuleEnumType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEnumType = null;


        try {
            // InternalModel2Blockly.g:1927:49: (iv_ruleEnumType= ruleEnumType EOF )
            // InternalModel2Blockly.g:1928:2: iv_ruleEnumType= ruleEnumType EOF
            {
             newCompositeNode(grammarAccess.getEnumTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleEnumType=ruleEnumType();

            state._fsp--;

             current =iv_ruleEnumType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleEnumType"


    // $ANTLR start "ruleEnumType"
    // InternalModel2Blockly.g:1934:1: ruleEnumType returns [EObject current=null] : (otherlv_0= 'enum' otherlv_1= '{' ( (lv_literals_2_0= ruleEnumLiteral ) ) (otherlv_3= ',' ( (lv_literals_4_0= ruleEnumLiteral ) ) )* otherlv_5= '}' ) ;
    public final EObject ruleEnumType() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_literals_2_0 = null;

        EObject lv_literals_4_0 = null;



        	enterRule();

        try {
            // InternalModel2Blockly.g:1940:2: ( (otherlv_0= 'enum' otherlv_1= '{' ( (lv_literals_2_0= ruleEnumLiteral ) ) (otherlv_3= ',' ( (lv_literals_4_0= ruleEnumLiteral ) ) )* otherlv_5= '}' ) )
            // InternalModel2Blockly.g:1941:2: (otherlv_0= 'enum' otherlv_1= '{' ( (lv_literals_2_0= ruleEnumLiteral ) ) (otherlv_3= ',' ( (lv_literals_4_0= ruleEnumLiteral ) ) )* otherlv_5= '}' )
            {
            // InternalModel2Blockly.g:1941:2: (otherlv_0= 'enum' otherlv_1= '{' ( (lv_literals_2_0= ruleEnumLiteral ) ) (otherlv_3= ',' ( (lv_literals_4_0= ruleEnumLiteral ) ) )* otherlv_5= '}' )
            // InternalModel2Blockly.g:1942:3: otherlv_0= 'enum' otherlv_1= '{' ( (lv_literals_2_0= ruleEnumLiteral ) ) (otherlv_3= ',' ( (lv_literals_4_0= ruleEnumLiteral ) ) )* otherlv_5= '}'
            {
            otherlv_0=(Token)match(input,64,FOLLOW_32); 

            			newLeafNode(otherlv_0, grammarAccess.getEnumTypeAccess().getEnumKeyword_0());
            		
            otherlv_1=(Token)match(input,18,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getEnumTypeAccess().getLeftCurlyBracketKeyword_1());
            		
            // InternalModel2Blockly.g:1950:3: ( (lv_literals_2_0= ruleEnumLiteral ) )
            // InternalModel2Blockly.g:1951:4: (lv_literals_2_0= ruleEnumLiteral )
            {
            // InternalModel2Blockly.g:1951:4: (lv_literals_2_0= ruleEnumLiteral )
            // InternalModel2Blockly.g:1952:5: lv_literals_2_0= ruleEnumLiteral
            {

            					newCompositeNode(grammarAccess.getEnumTypeAccess().getLiteralsEnumLiteralParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_60);
            lv_literals_2_0=ruleEnumLiteral();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getEnumTypeRule());
            					}
            					add(
            						current,
            						"literals",
            						lv_literals_2_0,
            						"io.github.plortinus.model2blockly.Model2Blockly.EnumLiteral");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalModel2Blockly.g:1969:3: (otherlv_3= ',' ( (lv_literals_4_0= ruleEnumLiteral ) ) )*
            loop57:
            do {
                int alt57=2;
                int LA57_0 = input.LA(1);

                if ( (LA57_0==65) ) {
                    alt57=1;
                }


                switch (alt57) {
            	case 1 :
            	    // InternalModel2Blockly.g:1970:4: otherlv_3= ',' ( (lv_literals_4_0= ruleEnumLiteral ) )
            	    {
            	    otherlv_3=(Token)match(input,65,FOLLOW_3); 

            	    				newLeafNode(otherlv_3, grammarAccess.getEnumTypeAccess().getCommaKeyword_3_0());
            	    			
            	    // InternalModel2Blockly.g:1974:4: ( (lv_literals_4_0= ruleEnumLiteral ) )
            	    // InternalModel2Blockly.g:1975:5: (lv_literals_4_0= ruleEnumLiteral )
            	    {
            	    // InternalModel2Blockly.g:1975:5: (lv_literals_4_0= ruleEnumLiteral )
            	    // InternalModel2Blockly.g:1976:6: lv_literals_4_0= ruleEnumLiteral
            	    {

            	    						newCompositeNode(grammarAccess.getEnumTypeAccess().getLiteralsEnumLiteralParserRuleCall_3_1_0());
            	    					
            	    pushFollow(FOLLOW_60);
            	    lv_literals_4_0=ruleEnumLiteral();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getEnumTypeRule());
            	    						}
            	    						add(
            	    							current,
            	    							"literals",
            	    							lv_literals_4_0,
            	    							"io.github.plortinus.model2blockly.Model2Blockly.EnumLiteral");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop57;
                }
            } while (true);

            otherlv_5=(Token)match(input,19,FOLLOW_2); 

            			newLeafNode(otherlv_5, grammarAccess.getEnumTypeAccess().getRightCurlyBracketKeyword_4());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleEnumType"


    // $ANTLR start "entryRuleEnumLiteral"
    // InternalModel2Blockly.g:2002:1: entryRuleEnumLiteral returns [EObject current=null] : iv_ruleEnumLiteral= ruleEnumLiteral EOF ;
    public final EObject entryRuleEnumLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEnumLiteral = null;


        try {
            // InternalModel2Blockly.g:2002:52: (iv_ruleEnumLiteral= ruleEnumLiteral EOF )
            // InternalModel2Blockly.g:2003:2: iv_ruleEnumLiteral= ruleEnumLiteral EOF
            {
             newCompositeNode(grammarAccess.getEnumLiteralRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleEnumLiteral=ruleEnumLiteral();

            state._fsp--;

             current =iv_ruleEnumLiteral; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleEnumLiteral"


    // $ANTLR start "ruleEnumLiteral"
    // InternalModel2Blockly.g:2009:1: ruleEnumLiteral returns [EObject current=null] : ( ( (lv_name_0_0= RULE_ID ) ) (otherlv_1= '=' ( (lv_label_2_0= RULE_STRING ) ) )? ) ;
    public final EObject ruleEnumLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        Token otherlv_1=null;
        Token lv_label_2_0=null;


        	enterRule();

        try {
            // InternalModel2Blockly.g:2015:2: ( ( ( (lv_name_0_0= RULE_ID ) ) (otherlv_1= '=' ( (lv_label_2_0= RULE_STRING ) ) )? ) )
            // InternalModel2Blockly.g:2016:2: ( ( (lv_name_0_0= RULE_ID ) ) (otherlv_1= '=' ( (lv_label_2_0= RULE_STRING ) ) )? )
            {
            // InternalModel2Blockly.g:2016:2: ( ( (lv_name_0_0= RULE_ID ) ) (otherlv_1= '=' ( (lv_label_2_0= RULE_STRING ) ) )? )
            // InternalModel2Blockly.g:2017:3: ( (lv_name_0_0= RULE_ID ) ) (otherlv_1= '=' ( (lv_label_2_0= RULE_STRING ) ) )?
            {
            // InternalModel2Blockly.g:2017:3: ( (lv_name_0_0= RULE_ID ) )
            // InternalModel2Blockly.g:2018:4: (lv_name_0_0= RULE_ID )
            {
            // InternalModel2Blockly.g:2018:4: (lv_name_0_0= RULE_ID )
            // InternalModel2Blockly.g:2019:5: lv_name_0_0= RULE_ID
            {
            lv_name_0_0=(Token)match(input,RULE_ID,FOLLOW_61); 

            					newLeafNode(lv_name_0_0, grammarAccess.getEnumLiteralAccess().getNameIDTerminalRuleCall_0_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getEnumLiteralRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_0_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            // InternalModel2Blockly.g:2035:3: (otherlv_1= '=' ( (lv_label_2_0= RULE_STRING ) ) )?
            int alt58=2;
            int LA58_0 = input.LA(1);

            if ( (LA58_0==66) ) {
                alt58=1;
            }
            switch (alt58) {
                case 1 :
                    // InternalModel2Blockly.g:2036:4: otherlv_1= '=' ( (lv_label_2_0= RULE_STRING ) )
                    {
                    otherlv_1=(Token)match(input,66,FOLLOW_5); 

                    				newLeafNode(otherlv_1, grammarAccess.getEnumLiteralAccess().getEqualsSignKeyword_1_0());
                    			
                    // InternalModel2Blockly.g:2040:4: ( (lv_label_2_0= RULE_STRING ) )
                    // InternalModel2Blockly.g:2041:5: (lv_label_2_0= RULE_STRING )
                    {
                    // InternalModel2Blockly.g:2041:5: (lv_label_2_0= RULE_STRING )
                    // InternalModel2Blockly.g:2042:6: lv_label_2_0= RULE_STRING
                    {
                    lv_label_2_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

                    						newLeafNode(lv_label_2_0, grammarAccess.getEnumLiteralAccess().getLabelSTRINGTerminalRuleCall_1_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getEnumLiteralRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"label",
                    							lv_label_2_0,
                    							"org.eclipse.xtext.common.Terminals.STRING");
                    					

                    }


                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleEnumLiteral"


    // $ANTLR start "entryRuleConstraintDef"
    // InternalModel2Blockly.g:2063:1: entryRuleConstraintDef returns [EObject current=null] : iv_ruleConstraintDef= ruleConstraintDef EOF ;
    public final EObject entryRuleConstraintDef() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConstraintDef = null;


        try {
            // InternalModel2Blockly.g:2063:54: (iv_ruleConstraintDef= ruleConstraintDef EOF )
            // InternalModel2Blockly.g:2064:2: iv_ruleConstraintDef= ruleConstraintDef EOF
            {
             newCompositeNode(grammarAccess.getConstraintDefRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleConstraintDef=ruleConstraintDef();

            state._fsp--;

             current =iv_ruleConstraintDef; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleConstraintDef"


    // $ANTLR start "ruleConstraintDef"
    // InternalModel2Blockly.g:2070:1: ruleConstraintDef returns [EObject current=null] : (otherlv_0= 'constraint' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= 'on' ( (otherlv_3= RULE_ID ) ) otherlv_4= ':' otherlv_5= 'must' otherlv_6= 'follow' ( (otherlv_7= RULE_ID ) ) ) ;
    public final EObject ruleConstraintDef() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token otherlv_7=null;


        	enterRule();

        try {
            // InternalModel2Blockly.g:2076:2: ( (otherlv_0= 'constraint' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= 'on' ( (otherlv_3= RULE_ID ) ) otherlv_4= ':' otherlv_5= 'must' otherlv_6= 'follow' ( (otherlv_7= RULE_ID ) ) ) )
            // InternalModel2Blockly.g:2077:2: (otherlv_0= 'constraint' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= 'on' ( (otherlv_3= RULE_ID ) ) otherlv_4= ':' otherlv_5= 'must' otherlv_6= 'follow' ( (otherlv_7= RULE_ID ) ) )
            {
            // InternalModel2Blockly.g:2077:2: (otherlv_0= 'constraint' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= 'on' ( (otherlv_3= RULE_ID ) ) otherlv_4= ':' otherlv_5= 'must' otherlv_6= 'follow' ( (otherlv_7= RULE_ID ) ) )
            // InternalModel2Blockly.g:2078:3: otherlv_0= 'constraint' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= 'on' ( (otherlv_3= RULE_ID ) ) otherlv_4= ':' otherlv_5= 'must' otherlv_6= 'follow' ( (otherlv_7= RULE_ID ) )
            {
            otherlv_0=(Token)match(input,67,FOLLOW_3); 

            			newLeafNode(otherlv_0, grammarAccess.getConstraintDefAccess().getConstraintKeyword_0());
            		
            // InternalModel2Blockly.g:2082:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalModel2Blockly.g:2083:4: (lv_name_1_0= RULE_ID )
            {
            // InternalModel2Blockly.g:2083:4: (lv_name_1_0= RULE_ID )
            // InternalModel2Blockly.g:2084:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_62); 

            					newLeafNode(lv_name_1_0, grammarAccess.getConstraintDefAccess().getNameIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getConstraintDefRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_2=(Token)match(input,68,FOLLOW_3); 

            			newLeafNode(otherlv_2, grammarAccess.getConstraintDefAccess().getOnKeyword_2());
            		
            // InternalModel2Blockly.g:2104:3: ( (otherlv_3= RULE_ID ) )
            // InternalModel2Blockly.g:2105:4: (otherlv_3= RULE_ID )
            {
            // InternalModel2Blockly.g:2105:4: (otherlv_3= RULE_ID )
            // InternalModel2Blockly.g:2106:5: otherlv_3= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getConstraintDefRule());
            					}
            				
            otherlv_3=(Token)match(input,RULE_ID,FOLLOW_34); 

            					newLeafNode(otherlv_3, grammarAccess.getConstraintDefAccess().getTargetClassDefCrossReference_3_0());
            				

            }


            }

            otherlv_4=(Token)match(input,32,FOLLOW_63); 

            			newLeafNode(otherlv_4, grammarAccess.getConstraintDefAccess().getColonKeyword_4());
            		
            otherlv_5=(Token)match(input,69,FOLLOW_64); 

            			newLeafNode(otherlv_5, grammarAccess.getConstraintDefAccess().getMustKeyword_5());
            		
            otherlv_6=(Token)match(input,70,FOLLOW_3); 

            			newLeafNode(otherlv_6, grammarAccess.getConstraintDefAccess().getFollowKeyword_6());
            		
            // InternalModel2Blockly.g:2129:3: ( (otherlv_7= RULE_ID ) )
            // InternalModel2Blockly.g:2130:4: (otherlv_7= RULE_ID )
            {
            // InternalModel2Blockly.g:2130:4: (otherlv_7= RULE_ID )
            // InternalModel2Blockly.g:2131:5: otherlv_7= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getConstraintDefRule());
            					}
            				
            otherlv_7=(Token)match(input,RULE_ID,FOLLOW_2); 

            					newLeafNode(otherlv_7, grammarAccess.getConstraintDefAccess().getPredecessorClassDefCrossReference_7_0());
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleConstraintDef"


    // $ANTLR start "entryRuleValidationDef"
    // InternalModel2Blockly.g:2146:1: entryRuleValidationDef returns [EObject current=null] : iv_ruleValidationDef= ruleValidationDef EOF ;
    public final EObject entryRuleValidationDef() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleValidationDef = null;


        try {
            // InternalModel2Blockly.g:2146:54: (iv_ruleValidationDef= ruleValidationDef EOF )
            // InternalModel2Blockly.g:2147:2: iv_ruleValidationDef= ruleValidationDef EOF
            {
             newCompositeNode(grammarAccess.getValidationDefRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleValidationDef=ruleValidationDef();

            state._fsp--;

             current =iv_ruleValidationDef; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleValidationDef"


    // $ANTLR start "ruleValidationDef"
    // InternalModel2Blockly.g:2153:1: ruleValidationDef returns [EObject current=null] : (otherlv_0= 'validation' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= 'on' ( (otherlv_3= RULE_ID ) ) otherlv_4= ':' ( (lv_kind_5_0= ruleValidationKind ) ) ( (lv_expression_6_0= RULE_STRING ) ) (otherlv_7= 'errorMessage' ( (lv_message_8_0= RULE_STRING ) ) )? ) ;
    public final EObject ruleValidationDef() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token lv_expression_6_0=null;
        Token otherlv_7=null;
        Token lv_message_8_0=null;
        Enumerator lv_kind_5_0 = null;



        	enterRule();

        try {
            // InternalModel2Blockly.g:2159:2: ( (otherlv_0= 'validation' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= 'on' ( (otherlv_3= RULE_ID ) ) otherlv_4= ':' ( (lv_kind_5_0= ruleValidationKind ) ) ( (lv_expression_6_0= RULE_STRING ) ) (otherlv_7= 'errorMessage' ( (lv_message_8_0= RULE_STRING ) ) )? ) )
            // InternalModel2Blockly.g:2160:2: (otherlv_0= 'validation' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= 'on' ( (otherlv_3= RULE_ID ) ) otherlv_4= ':' ( (lv_kind_5_0= ruleValidationKind ) ) ( (lv_expression_6_0= RULE_STRING ) ) (otherlv_7= 'errorMessage' ( (lv_message_8_0= RULE_STRING ) ) )? )
            {
            // InternalModel2Blockly.g:2160:2: (otherlv_0= 'validation' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= 'on' ( (otherlv_3= RULE_ID ) ) otherlv_4= ':' ( (lv_kind_5_0= ruleValidationKind ) ) ( (lv_expression_6_0= RULE_STRING ) ) (otherlv_7= 'errorMessage' ( (lv_message_8_0= RULE_STRING ) ) )? )
            // InternalModel2Blockly.g:2161:3: otherlv_0= 'validation' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= 'on' ( (otherlv_3= RULE_ID ) ) otherlv_4= ':' ( (lv_kind_5_0= ruleValidationKind ) ) ( (lv_expression_6_0= RULE_STRING ) ) (otherlv_7= 'errorMessage' ( (lv_message_8_0= RULE_STRING ) ) )?
            {
            otherlv_0=(Token)match(input,71,FOLLOW_3); 

            			newLeafNode(otherlv_0, grammarAccess.getValidationDefAccess().getValidationKeyword_0());
            		
            // InternalModel2Blockly.g:2165:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalModel2Blockly.g:2166:4: (lv_name_1_0= RULE_ID )
            {
            // InternalModel2Blockly.g:2166:4: (lv_name_1_0= RULE_ID )
            // InternalModel2Blockly.g:2167:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_62); 

            					newLeafNode(lv_name_1_0, grammarAccess.getValidationDefAccess().getNameIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getValidationDefRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_2=(Token)match(input,68,FOLLOW_3); 

            			newLeafNode(otherlv_2, grammarAccess.getValidationDefAccess().getOnKeyword_2());
            		
            // InternalModel2Blockly.g:2187:3: ( (otherlv_3= RULE_ID ) )
            // InternalModel2Blockly.g:2188:4: (otherlv_3= RULE_ID )
            {
            // InternalModel2Blockly.g:2188:4: (otherlv_3= RULE_ID )
            // InternalModel2Blockly.g:2189:5: otherlv_3= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getValidationDefRule());
            					}
            				
            otherlv_3=(Token)match(input,RULE_ID,FOLLOW_34); 

            					newLeafNode(otherlv_3, grammarAccess.getValidationDefAccess().getTargetClassDefCrossReference_3_0());
            				

            }


            }

            otherlv_4=(Token)match(input,32,FOLLOW_65); 

            			newLeafNode(otherlv_4, grammarAccess.getValidationDefAccess().getColonKeyword_4());
            		
            // InternalModel2Blockly.g:2204:3: ( (lv_kind_5_0= ruleValidationKind ) )
            // InternalModel2Blockly.g:2205:4: (lv_kind_5_0= ruleValidationKind )
            {
            // InternalModel2Blockly.g:2205:4: (lv_kind_5_0= ruleValidationKind )
            // InternalModel2Blockly.g:2206:5: lv_kind_5_0= ruleValidationKind
            {

            					newCompositeNode(grammarAccess.getValidationDefAccess().getKindValidationKindEnumRuleCall_5_0());
            				
            pushFollow(FOLLOW_5);
            lv_kind_5_0=ruleValidationKind();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getValidationDefRule());
            					}
            					set(
            						current,
            						"kind",
            						lv_kind_5_0,
            						"io.github.plortinus.model2blockly.Model2Blockly.ValidationKind");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalModel2Blockly.g:2223:3: ( (lv_expression_6_0= RULE_STRING ) )
            // InternalModel2Blockly.g:2224:4: (lv_expression_6_0= RULE_STRING )
            {
            // InternalModel2Blockly.g:2224:4: (lv_expression_6_0= RULE_STRING )
            // InternalModel2Blockly.g:2225:5: lv_expression_6_0= RULE_STRING
            {
            lv_expression_6_0=(Token)match(input,RULE_STRING,FOLLOW_66); 

            					newLeafNode(lv_expression_6_0, grammarAccess.getValidationDefAccess().getExpressionSTRINGTerminalRuleCall_6_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getValidationDefRule());
            					}
            					setWithLastConsumed(
            						current,
            						"expression",
            						lv_expression_6_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            // InternalModel2Blockly.g:2241:3: (otherlv_7= 'errorMessage' ( (lv_message_8_0= RULE_STRING ) ) )?
            int alt59=2;
            int LA59_0 = input.LA(1);

            if ( (LA59_0==72) ) {
                alt59=1;
            }
            switch (alt59) {
                case 1 :
                    // InternalModel2Blockly.g:2242:4: otherlv_7= 'errorMessage' ( (lv_message_8_0= RULE_STRING ) )
                    {
                    otherlv_7=(Token)match(input,72,FOLLOW_5); 

                    				newLeafNode(otherlv_7, grammarAccess.getValidationDefAccess().getErrorMessageKeyword_7_0());
                    			
                    // InternalModel2Blockly.g:2246:4: ( (lv_message_8_0= RULE_STRING ) )
                    // InternalModel2Blockly.g:2247:5: (lv_message_8_0= RULE_STRING )
                    {
                    // InternalModel2Blockly.g:2247:5: (lv_message_8_0= RULE_STRING )
                    // InternalModel2Blockly.g:2248:6: lv_message_8_0= RULE_STRING
                    {
                    lv_message_8_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

                    						newLeafNode(lv_message_8_0, grammarAccess.getValidationDefAccess().getMessageSTRINGTerminalRuleCall_7_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getValidationDefRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"message",
                    							lv_message_8_0,
                    							"org.eclipse.xtext.common.Terminals.STRING");
                    					

                    }


                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleValidationDef"


    // $ANTLR start "entryRuleWorkspaceConfig"
    // InternalModel2Blockly.g:2269:1: entryRuleWorkspaceConfig returns [EObject current=null] : iv_ruleWorkspaceConfig= ruleWorkspaceConfig EOF ;
    public final EObject entryRuleWorkspaceConfig() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWorkspaceConfig = null;


        try {
            // InternalModel2Blockly.g:2269:56: (iv_ruleWorkspaceConfig= ruleWorkspaceConfig EOF )
            // InternalModel2Blockly.g:2270:2: iv_ruleWorkspaceConfig= ruleWorkspaceConfig EOF
            {
             newCompositeNode(grammarAccess.getWorkspaceConfigRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleWorkspaceConfig=ruleWorkspaceConfig();

            state._fsp--;

             current =iv_ruleWorkspaceConfig; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleWorkspaceConfig"


    // $ANTLR start "ruleWorkspaceConfig"
    // InternalModel2Blockly.g:2276:1: ruleWorkspaceConfig returns [EObject current=null] : (otherlv_0= 'workspace' otherlv_1= '{' ( (lv_options_2_0= ruleWorkspaceOption ) )* otherlv_3= '}' ) ;
    public final EObject ruleWorkspaceConfig() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_options_2_0 = null;



        	enterRule();

        try {
            // InternalModel2Blockly.g:2282:2: ( (otherlv_0= 'workspace' otherlv_1= '{' ( (lv_options_2_0= ruleWorkspaceOption ) )* otherlv_3= '}' ) )
            // InternalModel2Blockly.g:2283:2: (otherlv_0= 'workspace' otherlv_1= '{' ( (lv_options_2_0= ruleWorkspaceOption ) )* otherlv_3= '}' )
            {
            // InternalModel2Blockly.g:2283:2: (otherlv_0= 'workspace' otherlv_1= '{' ( (lv_options_2_0= ruleWorkspaceOption ) )* otherlv_3= '}' )
            // InternalModel2Blockly.g:2284:3: otherlv_0= 'workspace' otherlv_1= '{' ( (lv_options_2_0= ruleWorkspaceOption ) )* otherlv_3= '}'
            {
            otherlv_0=(Token)match(input,73,FOLLOW_32); 

            			newLeafNode(otherlv_0, grammarAccess.getWorkspaceConfigAccess().getWorkspaceKeyword_0());
            		
            otherlv_1=(Token)match(input,18,FOLLOW_67); 

            			newLeafNode(otherlv_1, grammarAccess.getWorkspaceConfigAccess().getLeftCurlyBracketKeyword_1());
            		
            // InternalModel2Blockly.g:2292:3: ( (lv_options_2_0= ruleWorkspaceOption ) )*
            loop60:
            do {
                int alt60=2;
                int LA60_0 = input.LA(1);

                if ( (LA60_0==RULE_ID) ) {
                    alt60=1;
                }


                switch (alt60) {
            	case 1 :
            	    // InternalModel2Blockly.g:2293:4: (lv_options_2_0= ruleWorkspaceOption )
            	    {
            	    // InternalModel2Blockly.g:2293:4: (lv_options_2_0= ruleWorkspaceOption )
            	    // InternalModel2Blockly.g:2294:5: lv_options_2_0= ruleWorkspaceOption
            	    {

            	    					newCompositeNode(grammarAccess.getWorkspaceConfigAccess().getOptionsWorkspaceOptionParserRuleCall_2_0());
            	    				
            	    pushFollow(FOLLOW_67);
            	    lv_options_2_0=ruleWorkspaceOption();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getWorkspaceConfigRule());
            	    					}
            	    					add(
            	    						current,
            	    						"options",
            	    						lv_options_2_0,
            	    						"io.github.plortinus.model2blockly.Model2Blockly.WorkspaceOption");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop60;
                }
            } while (true);

            otherlv_3=(Token)match(input,19,FOLLOW_2); 

            			newLeafNode(otherlv_3, grammarAccess.getWorkspaceConfigAccess().getRightCurlyBracketKeyword_3());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleWorkspaceConfig"


    // $ANTLR start "entryRuleWorkspaceOption"
    // InternalModel2Blockly.g:2319:1: entryRuleWorkspaceOption returns [EObject current=null] : iv_ruleWorkspaceOption= ruleWorkspaceOption EOF ;
    public final EObject entryRuleWorkspaceOption() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWorkspaceOption = null;


        try {
            // InternalModel2Blockly.g:2319:56: (iv_ruleWorkspaceOption= ruleWorkspaceOption EOF )
            // InternalModel2Blockly.g:2320:2: iv_ruleWorkspaceOption= ruleWorkspaceOption EOF
            {
             newCompositeNode(grammarAccess.getWorkspaceOptionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleWorkspaceOption=ruleWorkspaceOption();

            state._fsp--;

             current =iv_ruleWorkspaceOption; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleWorkspaceOption"


    // $ANTLR start "ruleWorkspaceOption"
    // InternalModel2Blockly.g:2326:1: ruleWorkspaceOption returns [EObject current=null] : (this_StringOption_0= ruleStringOption | this_IntOption_1= ruleIntOption | this_BoolOption_2= ruleBoolOption | this_ObjectOption_3= ruleObjectOption ) ;
    public final EObject ruleWorkspaceOption() throws RecognitionException {
        EObject current = null;

        EObject this_StringOption_0 = null;

        EObject this_IntOption_1 = null;

        EObject this_BoolOption_2 = null;

        EObject this_ObjectOption_3 = null;



        	enterRule();

        try {
            // InternalModel2Blockly.g:2332:2: ( (this_StringOption_0= ruleStringOption | this_IntOption_1= ruleIntOption | this_BoolOption_2= ruleBoolOption | this_ObjectOption_3= ruleObjectOption ) )
            // InternalModel2Blockly.g:2333:2: (this_StringOption_0= ruleStringOption | this_IntOption_1= ruleIntOption | this_BoolOption_2= ruleBoolOption | this_ObjectOption_3= ruleObjectOption )
            {
            // InternalModel2Blockly.g:2333:2: (this_StringOption_0= ruleStringOption | this_IntOption_1= ruleIntOption | this_BoolOption_2= ruleBoolOption | this_ObjectOption_3= ruleObjectOption )
            int alt61=4;
            int LA61_0 = input.LA(1);

            if ( (LA61_0==RULE_ID) ) {
                int LA61_1 = input.LA(2);

                if ( (LA61_1==32) ) {
                    switch ( input.LA(3) ) {
                    case 18:
                        {
                        alt61=4;
                        }
                        break;
                    case RULE_INT:
                        {
                        alt61=2;
                        }
                        break;
                    case 98:
                    case 99:
                        {
                        alt61=3;
                        }
                        break;
                    case RULE_STRING:
                        {
                        alt61=1;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 61, 2, input);

                        throw nvae;
                    }

                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 61, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 61, 0, input);

                throw nvae;
            }
            switch (alt61) {
                case 1 :
                    // InternalModel2Blockly.g:2334:3: this_StringOption_0= ruleStringOption
                    {

                    			newCompositeNode(grammarAccess.getWorkspaceOptionAccess().getStringOptionParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_StringOption_0=ruleStringOption();

                    state._fsp--;


                    			current = this_StringOption_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalModel2Blockly.g:2343:3: this_IntOption_1= ruleIntOption
                    {

                    			newCompositeNode(grammarAccess.getWorkspaceOptionAccess().getIntOptionParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_IntOption_1=ruleIntOption();

                    state._fsp--;


                    			current = this_IntOption_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 3 :
                    // InternalModel2Blockly.g:2352:3: this_BoolOption_2= ruleBoolOption
                    {

                    			newCompositeNode(grammarAccess.getWorkspaceOptionAccess().getBoolOptionParserRuleCall_2());
                    		
                    pushFollow(FOLLOW_2);
                    this_BoolOption_2=ruleBoolOption();

                    state._fsp--;


                    			current = this_BoolOption_2;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 4 :
                    // InternalModel2Blockly.g:2361:3: this_ObjectOption_3= ruleObjectOption
                    {

                    			newCompositeNode(grammarAccess.getWorkspaceOptionAccess().getObjectOptionParserRuleCall_3());
                    		
                    pushFollow(FOLLOW_2);
                    this_ObjectOption_3=ruleObjectOption();

                    state._fsp--;


                    			current = this_ObjectOption_3;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleWorkspaceOption"


    // $ANTLR start "entryRuleStringOption"
    // InternalModel2Blockly.g:2373:1: entryRuleStringOption returns [EObject current=null] : iv_ruleStringOption= ruleStringOption EOF ;
    public final EObject entryRuleStringOption() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStringOption = null;


        try {
            // InternalModel2Blockly.g:2373:53: (iv_ruleStringOption= ruleStringOption EOF )
            // InternalModel2Blockly.g:2374:2: iv_ruleStringOption= ruleStringOption EOF
            {
             newCompositeNode(grammarAccess.getStringOptionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleStringOption=ruleStringOption();

            state._fsp--;

             current =iv_ruleStringOption; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleStringOption"


    // $ANTLR start "ruleStringOption"
    // InternalModel2Blockly.g:2380:1: ruleStringOption returns [EObject current=null] : ( ( (lv_key_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_value_2_0= RULE_STRING ) ) ) ;
    public final EObject ruleStringOption() throws RecognitionException {
        EObject current = null;

        Token lv_key_0_0=null;
        Token otherlv_1=null;
        Token lv_value_2_0=null;


        	enterRule();

        try {
            // InternalModel2Blockly.g:2386:2: ( ( ( (lv_key_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_value_2_0= RULE_STRING ) ) ) )
            // InternalModel2Blockly.g:2387:2: ( ( (lv_key_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_value_2_0= RULE_STRING ) ) )
            {
            // InternalModel2Blockly.g:2387:2: ( ( (lv_key_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_value_2_0= RULE_STRING ) ) )
            // InternalModel2Blockly.g:2388:3: ( (lv_key_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_value_2_0= RULE_STRING ) )
            {
            // InternalModel2Blockly.g:2388:3: ( (lv_key_0_0= RULE_ID ) )
            // InternalModel2Blockly.g:2389:4: (lv_key_0_0= RULE_ID )
            {
            // InternalModel2Blockly.g:2389:4: (lv_key_0_0= RULE_ID )
            // InternalModel2Blockly.g:2390:5: lv_key_0_0= RULE_ID
            {
            lv_key_0_0=(Token)match(input,RULE_ID,FOLLOW_34); 

            					newLeafNode(lv_key_0_0, grammarAccess.getStringOptionAccess().getKeyIDTerminalRuleCall_0_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getStringOptionRule());
            					}
            					setWithLastConsumed(
            						current,
            						"key",
            						lv_key_0_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_1=(Token)match(input,32,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getStringOptionAccess().getColonKeyword_1());
            		
            // InternalModel2Blockly.g:2410:3: ( (lv_value_2_0= RULE_STRING ) )
            // InternalModel2Blockly.g:2411:4: (lv_value_2_0= RULE_STRING )
            {
            // InternalModel2Blockly.g:2411:4: (lv_value_2_0= RULE_STRING )
            // InternalModel2Blockly.g:2412:5: lv_value_2_0= RULE_STRING
            {
            lv_value_2_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

            					newLeafNode(lv_value_2_0, grammarAccess.getStringOptionAccess().getValueSTRINGTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getStringOptionRule());
            					}
            					setWithLastConsumed(
            						current,
            						"value",
            						lv_value_2_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleStringOption"


    // $ANTLR start "entryRuleIntOption"
    // InternalModel2Blockly.g:2432:1: entryRuleIntOption returns [EObject current=null] : iv_ruleIntOption= ruleIntOption EOF ;
    public final EObject entryRuleIntOption() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIntOption = null;


        try {
            // InternalModel2Blockly.g:2432:50: (iv_ruleIntOption= ruleIntOption EOF )
            // InternalModel2Blockly.g:2433:2: iv_ruleIntOption= ruleIntOption EOF
            {
             newCompositeNode(grammarAccess.getIntOptionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleIntOption=ruleIntOption();

            state._fsp--;

             current =iv_ruleIntOption; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleIntOption"


    // $ANTLR start "ruleIntOption"
    // InternalModel2Blockly.g:2439:1: ruleIntOption returns [EObject current=null] : ( ( (lv_key_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_value_2_0= RULE_INT ) ) ) ;
    public final EObject ruleIntOption() throws RecognitionException {
        EObject current = null;

        Token lv_key_0_0=null;
        Token otherlv_1=null;
        Token lv_value_2_0=null;


        	enterRule();

        try {
            // InternalModel2Blockly.g:2445:2: ( ( ( (lv_key_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_value_2_0= RULE_INT ) ) ) )
            // InternalModel2Blockly.g:2446:2: ( ( (lv_key_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_value_2_0= RULE_INT ) ) )
            {
            // InternalModel2Blockly.g:2446:2: ( ( (lv_key_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_value_2_0= RULE_INT ) ) )
            // InternalModel2Blockly.g:2447:3: ( (lv_key_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_value_2_0= RULE_INT ) )
            {
            // InternalModel2Blockly.g:2447:3: ( (lv_key_0_0= RULE_ID ) )
            // InternalModel2Blockly.g:2448:4: (lv_key_0_0= RULE_ID )
            {
            // InternalModel2Blockly.g:2448:4: (lv_key_0_0= RULE_ID )
            // InternalModel2Blockly.g:2449:5: lv_key_0_0= RULE_ID
            {
            lv_key_0_0=(Token)match(input,RULE_ID,FOLLOW_34); 

            					newLeafNode(lv_key_0_0, grammarAccess.getIntOptionAccess().getKeyIDTerminalRuleCall_0_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getIntOptionRule());
            					}
            					setWithLastConsumed(
            						current,
            						"key",
            						lv_key_0_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_1=(Token)match(input,32,FOLLOW_15); 

            			newLeafNode(otherlv_1, grammarAccess.getIntOptionAccess().getColonKeyword_1());
            		
            // InternalModel2Blockly.g:2469:3: ( (lv_value_2_0= RULE_INT ) )
            // InternalModel2Blockly.g:2470:4: (lv_value_2_0= RULE_INT )
            {
            // InternalModel2Blockly.g:2470:4: (lv_value_2_0= RULE_INT )
            // InternalModel2Blockly.g:2471:5: lv_value_2_0= RULE_INT
            {
            lv_value_2_0=(Token)match(input,RULE_INT,FOLLOW_2); 

            					newLeafNode(lv_value_2_0, grammarAccess.getIntOptionAccess().getValueINTTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getIntOptionRule());
            					}
            					setWithLastConsumed(
            						current,
            						"value",
            						lv_value_2_0,
            						"org.eclipse.xtext.common.Terminals.INT");
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleIntOption"


    // $ANTLR start "entryRuleBoolOption"
    // InternalModel2Blockly.g:2491:1: entryRuleBoolOption returns [EObject current=null] : iv_ruleBoolOption= ruleBoolOption EOF ;
    public final EObject entryRuleBoolOption() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBoolOption = null;


        try {
            // InternalModel2Blockly.g:2491:51: (iv_ruleBoolOption= ruleBoolOption EOF )
            // InternalModel2Blockly.g:2492:2: iv_ruleBoolOption= ruleBoolOption EOF
            {
             newCompositeNode(grammarAccess.getBoolOptionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleBoolOption=ruleBoolOption();

            state._fsp--;

             current =iv_ruleBoolOption; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleBoolOption"


    // $ANTLR start "ruleBoolOption"
    // InternalModel2Blockly.g:2498:1: ruleBoolOption returns [EObject current=null] : ( ( (lv_key_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_value_2_0= ruleBoolVal ) ) ) ;
    public final EObject ruleBoolOption() throws RecognitionException {
        EObject current = null;

        Token lv_key_0_0=null;
        Token otherlv_1=null;
        Enumerator lv_value_2_0 = null;



        	enterRule();

        try {
            // InternalModel2Blockly.g:2504:2: ( ( ( (lv_key_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_value_2_0= ruleBoolVal ) ) ) )
            // InternalModel2Blockly.g:2505:2: ( ( (lv_key_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_value_2_0= ruleBoolVal ) ) )
            {
            // InternalModel2Blockly.g:2505:2: ( ( (lv_key_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_value_2_0= ruleBoolVal ) ) )
            // InternalModel2Blockly.g:2506:3: ( (lv_key_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_value_2_0= ruleBoolVal ) )
            {
            // InternalModel2Blockly.g:2506:3: ( (lv_key_0_0= RULE_ID ) )
            // InternalModel2Blockly.g:2507:4: (lv_key_0_0= RULE_ID )
            {
            // InternalModel2Blockly.g:2507:4: (lv_key_0_0= RULE_ID )
            // InternalModel2Blockly.g:2508:5: lv_key_0_0= RULE_ID
            {
            lv_key_0_0=(Token)match(input,RULE_ID,FOLLOW_34); 

            					newLeafNode(lv_key_0_0, grammarAccess.getBoolOptionAccess().getKeyIDTerminalRuleCall_0_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getBoolOptionRule());
            					}
            					setWithLastConsumed(
            						current,
            						"key",
            						lv_key_0_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_1=(Token)match(input,32,FOLLOW_29); 

            			newLeafNode(otherlv_1, grammarAccess.getBoolOptionAccess().getColonKeyword_1());
            		
            // InternalModel2Blockly.g:2528:3: ( (lv_value_2_0= ruleBoolVal ) )
            // InternalModel2Blockly.g:2529:4: (lv_value_2_0= ruleBoolVal )
            {
            // InternalModel2Blockly.g:2529:4: (lv_value_2_0= ruleBoolVal )
            // InternalModel2Blockly.g:2530:5: lv_value_2_0= ruleBoolVal
            {

            					newCompositeNode(grammarAccess.getBoolOptionAccess().getValueBoolValEnumRuleCall_2_0());
            				
            pushFollow(FOLLOW_2);
            lv_value_2_0=ruleBoolVal();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getBoolOptionRule());
            					}
            					set(
            						current,
            						"value",
            						lv_value_2_0,
            						"io.github.plortinus.model2blockly.Model2Blockly.BoolVal");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleBoolOption"


    // $ANTLR start "entryRuleObjectOption"
    // InternalModel2Blockly.g:2551:1: entryRuleObjectOption returns [EObject current=null] : iv_ruleObjectOption= ruleObjectOption EOF ;
    public final EObject entryRuleObjectOption() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleObjectOption = null;


        try {
            // InternalModel2Blockly.g:2551:53: (iv_ruleObjectOption= ruleObjectOption EOF )
            // InternalModel2Blockly.g:2552:2: iv_ruleObjectOption= ruleObjectOption EOF
            {
             newCompositeNode(grammarAccess.getObjectOptionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleObjectOption=ruleObjectOption();

            state._fsp--;

             current =iv_ruleObjectOption; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleObjectOption"


    // $ANTLR start "ruleObjectOption"
    // InternalModel2Blockly.g:2558:1: ruleObjectOption returns [EObject current=null] : ( ( (lv_key_0_0= RULE_ID ) ) otherlv_1= ':' otherlv_2= '{' ( (lv_entries_3_0= ruleWorkspaceOption ) )* otherlv_4= '}' ) ;
    public final EObject ruleObjectOption() throws RecognitionException {
        EObject current = null;

        Token lv_key_0_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_entries_3_0 = null;



        	enterRule();

        try {
            // InternalModel2Blockly.g:2564:2: ( ( ( (lv_key_0_0= RULE_ID ) ) otherlv_1= ':' otherlv_2= '{' ( (lv_entries_3_0= ruleWorkspaceOption ) )* otherlv_4= '}' ) )
            // InternalModel2Blockly.g:2565:2: ( ( (lv_key_0_0= RULE_ID ) ) otherlv_1= ':' otherlv_2= '{' ( (lv_entries_3_0= ruleWorkspaceOption ) )* otherlv_4= '}' )
            {
            // InternalModel2Blockly.g:2565:2: ( ( (lv_key_0_0= RULE_ID ) ) otherlv_1= ':' otherlv_2= '{' ( (lv_entries_3_0= ruleWorkspaceOption ) )* otherlv_4= '}' )
            // InternalModel2Blockly.g:2566:3: ( (lv_key_0_0= RULE_ID ) ) otherlv_1= ':' otherlv_2= '{' ( (lv_entries_3_0= ruleWorkspaceOption ) )* otherlv_4= '}'
            {
            // InternalModel2Blockly.g:2566:3: ( (lv_key_0_0= RULE_ID ) )
            // InternalModel2Blockly.g:2567:4: (lv_key_0_0= RULE_ID )
            {
            // InternalModel2Blockly.g:2567:4: (lv_key_0_0= RULE_ID )
            // InternalModel2Blockly.g:2568:5: lv_key_0_0= RULE_ID
            {
            lv_key_0_0=(Token)match(input,RULE_ID,FOLLOW_34); 

            					newLeafNode(lv_key_0_0, grammarAccess.getObjectOptionAccess().getKeyIDTerminalRuleCall_0_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getObjectOptionRule());
            					}
            					setWithLastConsumed(
            						current,
            						"key",
            						lv_key_0_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_1=(Token)match(input,32,FOLLOW_32); 

            			newLeafNode(otherlv_1, grammarAccess.getObjectOptionAccess().getColonKeyword_1());
            		
            otherlv_2=(Token)match(input,18,FOLLOW_67); 

            			newLeafNode(otherlv_2, grammarAccess.getObjectOptionAccess().getLeftCurlyBracketKeyword_2());
            		
            // InternalModel2Blockly.g:2592:3: ( (lv_entries_3_0= ruleWorkspaceOption ) )*
            loop62:
            do {
                int alt62=2;
                int LA62_0 = input.LA(1);

                if ( (LA62_0==RULE_ID) ) {
                    alt62=1;
                }


                switch (alt62) {
            	case 1 :
            	    // InternalModel2Blockly.g:2593:4: (lv_entries_3_0= ruleWorkspaceOption )
            	    {
            	    // InternalModel2Blockly.g:2593:4: (lv_entries_3_0= ruleWorkspaceOption )
            	    // InternalModel2Blockly.g:2594:5: lv_entries_3_0= ruleWorkspaceOption
            	    {

            	    					newCompositeNode(grammarAccess.getObjectOptionAccess().getEntriesWorkspaceOptionParserRuleCall_3_0());
            	    				
            	    pushFollow(FOLLOW_67);
            	    lv_entries_3_0=ruleWorkspaceOption();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getObjectOptionRule());
            	    					}
            	    					add(
            	    						current,
            	    						"entries",
            	    						lv_entries_3_0,
            	    						"io.github.plortinus.model2blockly.Model2Blockly.WorkspaceOption");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop62;
                }
            } while (true);

            otherlv_4=(Token)match(input,19,FOLLOW_2); 

            			newLeafNode(otherlv_4, grammarAccess.getObjectOptionAccess().getRightCurlyBracketKeyword_4());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleObjectOption"


    // $ANTLR start "ruleUiWidget"
    // InternalModel2Blockly.g:2619:1: ruleUiWidget returns [Enumerator current=null] : ( (enumLiteral_0= 'text' ) | (enumLiteral_1= 'textarea' ) | (enumLiteral_2= 'number' ) | (enumLiteral_3= 'slider' ) | (enumLiteral_4= 'switch' ) | (enumLiteral_5= 'checkbox' ) | (enumLiteral_6= 'select' ) | (enumLiteral_7= 'radio' ) | (enumLiteral_8= 'color' ) | (enumLiteral_9= 'angle' ) | (enumLiteral_10= 'image' ) | (enumLiteral_11= 'reference-select' ) | (enumLiteral_12= 'slot' ) | (enumLiteral_13= 'expression-slot' ) ) ;
    public final Enumerator ruleUiWidget() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;
        Token enumLiteral_3=null;
        Token enumLiteral_4=null;
        Token enumLiteral_5=null;
        Token enumLiteral_6=null;
        Token enumLiteral_7=null;
        Token enumLiteral_8=null;
        Token enumLiteral_9=null;
        Token enumLiteral_10=null;
        Token enumLiteral_11=null;
        Token enumLiteral_12=null;
        Token enumLiteral_13=null;


        	enterRule();

        try {
            // InternalModel2Blockly.g:2625:2: ( ( (enumLiteral_0= 'text' ) | (enumLiteral_1= 'textarea' ) | (enumLiteral_2= 'number' ) | (enumLiteral_3= 'slider' ) | (enumLiteral_4= 'switch' ) | (enumLiteral_5= 'checkbox' ) | (enumLiteral_6= 'select' ) | (enumLiteral_7= 'radio' ) | (enumLiteral_8= 'color' ) | (enumLiteral_9= 'angle' ) | (enumLiteral_10= 'image' ) | (enumLiteral_11= 'reference-select' ) | (enumLiteral_12= 'slot' ) | (enumLiteral_13= 'expression-slot' ) ) )
            // InternalModel2Blockly.g:2626:2: ( (enumLiteral_0= 'text' ) | (enumLiteral_1= 'textarea' ) | (enumLiteral_2= 'number' ) | (enumLiteral_3= 'slider' ) | (enumLiteral_4= 'switch' ) | (enumLiteral_5= 'checkbox' ) | (enumLiteral_6= 'select' ) | (enumLiteral_7= 'radio' ) | (enumLiteral_8= 'color' ) | (enumLiteral_9= 'angle' ) | (enumLiteral_10= 'image' ) | (enumLiteral_11= 'reference-select' ) | (enumLiteral_12= 'slot' ) | (enumLiteral_13= 'expression-slot' ) )
            {
            // InternalModel2Blockly.g:2626:2: ( (enumLiteral_0= 'text' ) | (enumLiteral_1= 'textarea' ) | (enumLiteral_2= 'number' ) | (enumLiteral_3= 'slider' ) | (enumLiteral_4= 'switch' ) | (enumLiteral_5= 'checkbox' ) | (enumLiteral_6= 'select' ) | (enumLiteral_7= 'radio' ) | (enumLiteral_8= 'color' ) | (enumLiteral_9= 'angle' ) | (enumLiteral_10= 'image' ) | (enumLiteral_11= 'reference-select' ) | (enumLiteral_12= 'slot' ) | (enumLiteral_13= 'expression-slot' ) )
            int alt63=14;
            switch ( input.LA(1) ) {
            case 74:
                {
                alt63=1;
                }
                break;
            case 75:
                {
                alt63=2;
                }
                break;
            case 76:
                {
                alt63=3;
                }
                break;
            case 77:
                {
                alt63=4;
                }
                break;
            case 78:
                {
                alt63=5;
                }
                break;
            case 79:
                {
                alt63=6;
                }
                break;
            case 80:
                {
                alt63=7;
                }
                break;
            case 81:
                {
                alt63=8;
                }
                break;
            case 82:
                {
                alt63=9;
                }
                break;
            case 83:
                {
                alt63=10;
                }
                break;
            case 84:
                {
                alt63=11;
                }
                break;
            case 85:
                {
                alt63=12;
                }
                break;
            case 86:
                {
                alt63=13;
                }
                break;
            case 87:
                {
                alt63=14;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 63, 0, input);

                throw nvae;
            }

            switch (alt63) {
                case 1 :
                    // InternalModel2Blockly.g:2627:3: (enumLiteral_0= 'text' )
                    {
                    // InternalModel2Blockly.g:2627:3: (enumLiteral_0= 'text' )
                    // InternalModel2Blockly.g:2628:4: enumLiteral_0= 'text'
                    {
                    enumLiteral_0=(Token)match(input,74,FOLLOW_2); 

                    				current = grammarAccess.getUiWidgetAccess().getTEXTEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getUiWidgetAccess().getTEXTEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalModel2Blockly.g:2635:3: (enumLiteral_1= 'textarea' )
                    {
                    // InternalModel2Blockly.g:2635:3: (enumLiteral_1= 'textarea' )
                    // InternalModel2Blockly.g:2636:4: enumLiteral_1= 'textarea'
                    {
                    enumLiteral_1=(Token)match(input,75,FOLLOW_2); 

                    				current = grammarAccess.getUiWidgetAccess().getTEXTAREAEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getUiWidgetAccess().getTEXTAREAEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalModel2Blockly.g:2643:3: (enumLiteral_2= 'number' )
                    {
                    // InternalModel2Blockly.g:2643:3: (enumLiteral_2= 'number' )
                    // InternalModel2Blockly.g:2644:4: enumLiteral_2= 'number'
                    {
                    enumLiteral_2=(Token)match(input,76,FOLLOW_2); 

                    				current = grammarAccess.getUiWidgetAccess().getNUMBEREnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_2, grammarAccess.getUiWidgetAccess().getNUMBEREnumLiteralDeclaration_2());
                    			

                    }


                    }
                    break;
                case 4 :
                    // InternalModel2Blockly.g:2651:3: (enumLiteral_3= 'slider' )
                    {
                    // InternalModel2Blockly.g:2651:3: (enumLiteral_3= 'slider' )
                    // InternalModel2Blockly.g:2652:4: enumLiteral_3= 'slider'
                    {
                    enumLiteral_3=(Token)match(input,77,FOLLOW_2); 

                    				current = grammarAccess.getUiWidgetAccess().getSLIDEREnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_3, grammarAccess.getUiWidgetAccess().getSLIDEREnumLiteralDeclaration_3());
                    			

                    }


                    }
                    break;
                case 5 :
                    // InternalModel2Blockly.g:2659:3: (enumLiteral_4= 'switch' )
                    {
                    // InternalModel2Blockly.g:2659:3: (enumLiteral_4= 'switch' )
                    // InternalModel2Blockly.g:2660:4: enumLiteral_4= 'switch'
                    {
                    enumLiteral_4=(Token)match(input,78,FOLLOW_2); 

                    				current = grammarAccess.getUiWidgetAccess().getSWITCHEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_4, grammarAccess.getUiWidgetAccess().getSWITCHEnumLiteralDeclaration_4());
                    			

                    }


                    }
                    break;
                case 6 :
                    // InternalModel2Blockly.g:2667:3: (enumLiteral_5= 'checkbox' )
                    {
                    // InternalModel2Blockly.g:2667:3: (enumLiteral_5= 'checkbox' )
                    // InternalModel2Blockly.g:2668:4: enumLiteral_5= 'checkbox'
                    {
                    enumLiteral_5=(Token)match(input,79,FOLLOW_2); 

                    				current = grammarAccess.getUiWidgetAccess().getCHECKBOXEnumLiteralDeclaration_5().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_5, grammarAccess.getUiWidgetAccess().getCHECKBOXEnumLiteralDeclaration_5());
                    			

                    }


                    }
                    break;
                case 7 :
                    // InternalModel2Blockly.g:2675:3: (enumLiteral_6= 'select' )
                    {
                    // InternalModel2Blockly.g:2675:3: (enumLiteral_6= 'select' )
                    // InternalModel2Blockly.g:2676:4: enumLiteral_6= 'select'
                    {
                    enumLiteral_6=(Token)match(input,80,FOLLOW_2); 

                    				current = grammarAccess.getUiWidgetAccess().getSELECTEnumLiteralDeclaration_6().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_6, grammarAccess.getUiWidgetAccess().getSELECTEnumLiteralDeclaration_6());
                    			

                    }


                    }
                    break;
                case 8 :
                    // InternalModel2Blockly.g:2683:3: (enumLiteral_7= 'radio' )
                    {
                    // InternalModel2Blockly.g:2683:3: (enumLiteral_7= 'radio' )
                    // InternalModel2Blockly.g:2684:4: enumLiteral_7= 'radio'
                    {
                    enumLiteral_7=(Token)match(input,81,FOLLOW_2); 

                    				current = grammarAccess.getUiWidgetAccess().getRADIOEnumLiteralDeclaration_7().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_7, grammarAccess.getUiWidgetAccess().getRADIOEnumLiteralDeclaration_7());
                    			

                    }


                    }
                    break;
                case 9 :
                    // InternalModel2Blockly.g:2691:3: (enumLiteral_8= 'color' )
                    {
                    // InternalModel2Blockly.g:2691:3: (enumLiteral_8= 'color' )
                    // InternalModel2Blockly.g:2692:4: enumLiteral_8= 'color'
                    {
                    enumLiteral_8=(Token)match(input,82,FOLLOW_2); 

                    				current = grammarAccess.getUiWidgetAccess().getCOLOREnumLiteralDeclaration_8().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_8, grammarAccess.getUiWidgetAccess().getCOLOREnumLiteralDeclaration_8());
                    			

                    }


                    }
                    break;
                case 10 :
                    // InternalModel2Blockly.g:2699:3: (enumLiteral_9= 'angle' )
                    {
                    // InternalModel2Blockly.g:2699:3: (enumLiteral_9= 'angle' )
                    // InternalModel2Blockly.g:2700:4: enumLiteral_9= 'angle'
                    {
                    enumLiteral_9=(Token)match(input,83,FOLLOW_2); 

                    				current = grammarAccess.getUiWidgetAccess().getANGLEEnumLiteralDeclaration_9().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_9, grammarAccess.getUiWidgetAccess().getANGLEEnumLiteralDeclaration_9());
                    			

                    }


                    }
                    break;
                case 11 :
                    // InternalModel2Blockly.g:2707:3: (enumLiteral_10= 'image' )
                    {
                    // InternalModel2Blockly.g:2707:3: (enumLiteral_10= 'image' )
                    // InternalModel2Blockly.g:2708:4: enumLiteral_10= 'image'
                    {
                    enumLiteral_10=(Token)match(input,84,FOLLOW_2); 

                    				current = grammarAccess.getUiWidgetAccess().getIMAGEEnumLiteralDeclaration_10().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_10, grammarAccess.getUiWidgetAccess().getIMAGEEnumLiteralDeclaration_10());
                    			

                    }


                    }
                    break;
                case 12 :
                    // InternalModel2Blockly.g:2715:3: (enumLiteral_11= 'reference-select' )
                    {
                    // InternalModel2Blockly.g:2715:3: (enumLiteral_11= 'reference-select' )
                    // InternalModel2Blockly.g:2716:4: enumLiteral_11= 'reference-select'
                    {
                    enumLiteral_11=(Token)match(input,85,FOLLOW_2); 

                    				current = grammarAccess.getUiWidgetAccess().getREFERENCE_SELECTEnumLiteralDeclaration_11().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_11, grammarAccess.getUiWidgetAccess().getREFERENCE_SELECTEnumLiteralDeclaration_11());
                    			

                    }


                    }
                    break;
                case 13 :
                    // InternalModel2Blockly.g:2723:3: (enumLiteral_12= 'slot' )
                    {
                    // InternalModel2Blockly.g:2723:3: (enumLiteral_12= 'slot' )
                    // InternalModel2Blockly.g:2724:4: enumLiteral_12= 'slot'
                    {
                    enumLiteral_12=(Token)match(input,86,FOLLOW_2); 

                    				current = grammarAccess.getUiWidgetAccess().getSLOTEnumLiteralDeclaration_12().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_12, grammarAccess.getUiWidgetAccess().getSLOTEnumLiteralDeclaration_12());
                    			

                    }


                    }
                    break;
                case 14 :
                    // InternalModel2Blockly.g:2731:3: (enumLiteral_13= 'expression-slot' )
                    {
                    // InternalModel2Blockly.g:2731:3: (enumLiteral_13= 'expression-slot' )
                    // InternalModel2Blockly.g:2732:4: enumLiteral_13= 'expression-slot'
                    {
                    enumLiteral_13=(Token)match(input,87,FOLLOW_2); 

                    				current = grammarAccess.getUiWidgetAccess().getEXPRESSION_SLOTEnumLiteralDeclaration_13().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_13, grammarAccess.getUiWidgetAccess().getEXPRESSION_SLOTEnumLiteralDeclaration_13());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleUiWidget"


    // $ANTLR start "ruleUiVariant"
    // InternalModel2Blockly.g:2742:1: ruleUiVariant returns [Enumerator current=null] : ( (enumLiteral_0= 'default' ) | (enumLiteral_1= 'compact' ) | (enumLiteral_2= 'prominent' ) ) ;
    public final Enumerator ruleUiVariant() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;


        	enterRule();

        try {
            // InternalModel2Blockly.g:2748:2: ( ( (enumLiteral_0= 'default' ) | (enumLiteral_1= 'compact' ) | (enumLiteral_2= 'prominent' ) ) )
            // InternalModel2Blockly.g:2749:2: ( (enumLiteral_0= 'default' ) | (enumLiteral_1= 'compact' ) | (enumLiteral_2= 'prominent' ) )
            {
            // InternalModel2Blockly.g:2749:2: ( (enumLiteral_0= 'default' ) | (enumLiteral_1= 'compact' ) | (enumLiteral_2= 'prominent' ) )
            int alt64=3;
            switch ( input.LA(1) ) {
            case 33:
                {
                alt64=1;
                }
                break;
            case 88:
                {
                alt64=2;
                }
                break;
            case 89:
                {
                alt64=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 64, 0, input);

                throw nvae;
            }

            switch (alt64) {
                case 1 :
                    // InternalModel2Blockly.g:2750:3: (enumLiteral_0= 'default' )
                    {
                    // InternalModel2Blockly.g:2750:3: (enumLiteral_0= 'default' )
                    // InternalModel2Blockly.g:2751:4: enumLiteral_0= 'default'
                    {
                    enumLiteral_0=(Token)match(input,33,FOLLOW_2); 

                    				current = grammarAccess.getUiVariantAccess().getDEFAULTEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getUiVariantAccess().getDEFAULTEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalModel2Blockly.g:2758:3: (enumLiteral_1= 'compact' )
                    {
                    // InternalModel2Blockly.g:2758:3: (enumLiteral_1= 'compact' )
                    // InternalModel2Blockly.g:2759:4: enumLiteral_1= 'compact'
                    {
                    enumLiteral_1=(Token)match(input,88,FOLLOW_2); 

                    				current = grammarAccess.getUiVariantAccess().getCOMPACTEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getUiVariantAccess().getCOMPACTEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalModel2Blockly.g:2766:3: (enumLiteral_2= 'prominent' )
                    {
                    // InternalModel2Blockly.g:2766:3: (enumLiteral_2= 'prominent' )
                    // InternalModel2Blockly.g:2767:4: enumLiteral_2= 'prominent'
                    {
                    enumLiteral_2=(Token)match(input,89,FOLLOW_2); 

                    				current = grammarAccess.getUiVariantAccess().getPROMINENTEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_2, grammarAccess.getUiVariantAccess().getPROMINENTEnumLiteralDeclaration_2());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleUiVariant"


    // $ANTLR start "ruleSimpleTypeName"
    // InternalModel2Blockly.g:2777:1: ruleSimpleTypeName returns [Enumerator current=null] : ( (enumLiteral_0= 'string' ) | (enumLiteral_1= 'int' ) | (enumLiteral_2= 'boolean' ) | (enumLiteral_3= 'float' ) | (enumLiteral_4= 'colour' ) | (enumLiteral_5= 'angle' ) | (enumLiteral_6= 'image' ) | (enumLiteral_7= 'label' ) ) ;
    public final Enumerator ruleSimpleTypeName() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;
        Token enumLiteral_3=null;
        Token enumLiteral_4=null;
        Token enumLiteral_5=null;
        Token enumLiteral_6=null;
        Token enumLiteral_7=null;


        	enterRule();

        try {
            // InternalModel2Blockly.g:2783:2: ( ( (enumLiteral_0= 'string' ) | (enumLiteral_1= 'int' ) | (enumLiteral_2= 'boolean' ) | (enumLiteral_3= 'float' ) | (enumLiteral_4= 'colour' ) | (enumLiteral_5= 'angle' ) | (enumLiteral_6= 'image' ) | (enumLiteral_7= 'label' ) ) )
            // InternalModel2Blockly.g:2784:2: ( (enumLiteral_0= 'string' ) | (enumLiteral_1= 'int' ) | (enumLiteral_2= 'boolean' ) | (enumLiteral_3= 'float' ) | (enumLiteral_4= 'colour' ) | (enumLiteral_5= 'angle' ) | (enumLiteral_6= 'image' ) | (enumLiteral_7= 'label' ) )
            {
            // InternalModel2Blockly.g:2784:2: ( (enumLiteral_0= 'string' ) | (enumLiteral_1= 'int' ) | (enumLiteral_2= 'boolean' ) | (enumLiteral_3= 'float' ) | (enumLiteral_4= 'colour' ) | (enumLiteral_5= 'angle' ) | (enumLiteral_6= 'image' ) | (enumLiteral_7= 'label' ) )
            int alt65=8;
            switch ( input.LA(1) ) {
            case 90:
                {
                alt65=1;
                }
                break;
            case 91:
                {
                alt65=2;
                }
                break;
            case 92:
                {
                alt65=3;
                }
                break;
            case 93:
                {
                alt65=4;
                }
                break;
            case 17:
                {
                alt65=5;
                }
                break;
            case 83:
                {
                alt65=6;
                }
                break;
            case 84:
                {
                alt65=7;
                }
                break;
            case 16:
                {
                alt65=8;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 65, 0, input);

                throw nvae;
            }

            switch (alt65) {
                case 1 :
                    // InternalModel2Blockly.g:2785:3: (enumLiteral_0= 'string' )
                    {
                    // InternalModel2Blockly.g:2785:3: (enumLiteral_0= 'string' )
                    // InternalModel2Blockly.g:2786:4: enumLiteral_0= 'string'
                    {
                    enumLiteral_0=(Token)match(input,90,FOLLOW_2); 

                    				current = grammarAccess.getSimpleTypeNameAccess().getStringEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getSimpleTypeNameAccess().getStringEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalModel2Blockly.g:2793:3: (enumLiteral_1= 'int' )
                    {
                    // InternalModel2Blockly.g:2793:3: (enumLiteral_1= 'int' )
                    // InternalModel2Blockly.g:2794:4: enumLiteral_1= 'int'
                    {
                    enumLiteral_1=(Token)match(input,91,FOLLOW_2); 

                    				current = grammarAccess.getSimpleTypeNameAccess().getIntEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getSimpleTypeNameAccess().getIntEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalModel2Blockly.g:2801:3: (enumLiteral_2= 'boolean' )
                    {
                    // InternalModel2Blockly.g:2801:3: (enumLiteral_2= 'boolean' )
                    // InternalModel2Blockly.g:2802:4: enumLiteral_2= 'boolean'
                    {
                    enumLiteral_2=(Token)match(input,92,FOLLOW_2); 

                    				current = grammarAccess.getSimpleTypeNameAccess().getBooleanEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_2, grammarAccess.getSimpleTypeNameAccess().getBooleanEnumLiteralDeclaration_2());
                    			

                    }


                    }
                    break;
                case 4 :
                    // InternalModel2Blockly.g:2809:3: (enumLiteral_3= 'float' )
                    {
                    // InternalModel2Blockly.g:2809:3: (enumLiteral_3= 'float' )
                    // InternalModel2Blockly.g:2810:4: enumLiteral_3= 'float'
                    {
                    enumLiteral_3=(Token)match(input,93,FOLLOW_2); 

                    				current = grammarAccess.getSimpleTypeNameAccess().getFloatEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_3, grammarAccess.getSimpleTypeNameAccess().getFloatEnumLiteralDeclaration_3());
                    			

                    }


                    }
                    break;
                case 5 :
                    // InternalModel2Blockly.g:2817:3: (enumLiteral_4= 'colour' )
                    {
                    // InternalModel2Blockly.g:2817:3: (enumLiteral_4= 'colour' )
                    // InternalModel2Blockly.g:2818:4: enumLiteral_4= 'colour'
                    {
                    enumLiteral_4=(Token)match(input,17,FOLLOW_2); 

                    				current = grammarAccess.getSimpleTypeNameAccess().getColourEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_4, grammarAccess.getSimpleTypeNameAccess().getColourEnumLiteralDeclaration_4());
                    			

                    }


                    }
                    break;
                case 6 :
                    // InternalModel2Blockly.g:2825:3: (enumLiteral_5= 'angle' )
                    {
                    // InternalModel2Blockly.g:2825:3: (enumLiteral_5= 'angle' )
                    // InternalModel2Blockly.g:2826:4: enumLiteral_5= 'angle'
                    {
                    enumLiteral_5=(Token)match(input,83,FOLLOW_2); 

                    				current = grammarAccess.getSimpleTypeNameAccess().getAngleEnumLiteralDeclaration_5().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_5, grammarAccess.getSimpleTypeNameAccess().getAngleEnumLiteralDeclaration_5());
                    			

                    }


                    }
                    break;
                case 7 :
                    // InternalModel2Blockly.g:2833:3: (enumLiteral_6= 'image' )
                    {
                    // InternalModel2Blockly.g:2833:3: (enumLiteral_6= 'image' )
                    // InternalModel2Blockly.g:2834:4: enumLiteral_6= 'image'
                    {
                    enumLiteral_6=(Token)match(input,84,FOLLOW_2); 

                    				current = grammarAccess.getSimpleTypeNameAccess().getImageEnumLiteralDeclaration_6().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_6, grammarAccess.getSimpleTypeNameAccess().getImageEnumLiteralDeclaration_6());
                    			

                    }


                    }
                    break;
                case 8 :
                    // InternalModel2Blockly.g:2841:3: (enumLiteral_7= 'label' )
                    {
                    // InternalModel2Blockly.g:2841:3: (enumLiteral_7= 'label' )
                    // InternalModel2Blockly.g:2842:4: enumLiteral_7= 'label'
                    {
                    enumLiteral_7=(Token)match(input,16,FOLLOW_2); 

                    				current = grammarAccess.getSimpleTypeNameAccess().getLabelEnumLiteralDeclaration_7().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_7, grammarAccess.getSimpleTypeNameAccess().getLabelEnumLiteralDeclaration_7());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSimpleTypeName"


    // $ANTLR start "ruleValidationKind"
    // InternalModel2Blockly.g:2852:1: ruleValidationKind returns [Enumerator current=null] : ( (enumLiteral_0= 'expression' ) | (enumLiteral_1= 'condition' ) | (enumLiteral_2= 'js' ) | (enumLiteral_3= 'ocl' ) ) ;
    public final Enumerator ruleValidationKind() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;
        Token enumLiteral_3=null;


        	enterRule();

        try {
            // InternalModel2Blockly.g:2858:2: ( ( (enumLiteral_0= 'expression' ) | (enumLiteral_1= 'condition' ) | (enumLiteral_2= 'js' ) | (enumLiteral_3= 'ocl' ) ) )
            // InternalModel2Blockly.g:2859:2: ( (enumLiteral_0= 'expression' ) | (enumLiteral_1= 'condition' ) | (enumLiteral_2= 'js' ) | (enumLiteral_3= 'ocl' ) )
            {
            // InternalModel2Blockly.g:2859:2: ( (enumLiteral_0= 'expression' ) | (enumLiteral_1= 'condition' ) | (enumLiteral_2= 'js' ) | (enumLiteral_3= 'ocl' ) )
            int alt66=4;
            switch ( input.LA(1) ) {
            case 94:
                {
                alt66=1;
                }
                break;
            case 95:
                {
                alt66=2;
                }
                break;
            case 96:
                {
                alt66=3;
                }
                break;
            case 97:
                {
                alt66=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 66, 0, input);

                throw nvae;
            }

            switch (alt66) {
                case 1 :
                    // InternalModel2Blockly.g:2860:3: (enumLiteral_0= 'expression' )
                    {
                    // InternalModel2Blockly.g:2860:3: (enumLiteral_0= 'expression' )
                    // InternalModel2Blockly.g:2861:4: enumLiteral_0= 'expression'
                    {
                    enumLiteral_0=(Token)match(input,94,FOLLOW_2); 

                    				current = grammarAccess.getValidationKindAccess().getEXPRESSIONEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getValidationKindAccess().getEXPRESSIONEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalModel2Blockly.g:2868:3: (enumLiteral_1= 'condition' )
                    {
                    // InternalModel2Blockly.g:2868:3: (enumLiteral_1= 'condition' )
                    // InternalModel2Blockly.g:2869:4: enumLiteral_1= 'condition'
                    {
                    enumLiteral_1=(Token)match(input,95,FOLLOW_2); 

                    				current = grammarAccess.getValidationKindAccess().getCONDITIONEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getValidationKindAccess().getCONDITIONEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalModel2Blockly.g:2876:3: (enumLiteral_2= 'js' )
                    {
                    // InternalModel2Blockly.g:2876:3: (enumLiteral_2= 'js' )
                    // InternalModel2Blockly.g:2877:4: enumLiteral_2= 'js'
                    {
                    enumLiteral_2=(Token)match(input,96,FOLLOW_2); 

                    				current = grammarAccess.getValidationKindAccess().getJSEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_2, grammarAccess.getValidationKindAccess().getJSEnumLiteralDeclaration_2());
                    			

                    }


                    }
                    break;
                case 4 :
                    // InternalModel2Blockly.g:2884:3: (enumLiteral_3= 'ocl' )
                    {
                    // InternalModel2Blockly.g:2884:3: (enumLiteral_3= 'ocl' )
                    // InternalModel2Blockly.g:2885:4: enumLiteral_3= 'ocl'
                    {
                    enumLiteral_3=(Token)match(input,97,FOLLOW_2); 

                    				current = grammarAccess.getValidationKindAccess().getOCLEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_3, grammarAccess.getValidationKindAccess().getOCLEnumLiteralDeclaration_3());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleValidationKind"


    // $ANTLR start "ruleBoolVal"
    // InternalModel2Blockly.g:2895:1: ruleBoolVal returns [Enumerator current=null] : ( (enumLiteral_0= 'true' ) | (enumLiteral_1= 'false' ) ) ;
    public final Enumerator ruleBoolVal() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;


        	enterRule();

        try {
            // InternalModel2Blockly.g:2901:2: ( ( (enumLiteral_0= 'true' ) | (enumLiteral_1= 'false' ) ) )
            // InternalModel2Blockly.g:2902:2: ( (enumLiteral_0= 'true' ) | (enumLiteral_1= 'false' ) )
            {
            // InternalModel2Blockly.g:2902:2: ( (enumLiteral_0= 'true' ) | (enumLiteral_1= 'false' ) )
            int alt67=2;
            int LA67_0 = input.LA(1);

            if ( (LA67_0==98) ) {
                alt67=1;
            }
            else if ( (LA67_0==99) ) {
                alt67=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 67, 0, input);

                throw nvae;
            }
            switch (alt67) {
                case 1 :
                    // InternalModel2Blockly.g:2903:3: (enumLiteral_0= 'true' )
                    {
                    // InternalModel2Blockly.g:2903:3: (enumLiteral_0= 'true' )
                    // InternalModel2Blockly.g:2904:4: enumLiteral_0= 'true'
                    {
                    enumLiteral_0=(Token)match(input,98,FOLLOW_2); 

                    				current = grammarAccess.getBoolValAccess().getTRUEEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getBoolValAccess().getTRUEEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalModel2Blockly.g:2911:3: (enumLiteral_1= 'false' )
                    {
                    // InternalModel2Blockly.g:2911:3: (enumLiteral_1= 'false' )
                    // InternalModel2Blockly.g:2912:4: enumLiteral_1= 'false'
                    {
                    enumLiteral_1=(Token)match(input,99,FOLLOW_2); 

                    				current = grammarAccess.getBoolValAccess().getFALSEEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getBoolValAccess().getFALSEEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleBoolVal"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000B0F002L,0x0000000000000288L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000B0E002L,0x0000000000000288L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000B0C002L,0x0000000000000288L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000B08002L,0x0000000000000288L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000B08002L,0x0000000000000088L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000B00002L,0x0000000000000088L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000088L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000080L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000070002L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000060002L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000000088000L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000000A00000L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000000C00000L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x000000007F078000L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x000000007E078000L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x000000007E070000L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x000000007E050000L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x000000007E040000L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x000000007C040000L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x0000000078040000L});
    public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x0000000070040000L});
    public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x0000000000000000L,0x0000000C00000000L});
    public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x0000000060040000L});
    public static final BitSet FOLLOW_31 = new BitSet(new long[]{0x0000000040040000L});
    public static final BitSet FOLLOW_32 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_33 = new BitSet(new long[]{0x0014400080080000L});
    public static final BitSet FOLLOW_34 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_35 = new BitSet(new long[]{0x0000000000030000L,0x000000003C180001L});
    public static final BitSet FOLLOW_36 = new BitSet(new long[]{0xFFC0BFFE00000002L});
    public static final BitSet FOLLOW_37 = new BitSet(new long[]{0xFFC03FFE00000002L});
    public static final BitSet FOLLOW_38 = new BitSet(new long[]{0xFFC03FFC00000002L});
    public static final BitSet FOLLOW_39 = new BitSet(new long[]{0xFFC03FF800000002L});
    public static final BitSet FOLLOW_40 = new BitSet(new long[]{0xFFC03FF000000002L});
    public static final BitSet FOLLOW_41 = new BitSet(new long[]{0xFFC03FE000000002L});
    public static final BitSet FOLLOW_42 = new BitSet(new long[]{0xFFC03FC000000002L});
    public static final BitSet FOLLOW_43 = new BitSet(new long[]{0xFFC03F8000000002L});
    public static final BitSet FOLLOW_44 = new BitSet(new long[]{0xFFC03F0000000002L});
    public static final BitSet FOLLOW_45 = new BitSet(new long[]{0xFFC03E0000000002L});
    public static final BitSet FOLLOW_46 = new BitSet(new long[]{0xFFC03C0000000002L});
    public static final BitSet FOLLOW_47 = new BitSet(new long[]{0xFFC0380000000002L});
    public static final BitSet FOLLOW_48 = new BitSet(new long[]{0xFFC0300000000002L});
    public static final BitSet FOLLOW_49 = new BitSet(new long[]{0xFFC0200000000002L});
    public static final BitSet FOLLOW_50 = new BitSet(new long[]{0xFFC0000000000002L});
    public static final BitSet FOLLOW_51 = new BitSet(new long[]{0xFFC0800000000002L});
    public static final BitSet FOLLOW_52 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_53 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_54 = new BitSet(new long[]{0xFFC8BD0000000002L});
    public static final BitSet FOLLOW_55 = new BitSet(new long[]{0xFFC83D0000000002L});
    public static final BitSet FOLLOW_56 = new BitSet(new long[]{0xFFC03D0000000002L});
    public static final BitSet FOLLOW_57 = new BitSet(new long[]{0xFFE0000000000002L});
    public static final BitSet FOLLOW_58 = new BitSet(new long[]{0x0000000000000000L,0x0000000000FFFC00L});
    public static final BitSet FOLLOW_59 = new BitSet(new long[]{0x0000000200000000L,0x0000000003000000L});
    public static final BitSet FOLLOW_60 = new BitSet(new long[]{0x0000000000080000L,0x0000000000000002L});
    public static final BitSet FOLLOW_61 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000004L});
    public static final BitSet FOLLOW_62 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_63 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_64 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_65 = new BitSet(new long[]{0x0000000000000000L,0x00000003C0000000L});
    public static final BitSet FOLLOW_66 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000100L});
    public static final BitSet FOLLOW_67 = new BitSet(new long[]{0x0000000000080010L});

}