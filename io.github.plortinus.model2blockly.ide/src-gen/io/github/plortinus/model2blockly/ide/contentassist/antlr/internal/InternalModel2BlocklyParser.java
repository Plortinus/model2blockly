package io.github.plortinus.model2blockly.ide.contentassist.antlr.internal;

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.DFA;
import io.github.plortinus.model2blockly.services.Model2BlocklyGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalModel2BlocklyParser extends AbstractInternalContentAssistParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_STRING", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'text'", "'textarea'", "'number'", "'slider'", "'switch'", "'checkbox'", "'select'", "'radio'", "'color'", "'angle'", "'image'", "'reference-select'", "'slot'", "'expression-slot'", "'default'", "'compact'", "'prominent'", "'string'", "'int'", "'boolean'", "'float'", "'colour'", "'label'", "'expression'", "'condition'", "'js'", "'ocl'", "'true'", "'false'", "'domain'", "'codeLanguage'", "'codeFileExtension'", "'runtimeKind'", "'category'", "'{'", "'}'", "'class'", "'as'", "'extends'", "'message0'", "'tooltip'", "'helpUrl'", "'inputsInline'", "'code'", "'attribute'", "':'", "'min'", "'max'", "'src'", "'width'", "'height'", "'alt'", "'contains'", "'['", "'..'", "']'", "'reference'", "'opposite'", "'value'", "'shadow'", "'widget'", "'uiLabel'", "'help'", "'placeholder'", "'group'", "'order'", "'variant'", "'referenceLabelField'", "'enum'", "','", "'='", "'constraint'", "'on'", "'must'", "'follow'", "'validation'", "'errorMessage'", "'workspace'", "'abstract'", "'output'", "'inline'", "'required'", "'modelId'", "'unique'", "'nonUnique'", "'ordered'", "'unordered'", "'readonly'", "'hidden'"
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

    	public void setGrammarAccess(Model2BlocklyGrammarAccess grammarAccess) {
    		this.grammarAccess = grammarAccess;
    	}

    	@Override
    	protected Grammar getGrammar() {
    		return grammarAccess.getGrammar();
    	}

    	@Override
    	protected String getValueForTokenName(String tokenName) {
    		return tokenName;
    	}



    // $ANTLR start "entryRuleDomainModel"
    // InternalModel2Blockly.g:53:1: entryRuleDomainModel : ruleDomainModel EOF ;
    public final void entryRuleDomainModel() throws RecognitionException {
        try {
            // InternalModel2Blockly.g:54:1: ( ruleDomainModel EOF )
            // InternalModel2Blockly.g:55:1: ruleDomainModel EOF
            {
             before(grammarAccess.getDomainModelRule()); 
            pushFollow(FOLLOW_1);
            ruleDomainModel();

            state._fsp--;

             after(grammarAccess.getDomainModelRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleDomainModel"


    // $ANTLR start "ruleDomainModel"
    // InternalModel2Blockly.g:62:1: ruleDomainModel : ( ( rule__DomainModel__Group__0 ) ) ;
    public final void ruleDomainModel() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:66:2: ( ( ( rule__DomainModel__Group__0 ) ) )
            // InternalModel2Blockly.g:67:2: ( ( rule__DomainModel__Group__0 ) )
            {
            // InternalModel2Blockly.g:67:2: ( ( rule__DomainModel__Group__0 ) )
            // InternalModel2Blockly.g:68:3: ( rule__DomainModel__Group__0 )
            {
             before(grammarAccess.getDomainModelAccess().getGroup()); 
            // InternalModel2Blockly.g:69:3: ( rule__DomainModel__Group__0 )
            // InternalModel2Blockly.g:69:4: rule__DomainModel__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__DomainModel__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getDomainModelAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleDomainModel"


    // $ANTLR start "entryRuleCategoryDef"
    // InternalModel2Blockly.g:78:1: entryRuleCategoryDef : ruleCategoryDef EOF ;
    public final void entryRuleCategoryDef() throws RecognitionException {
        try {
            // InternalModel2Blockly.g:79:1: ( ruleCategoryDef EOF )
            // InternalModel2Blockly.g:80:1: ruleCategoryDef EOF
            {
             before(grammarAccess.getCategoryDefRule()); 
            pushFollow(FOLLOW_1);
            ruleCategoryDef();

            state._fsp--;

             after(grammarAccess.getCategoryDefRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleCategoryDef"


    // $ANTLR start "ruleCategoryDef"
    // InternalModel2Blockly.g:87:1: ruleCategoryDef : ( ( rule__CategoryDef__Group__0 ) ) ;
    public final void ruleCategoryDef() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:91:2: ( ( ( rule__CategoryDef__Group__0 ) ) )
            // InternalModel2Blockly.g:92:2: ( ( rule__CategoryDef__Group__0 ) )
            {
            // InternalModel2Blockly.g:92:2: ( ( rule__CategoryDef__Group__0 ) )
            // InternalModel2Blockly.g:93:3: ( rule__CategoryDef__Group__0 )
            {
             before(grammarAccess.getCategoryDefAccess().getGroup()); 
            // InternalModel2Blockly.g:94:3: ( rule__CategoryDef__Group__0 )
            // InternalModel2Blockly.g:94:4: rule__CategoryDef__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__CategoryDef__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getCategoryDefAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleCategoryDef"


    // $ANTLR start "entryRuleClassDef"
    // InternalModel2Blockly.g:103:1: entryRuleClassDef : ruleClassDef EOF ;
    public final void entryRuleClassDef() throws RecognitionException {
        try {
            // InternalModel2Blockly.g:104:1: ( ruleClassDef EOF )
            // InternalModel2Blockly.g:105:1: ruleClassDef EOF
            {
             before(grammarAccess.getClassDefRule()); 
            pushFollow(FOLLOW_1);
            ruleClassDef();

            state._fsp--;

             after(grammarAccess.getClassDefRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleClassDef"


    // $ANTLR start "ruleClassDef"
    // InternalModel2Blockly.g:112:1: ruleClassDef : ( ( rule__ClassDef__Group__0 ) ) ;
    public final void ruleClassDef() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:116:2: ( ( ( rule__ClassDef__Group__0 ) ) )
            // InternalModel2Blockly.g:117:2: ( ( rule__ClassDef__Group__0 ) )
            {
            // InternalModel2Blockly.g:117:2: ( ( rule__ClassDef__Group__0 ) )
            // InternalModel2Blockly.g:118:3: ( rule__ClassDef__Group__0 )
            {
             before(grammarAccess.getClassDefAccess().getGroup()); 
            // InternalModel2Blockly.g:119:3: ( rule__ClassDef__Group__0 )
            // InternalModel2Blockly.g:119:4: rule__ClassDef__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__ClassDef__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getClassDefAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleClassDef"


    // $ANTLR start "entryRuleFeature"
    // InternalModel2Blockly.g:128:1: entryRuleFeature : ruleFeature EOF ;
    public final void entryRuleFeature() throws RecognitionException {
        try {
            // InternalModel2Blockly.g:129:1: ( ruleFeature EOF )
            // InternalModel2Blockly.g:130:1: ruleFeature EOF
            {
             before(grammarAccess.getFeatureRule()); 
            pushFollow(FOLLOW_1);
            ruleFeature();

            state._fsp--;

             after(grammarAccess.getFeatureRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleFeature"


    // $ANTLR start "ruleFeature"
    // InternalModel2Blockly.g:137:1: ruleFeature : ( ( rule__Feature__Alternatives ) ) ;
    public final void ruleFeature() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:141:2: ( ( ( rule__Feature__Alternatives ) ) )
            // InternalModel2Blockly.g:142:2: ( ( rule__Feature__Alternatives ) )
            {
            // InternalModel2Blockly.g:142:2: ( ( rule__Feature__Alternatives ) )
            // InternalModel2Blockly.g:143:3: ( rule__Feature__Alternatives )
            {
             before(grammarAccess.getFeatureAccess().getAlternatives()); 
            // InternalModel2Blockly.g:144:3: ( rule__Feature__Alternatives )
            // InternalModel2Blockly.g:144:4: rule__Feature__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__Feature__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getFeatureAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleFeature"


    // $ANTLR start "entryRuleAttribute"
    // InternalModel2Blockly.g:153:1: entryRuleAttribute : ruleAttribute EOF ;
    public final void entryRuleAttribute() throws RecognitionException {
        try {
            // InternalModel2Blockly.g:154:1: ( ruleAttribute EOF )
            // InternalModel2Blockly.g:155:1: ruleAttribute EOF
            {
             before(grammarAccess.getAttributeRule()); 
            pushFollow(FOLLOW_1);
            ruleAttribute();

            state._fsp--;

             after(grammarAccess.getAttributeRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleAttribute"


    // $ANTLR start "ruleAttribute"
    // InternalModel2Blockly.g:162:1: ruleAttribute : ( ( rule__Attribute__Group__0 ) ) ;
    public final void ruleAttribute() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:166:2: ( ( ( rule__Attribute__Group__0 ) ) )
            // InternalModel2Blockly.g:167:2: ( ( rule__Attribute__Group__0 ) )
            {
            // InternalModel2Blockly.g:167:2: ( ( rule__Attribute__Group__0 ) )
            // InternalModel2Blockly.g:168:3: ( rule__Attribute__Group__0 )
            {
             before(grammarAccess.getAttributeAccess().getGroup()); 
            // InternalModel2Blockly.g:169:3: ( rule__Attribute__Group__0 )
            // InternalModel2Blockly.g:169:4: rule__Attribute__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Attribute__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getAttributeAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleAttribute"


    // $ANTLR start "entryRuleContainment"
    // InternalModel2Blockly.g:178:1: entryRuleContainment : ruleContainment EOF ;
    public final void entryRuleContainment() throws RecognitionException {
        try {
            // InternalModel2Blockly.g:179:1: ( ruleContainment EOF )
            // InternalModel2Blockly.g:180:1: ruleContainment EOF
            {
             before(grammarAccess.getContainmentRule()); 
            pushFollow(FOLLOW_1);
            ruleContainment();

            state._fsp--;

             after(grammarAccess.getContainmentRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleContainment"


    // $ANTLR start "ruleContainment"
    // InternalModel2Blockly.g:187:1: ruleContainment : ( ( rule__Containment__Group__0 ) ) ;
    public final void ruleContainment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:191:2: ( ( ( rule__Containment__Group__0 ) ) )
            // InternalModel2Blockly.g:192:2: ( ( rule__Containment__Group__0 ) )
            {
            // InternalModel2Blockly.g:192:2: ( ( rule__Containment__Group__0 ) )
            // InternalModel2Blockly.g:193:3: ( rule__Containment__Group__0 )
            {
             before(grammarAccess.getContainmentAccess().getGroup()); 
            // InternalModel2Blockly.g:194:3: ( rule__Containment__Group__0 )
            // InternalModel2Blockly.g:194:4: rule__Containment__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Containment__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getContainmentAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleContainment"


    // $ANTLR start "entryRuleReference"
    // InternalModel2Blockly.g:203:1: entryRuleReference : ruleReference EOF ;
    public final void entryRuleReference() throws RecognitionException {
        try {
            // InternalModel2Blockly.g:204:1: ( ruleReference EOF )
            // InternalModel2Blockly.g:205:1: ruleReference EOF
            {
             before(grammarAccess.getReferenceRule()); 
            pushFollow(FOLLOW_1);
            ruleReference();

            state._fsp--;

             after(grammarAccess.getReferenceRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleReference"


    // $ANTLR start "ruleReference"
    // InternalModel2Blockly.g:212:1: ruleReference : ( ( rule__Reference__Group__0 ) ) ;
    public final void ruleReference() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:216:2: ( ( ( rule__Reference__Group__0 ) ) )
            // InternalModel2Blockly.g:217:2: ( ( rule__Reference__Group__0 ) )
            {
            // InternalModel2Blockly.g:217:2: ( ( rule__Reference__Group__0 ) )
            // InternalModel2Blockly.g:218:3: ( rule__Reference__Group__0 )
            {
             before(grammarAccess.getReferenceAccess().getGroup()); 
            // InternalModel2Blockly.g:219:3: ( rule__Reference__Group__0 )
            // InternalModel2Blockly.g:219:4: rule__Reference__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Reference__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getReferenceAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleReference"


    // $ANTLR start "entryRuleValueInput"
    // InternalModel2Blockly.g:228:1: entryRuleValueInput : ruleValueInput EOF ;
    public final void entryRuleValueInput() throws RecognitionException {
        try {
            // InternalModel2Blockly.g:229:1: ( ruleValueInput EOF )
            // InternalModel2Blockly.g:230:1: ruleValueInput EOF
            {
             before(grammarAccess.getValueInputRule()); 
            pushFollow(FOLLOW_1);
            ruleValueInput();

            state._fsp--;

             after(grammarAccess.getValueInputRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleValueInput"


    // $ANTLR start "ruleValueInput"
    // InternalModel2Blockly.g:237:1: ruleValueInput : ( ( rule__ValueInput__Group__0 ) ) ;
    public final void ruleValueInput() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:241:2: ( ( ( rule__ValueInput__Group__0 ) ) )
            // InternalModel2Blockly.g:242:2: ( ( rule__ValueInput__Group__0 ) )
            {
            // InternalModel2Blockly.g:242:2: ( ( rule__ValueInput__Group__0 ) )
            // InternalModel2Blockly.g:243:3: ( rule__ValueInput__Group__0 )
            {
             before(grammarAccess.getValueInputAccess().getGroup()); 
            // InternalModel2Blockly.g:244:3: ( rule__ValueInput__Group__0 )
            // InternalModel2Blockly.g:244:4: rule__ValueInput__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__ValueInput__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getValueInputAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleValueInput"


    // $ANTLR start "entryRuleCardinality"
    // InternalModel2Blockly.g:253:1: entryRuleCardinality : ruleCardinality EOF ;
    public final void entryRuleCardinality() throws RecognitionException {
        try {
            // InternalModel2Blockly.g:254:1: ( ruleCardinality EOF )
            // InternalModel2Blockly.g:255:1: ruleCardinality EOF
            {
             before(grammarAccess.getCardinalityRule()); 
            pushFollow(FOLLOW_1);
            ruleCardinality();

            state._fsp--;

             after(grammarAccess.getCardinalityRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleCardinality"


    // $ANTLR start "ruleCardinality"
    // InternalModel2Blockly.g:262:1: ruleCardinality : ( ( rule__Cardinality__Group__0 ) ) ;
    public final void ruleCardinality() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:266:2: ( ( ( rule__Cardinality__Group__0 ) ) )
            // InternalModel2Blockly.g:267:2: ( ( rule__Cardinality__Group__0 ) )
            {
            // InternalModel2Blockly.g:267:2: ( ( rule__Cardinality__Group__0 ) )
            // InternalModel2Blockly.g:268:3: ( rule__Cardinality__Group__0 )
            {
             before(grammarAccess.getCardinalityAccess().getGroup()); 
            // InternalModel2Blockly.g:269:3: ( rule__Cardinality__Group__0 )
            // InternalModel2Blockly.g:269:4: rule__Cardinality__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Cardinality__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getCardinalityAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleCardinality"


    // $ANTLR start "entryRuleUiOptions"
    // InternalModel2Blockly.g:278:1: entryRuleUiOptions : ruleUiOptions EOF ;
    public final void entryRuleUiOptions() throws RecognitionException {
        try {
            // InternalModel2Blockly.g:279:1: ( ruleUiOptions EOF )
            // InternalModel2Blockly.g:280:1: ruleUiOptions EOF
            {
             before(grammarAccess.getUiOptionsRule()); 
            pushFollow(FOLLOW_1);
            ruleUiOptions();

            state._fsp--;

             after(grammarAccess.getUiOptionsRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleUiOptions"


    // $ANTLR start "ruleUiOptions"
    // InternalModel2Blockly.g:287:1: ruleUiOptions : ( ( ( rule__UiOptions__Alternatives ) ) ( ( rule__UiOptions__Alternatives )* ) ) ;
    public final void ruleUiOptions() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:291:2: ( ( ( ( rule__UiOptions__Alternatives ) ) ( ( rule__UiOptions__Alternatives )* ) ) )
            // InternalModel2Blockly.g:292:2: ( ( ( rule__UiOptions__Alternatives ) ) ( ( rule__UiOptions__Alternatives )* ) )
            {
            // InternalModel2Blockly.g:292:2: ( ( ( rule__UiOptions__Alternatives ) ) ( ( rule__UiOptions__Alternatives )* ) )
            // InternalModel2Blockly.g:293:3: ( ( rule__UiOptions__Alternatives ) ) ( ( rule__UiOptions__Alternatives )* )
            {
            // InternalModel2Blockly.g:293:3: ( ( rule__UiOptions__Alternatives ) )
            // InternalModel2Blockly.g:294:4: ( rule__UiOptions__Alternatives )
            {
             before(grammarAccess.getUiOptionsAccess().getAlternatives()); 
            // InternalModel2Blockly.g:295:4: ( rule__UiOptions__Alternatives )
            // InternalModel2Blockly.g:295:5: rule__UiOptions__Alternatives
            {
            pushFollow(FOLLOW_3);
            rule__UiOptions__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getUiOptionsAccess().getAlternatives()); 

            }

            // InternalModel2Blockly.g:298:3: ( ( rule__UiOptions__Alternatives )* )
            // InternalModel2Blockly.g:299:4: ( rule__UiOptions__Alternatives )*
            {
             before(grammarAccess.getUiOptionsAccess().getAlternatives()); 
            // InternalModel2Blockly.g:300:4: ( rule__UiOptions__Alternatives )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>=71 && LA1_0<=78)||(LA1_0>=98 && LA1_0<=99)) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalModel2Blockly.g:300:5: rule__UiOptions__Alternatives
            	    {
            	    pushFollow(FOLLOW_3);
            	    rule__UiOptions__Alternatives();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

             after(grammarAccess.getUiOptionsAccess().getAlternatives()); 

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleUiOptions"


    // $ANTLR start "entryRuleAttributeType"
    // InternalModel2Blockly.g:310:1: entryRuleAttributeType : ruleAttributeType EOF ;
    public final void entryRuleAttributeType() throws RecognitionException {
        try {
            // InternalModel2Blockly.g:311:1: ( ruleAttributeType EOF )
            // InternalModel2Blockly.g:312:1: ruleAttributeType EOF
            {
             before(grammarAccess.getAttributeTypeRule()); 
            pushFollow(FOLLOW_1);
            ruleAttributeType();

            state._fsp--;

             after(grammarAccess.getAttributeTypeRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleAttributeType"


    // $ANTLR start "ruleAttributeType"
    // InternalModel2Blockly.g:319:1: ruleAttributeType : ( ( rule__AttributeType__Alternatives ) ) ;
    public final void ruleAttributeType() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:323:2: ( ( ( rule__AttributeType__Alternatives ) ) )
            // InternalModel2Blockly.g:324:2: ( ( rule__AttributeType__Alternatives ) )
            {
            // InternalModel2Blockly.g:324:2: ( ( rule__AttributeType__Alternatives ) )
            // InternalModel2Blockly.g:325:3: ( rule__AttributeType__Alternatives )
            {
             before(grammarAccess.getAttributeTypeAccess().getAlternatives()); 
            // InternalModel2Blockly.g:326:3: ( rule__AttributeType__Alternatives )
            // InternalModel2Blockly.g:326:4: rule__AttributeType__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__AttributeType__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getAttributeTypeAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleAttributeType"


    // $ANTLR start "entryRuleSimpleType"
    // InternalModel2Blockly.g:335:1: entryRuleSimpleType : ruleSimpleType EOF ;
    public final void entryRuleSimpleType() throws RecognitionException {
        try {
            // InternalModel2Blockly.g:336:1: ( ruleSimpleType EOF )
            // InternalModel2Blockly.g:337:1: ruleSimpleType EOF
            {
             before(grammarAccess.getSimpleTypeRule()); 
            pushFollow(FOLLOW_1);
            ruleSimpleType();

            state._fsp--;

             after(grammarAccess.getSimpleTypeRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleSimpleType"


    // $ANTLR start "ruleSimpleType"
    // InternalModel2Blockly.g:344:1: ruleSimpleType : ( ( rule__SimpleType__TypeNameAssignment ) ) ;
    public final void ruleSimpleType() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:348:2: ( ( ( rule__SimpleType__TypeNameAssignment ) ) )
            // InternalModel2Blockly.g:349:2: ( ( rule__SimpleType__TypeNameAssignment ) )
            {
            // InternalModel2Blockly.g:349:2: ( ( rule__SimpleType__TypeNameAssignment ) )
            // InternalModel2Blockly.g:350:3: ( rule__SimpleType__TypeNameAssignment )
            {
             before(grammarAccess.getSimpleTypeAccess().getTypeNameAssignment()); 
            // InternalModel2Blockly.g:351:3: ( rule__SimpleType__TypeNameAssignment )
            // InternalModel2Blockly.g:351:4: rule__SimpleType__TypeNameAssignment
            {
            pushFollow(FOLLOW_2);
            rule__SimpleType__TypeNameAssignment();

            state._fsp--;


            }

             after(grammarAccess.getSimpleTypeAccess().getTypeNameAssignment()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleSimpleType"


    // $ANTLR start "entryRuleEnumType"
    // InternalModel2Blockly.g:360:1: entryRuleEnumType : ruleEnumType EOF ;
    public final void entryRuleEnumType() throws RecognitionException {
        try {
            // InternalModel2Blockly.g:361:1: ( ruleEnumType EOF )
            // InternalModel2Blockly.g:362:1: ruleEnumType EOF
            {
             before(grammarAccess.getEnumTypeRule()); 
            pushFollow(FOLLOW_1);
            ruleEnumType();

            state._fsp--;

             after(grammarAccess.getEnumTypeRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleEnumType"


    // $ANTLR start "ruleEnumType"
    // InternalModel2Blockly.g:369:1: ruleEnumType : ( ( rule__EnumType__Group__0 ) ) ;
    public final void ruleEnumType() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:373:2: ( ( ( rule__EnumType__Group__0 ) ) )
            // InternalModel2Blockly.g:374:2: ( ( rule__EnumType__Group__0 ) )
            {
            // InternalModel2Blockly.g:374:2: ( ( rule__EnumType__Group__0 ) )
            // InternalModel2Blockly.g:375:3: ( rule__EnumType__Group__0 )
            {
             before(grammarAccess.getEnumTypeAccess().getGroup()); 
            // InternalModel2Blockly.g:376:3: ( rule__EnumType__Group__0 )
            // InternalModel2Blockly.g:376:4: rule__EnumType__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__EnumType__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getEnumTypeAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleEnumType"


    // $ANTLR start "entryRuleEnumLiteral"
    // InternalModel2Blockly.g:385:1: entryRuleEnumLiteral : ruleEnumLiteral EOF ;
    public final void entryRuleEnumLiteral() throws RecognitionException {
        try {
            // InternalModel2Blockly.g:386:1: ( ruleEnumLiteral EOF )
            // InternalModel2Blockly.g:387:1: ruleEnumLiteral EOF
            {
             before(grammarAccess.getEnumLiteralRule()); 
            pushFollow(FOLLOW_1);
            ruleEnumLiteral();

            state._fsp--;

             after(grammarAccess.getEnumLiteralRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleEnumLiteral"


    // $ANTLR start "ruleEnumLiteral"
    // InternalModel2Blockly.g:394:1: ruleEnumLiteral : ( ( rule__EnumLiteral__Group__0 ) ) ;
    public final void ruleEnumLiteral() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:398:2: ( ( ( rule__EnumLiteral__Group__0 ) ) )
            // InternalModel2Blockly.g:399:2: ( ( rule__EnumLiteral__Group__0 ) )
            {
            // InternalModel2Blockly.g:399:2: ( ( rule__EnumLiteral__Group__0 ) )
            // InternalModel2Blockly.g:400:3: ( rule__EnumLiteral__Group__0 )
            {
             before(grammarAccess.getEnumLiteralAccess().getGroup()); 
            // InternalModel2Blockly.g:401:3: ( rule__EnumLiteral__Group__0 )
            // InternalModel2Blockly.g:401:4: rule__EnumLiteral__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__EnumLiteral__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getEnumLiteralAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleEnumLiteral"


    // $ANTLR start "entryRuleConstraintDef"
    // InternalModel2Blockly.g:410:1: entryRuleConstraintDef : ruleConstraintDef EOF ;
    public final void entryRuleConstraintDef() throws RecognitionException {
        try {
            // InternalModel2Blockly.g:411:1: ( ruleConstraintDef EOF )
            // InternalModel2Blockly.g:412:1: ruleConstraintDef EOF
            {
             before(grammarAccess.getConstraintDefRule()); 
            pushFollow(FOLLOW_1);
            ruleConstraintDef();

            state._fsp--;

             after(grammarAccess.getConstraintDefRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleConstraintDef"


    // $ANTLR start "ruleConstraintDef"
    // InternalModel2Blockly.g:419:1: ruleConstraintDef : ( ( rule__ConstraintDef__Group__0 ) ) ;
    public final void ruleConstraintDef() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:423:2: ( ( ( rule__ConstraintDef__Group__0 ) ) )
            // InternalModel2Blockly.g:424:2: ( ( rule__ConstraintDef__Group__0 ) )
            {
            // InternalModel2Blockly.g:424:2: ( ( rule__ConstraintDef__Group__0 ) )
            // InternalModel2Blockly.g:425:3: ( rule__ConstraintDef__Group__0 )
            {
             before(grammarAccess.getConstraintDefAccess().getGroup()); 
            // InternalModel2Blockly.g:426:3: ( rule__ConstraintDef__Group__0 )
            // InternalModel2Blockly.g:426:4: rule__ConstraintDef__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__ConstraintDef__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getConstraintDefAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleConstraintDef"


    // $ANTLR start "entryRuleValidationDef"
    // InternalModel2Blockly.g:435:1: entryRuleValidationDef : ruleValidationDef EOF ;
    public final void entryRuleValidationDef() throws RecognitionException {
        try {
            // InternalModel2Blockly.g:436:1: ( ruleValidationDef EOF )
            // InternalModel2Blockly.g:437:1: ruleValidationDef EOF
            {
             before(grammarAccess.getValidationDefRule()); 
            pushFollow(FOLLOW_1);
            ruleValidationDef();

            state._fsp--;

             after(grammarAccess.getValidationDefRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleValidationDef"


    // $ANTLR start "ruleValidationDef"
    // InternalModel2Blockly.g:444:1: ruleValidationDef : ( ( rule__ValidationDef__Group__0 ) ) ;
    public final void ruleValidationDef() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:448:2: ( ( ( rule__ValidationDef__Group__0 ) ) )
            // InternalModel2Blockly.g:449:2: ( ( rule__ValidationDef__Group__0 ) )
            {
            // InternalModel2Blockly.g:449:2: ( ( rule__ValidationDef__Group__0 ) )
            // InternalModel2Blockly.g:450:3: ( rule__ValidationDef__Group__0 )
            {
             before(grammarAccess.getValidationDefAccess().getGroup()); 
            // InternalModel2Blockly.g:451:3: ( rule__ValidationDef__Group__0 )
            // InternalModel2Blockly.g:451:4: rule__ValidationDef__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__ValidationDef__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getValidationDefAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleValidationDef"


    // $ANTLR start "entryRuleWorkspaceConfig"
    // InternalModel2Blockly.g:460:1: entryRuleWorkspaceConfig : ruleWorkspaceConfig EOF ;
    public final void entryRuleWorkspaceConfig() throws RecognitionException {
        try {
            // InternalModel2Blockly.g:461:1: ( ruleWorkspaceConfig EOF )
            // InternalModel2Blockly.g:462:1: ruleWorkspaceConfig EOF
            {
             before(grammarAccess.getWorkspaceConfigRule()); 
            pushFollow(FOLLOW_1);
            ruleWorkspaceConfig();

            state._fsp--;

             after(grammarAccess.getWorkspaceConfigRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleWorkspaceConfig"


    // $ANTLR start "ruleWorkspaceConfig"
    // InternalModel2Blockly.g:469:1: ruleWorkspaceConfig : ( ( rule__WorkspaceConfig__Group__0 ) ) ;
    public final void ruleWorkspaceConfig() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:473:2: ( ( ( rule__WorkspaceConfig__Group__0 ) ) )
            // InternalModel2Blockly.g:474:2: ( ( rule__WorkspaceConfig__Group__0 ) )
            {
            // InternalModel2Blockly.g:474:2: ( ( rule__WorkspaceConfig__Group__0 ) )
            // InternalModel2Blockly.g:475:3: ( rule__WorkspaceConfig__Group__0 )
            {
             before(grammarAccess.getWorkspaceConfigAccess().getGroup()); 
            // InternalModel2Blockly.g:476:3: ( rule__WorkspaceConfig__Group__0 )
            // InternalModel2Blockly.g:476:4: rule__WorkspaceConfig__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__WorkspaceConfig__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getWorkspaceConfigAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleWorkspaceConfig"


    // $ANTLR start "entryRuleWorkspaceOption"
    // InternalModel2Blockly.g:485:1: entryRuleWorkspaceOption : ruleWorkspaceOption EOF ;
    public final void entryRuleWorkspaceOption() throws RecognitionException {
        try {
            // InternalModel2Blockly.g:486:1: ( ruleWorkspaceOption EOF )
            // InternalModel2Blockly.g:487:1: ruleWorkspaceOption EOF
            {
             before(grammarAccess.getWorkspaceOptionRule()); 
            pushFollow(FOLLOW_1);
            ruleWorkspaceOption();

            state._fsp--;

             after(grammarAccess.getWorkspaceOptionRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleWorkspaceOption"


    // $ANTLR start "ruleWorkspaceOption"
    // InternalModel2Blockly.g:494:1: ruleWorkspaceOption : ( ( rule__WorkspaceOption__Alternatives ) ) ;
    public final void ruleWorkspaceOption() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:498:2: ( ( ( rule__WorkspaceOption__Alternatives ) ) )
            // InternalModel2Blockly.g:499:2: ( ( rule__WorkspaceOption__Alternatives ) )
            {
            // InternalModel2Blockly.g:499:2: ( ( rule__WorkspaceOption__Alternatives ) )
            // InternalModel2Blockly.g:500:3: ( rule__WorkspaceOption__Alternatives )
            {
             before(grammarAccess.getWorkspaceOptionAccess().getAlternatives()); 
            // InternalModel2Blockly.g:501:3: ( rule__WorkspaceOption__Alternatives )
            // InternalModel2Blockly.g:501:4: rule__WorkspaceOption__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__WorkspaceOption__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getWorkspaceOptionAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleWorkspaceOption"


    // $ANTLR start "entryRuleStringOption"
    // InternalModel2Blockly.g:510:1: entryRuleStringOption : ruleStringOption EOF ;
    public final void entryRuleStringOption() throws RecognitionException {
        try {
            // InternalModel2Blockly.g:511:1: ( ruleStringOption EOF )
            // InternalModel2Blockly.g:512:1: ruleStringOption EOF
            {
             before(grammarAccess.getStringOptionRule()); 
            pushFollow(FOLLOW_1);
            ruleStringOption();

            state._fsp--;

             after(grammarAccess.getStringOptionRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleStringOption"


    // $ANTLR start "ruleStringOption"
    // InternalModel2Blockly.g:519:1: ruleStringOption : ( ( rule__StringOption__Group__0 ) ) ;
    public final void ruleStringOption() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:523:2: ( ( ( rule__StringOption__Group__0 ) ) )
            // InternalModel2Blockly.g:524:2: ( ( rule__StringOption__Group__0 ) )
            {
            // InternalModel2Blockly.g:524:2: ( ( rule__StringOption__Group__0 ) )
            // InternalModel2Blockly.g:525:3: ( rule__StringOption__Group__0 )
            {
             before(grammarAccess.getStringOptionAccess().getGroup()); 
            // InternalModel2Blockly.g:526:3: ( rule__StringOption__Group__0 )
            // InternalModel2Blockly.g:526:4: rule__StringOption__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__StringOption__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getStringOptionAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleStringOption"


    // $ANTLR start "entryRuleIntOption"
    // InternalModel2Blockly.g:535:1: entryRuleIntOption : ruleIntOption EOF ;
    public final void entryRuleIntOption() throws RecognitionException {
        try {
            // InternalModel2Blockly.g:536:1: ( ruleIntOption EOF )
            // InternalModel2Blockly.g:537:1: ruleIntOption EOF
            {
             before(grammarAccess.getIntOptionRule()); 
            pushFollow(FOLLOW_1);
            ruleIntOption();

            state._fsp--;

             after(grammarAccess.getIntOptionRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleIntOption"


    // $ANTLR start "ruleIntOption"
    // InternalModel2Blockly.g:544:1: ruleIntOption : ( ( rule__IntOption__Group__0 ) ) ;
    public final void ruleIntOption() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:548:2: ( ( ( rule__IntOption__Group__0 ) ) )
            // InternalModel2Blockly.g:549:2: ( ( rule__IntOption__Group__0 ) )
            {
            // InternalModel2Blockly.g:549:2: ( ( rule__IntOption__Group__0 ) )
            // InternalModel2Blockly.g:550:3: ( rule__IntOption__Group__0 )
            {
             before(grammarAccess.getIntOptionAccess().getGroup()); 
            // InternalModel2Blockly.g:551:3: ( rule__IntOption__Group__0 )
            // InternalModel2Blockly.g:551:4: rule__IntOption__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__IntOption__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getIntOptionAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleIntOption"


    // $ANTLR start "entryRuleBoolOption"
    // InternalModel2Blockly.g:560:1: entryRuleBoolOption : ruleBoolOption EOF ;
    public final void entryRuleBoolOption() throws RecognitionException {
        try {
            // InternalModel2Blockly.g:561:1: ( ruleBoolOption EOF )
            // InternalModel2Blockly.g:562:1: ruleBoolOption EOF
            {
             before(grammarAccess.getBoolOptionRule()); 
            pushFollow(FOLLOW_1);
            ruleBoolOption();

            state._fsp--;

             after(grammarAccess.getBoolOptionRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleBoolOption"


    // $ANTLR start "ruleBoolOption"
    // InternalModel2Blockly.g:569:1: ruleBoolOption : ( ( rule__BoolOption__Group__0 ) ) ;
    public final void ruleBoolOption() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:573:2: ( ( ( rule__BoolOption__Group__0 ) ) )
            // InternalModel2Blockly.g:574:2: ( ( rule__BoolOption__Group__0 ) )
            {
            // InternalModel2Blockly.g:574:2: ( ( rule__BoolOption__Group__0 ) )
            // InternalModel2Blockly.g:575:3: ( rule__BoolOption__Group__0 )
            {
             before(grammarAccess.getBoolOptionAccess().getGroup()); 
            // InternalModel2Blockly.g:576:3: ( rule__BoolOption__Group__0 )
            // InternalModel2Blockly.g:576:4: rule__BoolOption__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__BoolOption__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getBoolOptionAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleBoolOption"


    // $ANTLR start "entryRuleObjectOption"
    // InternalModel2Blockly.g:585:1: entryRuleObjectOption : ruleObjectOption EOF ;
    public final void entryRuleObjectOption() throws RecognitionException {
        try {
            // InternalModel2Blockly.g:586:1: ( ruleObjectOption EOF )
            // InternalModel2Blockly.g:587:1: ruleObjectOption EOF
            {
             before(grammarAccess.getObjectOptionRule()); 
            pushFollow(FOLLOW_1);
            ruleObjectOption();

            state._fsp--;

             after(grammarAccess.getObjectOptionRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleObjectOption"


    // $ANTLR start "ruleObjectOption"
    // InternalModel2Blockly.g:594:1: ruleObjectOption : ( ( rule__ObjectOption__Group__0 ) ) ;
    public final void ruleObjectOption() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:598:2: ( ( ( rule__ObjectOption__Group__0 ) ) )
            // InternalModel2Blockly.g:599:2: ( ( rule__ObjectOption__Group__0 ) )
            {
            // InternalModel2Blockly.g:599:2: ( ( rule__ObjectOption__Group__0 ) )
            // InternalModel2Blockly.g:600:3: ( rule__ObjectOption__Group__0 )
            {
             before(grammarAccess.getObjectOptionAccess().getGroup()); 
            // InternalModel2Blockly.g:601:3: ( rule__ObjectOption__Group__0 )
            // InternalModel2Blockly.g:601:4: rule__ObjectOption__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__ObjectOption__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getObjectOptionAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleObjectOption"


    // $ANTLR start "ruleUiWidget"
    // InternalModel2Blockly.g:610:1: ruleUiWidget : ( ( rule__UiWidget__Alternatives ) ) ;
    public final void ruleUiWidget() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:614:1: ( ( ( rule__UiWidget__Alternatives ) ) )
            // InternalModel2Blockly.g:615:2: ( ( rule__UiWidget__Alternatives ) )
            {
            // InternalModel2Blockly.g:615:2: ( ( rule__UiWidget__Alternatives ) )
            // InternalModel2Blockly.g:616:3: ( rule__UiWidget__Alternatives )
            {
             before(grammarAccess.getUiWidgetAccess().getAlternatives()); 
            // InternalModel2Blockly.g:617:3: ( rule__UiWidget__Alternatives )
            // InternalModel2Blockly.g:617:4: rule__UiWidget__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__UiWidget__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getUiWidgetAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleUiWidget"


    // $ANTLR start "ruleUiVariant"
    // InternalModel2Blockly.g:626:1: ruleUiVariant : ( ( rule__UiVariant__Alternatives ) ) ;
    public final void ruleUiVariant() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:630:1: ( ( ( rule__UiVariant__Alternatives ) ) )
            // InternalModel2Blockly.g:631:2: ( ( rule__UiVariant__Alternatives ) )
            {
            // InternalModel2Blockly.g:631:2: ( ( rule__UiVariant__Alternatives ) )
            // InternalModel2Blockly.g:632:3: ( rule__UiVariant__Alternatives )
            {
             before(grammarAccess.getUiVariantAccess().getAlternatives()); 
            // InternalModel2Blockly.g:633:3: ( rule__UiVariant__Alternatives )
            // InternalModel2Blockly.g:633:4: rule__UiVariant__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__UiVariant__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getUiVariantAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleUiVariant"


    // $ANTLR start "ruleSimpleTypeName"
    // InternalModel2Blockly.g:642:1: ruleSimpleTypeName : ( ( rule__SimpleTypeName__Alternatives ) ) ;
    public final void ruleSimpleTypeName() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:646:1: ( ( ( rule__SimpleTypeName__Alternatives ) ) )
            // InternalModel2Blockly.g:647:2: ( ( rule__SimpleTypeName__Alternatives ) )
            {
            // InternalModel2Blockly.g:647:2: ( ( rule__SimpleTypeName__Alternatives ) )
            // InternalModel2Blockly.g:648:3: ( rule__SimpleTypeName__Alternatives )
            {
             before(grammarAccess.getSimpleTypeNameAccess().getAlternatives()); 
            // InternalModel2Blockly.g:649:3: ( rule__SimpleTypeName__Alternatives )
            // InternalModel2Blockly.g:649:4: rule__SimpleTypeName__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__SimpleTypeName__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getSimpleTypeNameAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleSimpleTypeName"


    // $ANTLR start "ruleValidationKind"
    // InternalModel2Blockly.g:658:1: ruleValidationKind : ( ( rule__ValidationKind__Alternatives ) ) ;
    public final void ruleValidationKind() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:662:1: ( ( ( rule__ValidationKind__Alternatives ) ) )
            // InternalModel2Blockly.g:663:2: ( ( rule__ValidationKind__Alternatives ) )
            {
            // InternalModel2Blockly.g:663:2: ( ( rule__ValidationKind__Alternatives ) )
            // InternalModel2Blockly.g:664:3: ( rule__ValidationKind__Alternatives )
            {
             before(grammarAccess.getValidationKindAccess().getAlternatives()); 
            // InternalModel2Blockly.g:665:3: ( rule__ValidationKind__Alternatives )
            // InternalModel2Blockly.g:665:4: rule__ValidationKind__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__ValidationKind__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getValidationKindAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleValidationKind"


    // $ANTLR start "ruleBoolVal"
    // InternalModel2Blockly.g:674:1: ruleBoolVal : ( ( rule__BoolVal__Alternatives ) ) ;
    public final void ruleBoolVal() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:678:1: ( ( ( rule__BoolVal__Alternatives ) ) )
            // InternalModel2Blockly.g:679:2: ( ( rule__BoolVal__Alternatives ) )
            {
            // InternalModel2Blockly.g:679:2: ( ( rule__BoolVal__Alternatives ) )
            // InternalModel2Blockly.g:680:3: ( rule__BoolVal__Alternatives )
            {
             before(grammarAccess.getBoolValAccess().getAlternatives()); 
            // InternalModel2Blockly.g:681:3: ( rule__BoolVal__Alternatives )
            // InternalModel2Blockly.g:681:4: rule__BoolVal__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__BoolVal__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getBoolValAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleBoolVal"


    // $ANTLR start "rule__Feature__Alternatives"
    // InternalModel2Blockly.g:689:1: rule__Feature__Alternatives : ( ( ruleAttribute ) | ( ruleContainment ) | ( ruleReference ) | ( ruleValueInput ) );
    public final void rule__Feature__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:693:1: ( ( ruleAttribute ) | ( ruleContainment ) | ( ruleReference ) | ( ruleValueInput ) )
            int alt2=4;
            switch ( input.LA(1) ) {
            case 55:
                {
                alt2=1;
                }
                break;
            case 63:
                {
                alt2=2;
                }
                break;
            case 67:
                {
                alt2=3;
                }
                break;
            case 69:
                {
                alt2=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }

            switch (alt2) {
                case 1 :
                    // InternalModel2Blockly.g:694:2: ( ruleAttribute )
                    {
                    // InternalModel2Blockly.g:694:2: ( ruleAttribute )
                    // InternalModel2Blockly.g:695:3: ruleAttribute
                    {
                     before(grammarAccess.getFeatureAccess().getAttributeParserRuleCall_0()); 
                    pushFollow(FOLLOW_2);
                    ruleAttribute();

                    state._fsp--;

                     after(grammarAccess.getFeatureAccess().getAttributeParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalModel2Blockly.g:700:2: ( ruleContainment )
                    {
                    // InternalModel2Blockly.g:700:2: ( ruleContainment )
                    // InternalModel2Blockly.g:701:3: ruleContainment
                    {
                     before(grammarAccess.getFeatureAccess().getContainmentParserRuleCall_1()); 
                    pushFollow(FOLLOW_2);
                    ruleContainment();

                    state._fsp--;

                     after(grammarAccess.getFeatureAccess().getContainmentParserRuleCall_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalModel2Blockly.g:706:2: ( ruleReference )
                    {
                    // InternalModel2Blockly.g:706:2: ( ruleReference )
                    // InternalModel2Blockly.g:707:3: ruleReference
                    {
                     before(grammarAccess.getFeatureAccess().getReferenceParserRuleCall_2()); 
                    pushFollow(FOLLOW_2);
                    ruleReference();

                    state._fsp--;

                     after(grammarAccess.getFeatureAccess().getReferenceParserRuleCall_2()); 

                    }


                    }
                    break;
                case 4 :
                    // InternalModel2Blockly.g:712:2: ( ruleValueInput )
                    {
                    // InternalModel2Blockly.g:712:2: ( ruleValueInput )
                    // InternalModel2Blockly.g:713:3: ruleValueInput
                    {
                     before(grammarAccess.getFeatureAccess().getValueInputParserRuleCall_3()); 
                    pushFollow(FOLLOW_2);
                    ruleValueInput();

                    state._fsp--;

                     after(grammarAccess.getFeatureAccess().getValueInputParserRuleCall_3()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Feature__Alternatives"


    // $ANTLR start "rule__UiOptions__Alternatives"
    // InternalModel2Blockly.g:722:1: rule__UiOptions__Alternatives : ( ( ( rule__UiOptions__Group_0__0 ) ) | ( ( rule__UiOptions__Group_1__0 ) ) | ( ( rule__UiOptions__Group_2__0 ) ) | ( ( rule__UiOptions__Group_3__0 ) ) | ( ( rule__UiOptions__Group_4__0 ) ) | ( ( rule__UiOptions__Group_5__0 ) ) | ( ( rule__UiOptions__UiReadonlyAssignment_6 ) ) | ( ( rule__UiOptions__UiHiddenAssignment_7 ) ) | ( ( rule__UiOptions__Group_8__0 ) ) | ( ( rule__UiOptions__Group_9__0 ) ) );
    public final void rule__UiOptions__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:726:1: ( ( ( rule__UiOptions__Group_0__0 ) ) | ( ( rule__UiOptions__Group_1__0 ) ) | ( ( rule__UiOptions__Group_2__0 ) ) | ( ( rule__UiOptions__Group_3__0 ) ) | ( ( rule__UiOptions__Group_4__0 ) ) | ( ( rule__UiOptions__Group_5__0 ) ) | ( ( rule__UiOptions__UiReadonlyAssignment_6 ) ) | ( ( rule__UiOptions__UiHiddenAssignment_7 ) ) | ( ( rule__UiOptions__Group_8__0 ) ) | ( ( rule__UiOptions__Group_9__0 ) ) )
            int alt3=10;
            switch ( input.LA(1) ) {
            case 71:
                {
                alt3=1;
                }
                break;
            case 72:
                {
                alt3=2;
                }
                break;
            case 73:
                {
                alt3=3;
                }
                break;
            case 74:
                {
                alt3=4;
                }
                break;
            case 75:
                {
                alt3=5;
                }
                break;
            case 76:
                {
                alt3=6;
                }
                break;
            case 98:
                {
                alt3=7;
                }
                break;
            case 99:
                {
                alt3=8;
                }
                break;
            case 77:
                {
                alt3=9;
                }
                break;
            case 78:
                {
                alt3=10;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }

            switch (alt3) {
                case 1 :
                    // InternalModel2Blockly.g:727:2: ( ( rule__UiOptions__Group_0__0 ) )
                    {
                    // InternalModel2Blockly.g:727:2: ( ( rule__UiOptions__Group_0__0 ) )
                    // InternalModel2Blockly.g:728:3: ( rule__UiOptions__Group_0__0 )
                    {
                     before(grammarAccess.getUiOptionsAccess().getGroup_0()); 
                    // InternalModel2Blockly.g:729:3: ( rule__UiOptions__Group_0__0 )
                    // InternalModel2Blockly.g:729:4: rule__UiOptions__Group_0__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__UiOptions__Group_0__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getUiOptionsAccess().getGroup_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalModel2Blockly.g:733:2: ( ( rule__UiOptions__Group_1__0 ) )
                    {
                    // InternalModel2Blockly.g:733:2: ( ( rule__UiOptions__Group_1__0 ) )
                    // InternalModel2Blockly.g:734:3: ( rule__UiOptions__Group_1__0 )
                    {
                     before(grammarAccess.getUiOptionsAccess().getGroup_1()); 
                    // InternalModel2Blockly.g:735:3: ( rule__UiOptions__Group_1__0 )
                    // InternalModel2Blockly.g:735:4: rule__UiOptions__Group_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__UiOptions__Group_1__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getUiOptionsAccess().getGroup_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalModel2Blockly.g:739:2: ( ( rule__UiOptions__Group_2__0 ) )
                    {
                    // InternalModel2Blockly.g:739:2: ( ( rule__UiOptions__Group_2__0 ) )
                    // InternalModel2Blockly.g:740:3: ( rule__UiOptions__Group_2__0 )
                    {
                     before(grammarAccess.getUiOptionsAccess().getGroup_2()); 
                    // InternalModel2Blockly.g:741:3: ( rule__UiOptions__Group_2__0 )
                    // InternalModel2Blockly.g:741:4: rule__UiOptions__Group_2__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__UiOptions__Group_2__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getUiOptionsAccess().getGroup_2()); 

                    }


                    }
                    break;
                case 4 :
                    // InternalModel2Blockly.g:745:2: ( ( rule__UiOptions__Group_3__0 ) )
                    {
                    // InternalModel2Blockly.g:745:2: ( ( rule__UiOptions__Group_3__0 ) )
                    // InternalModel2Blockly.g:746:3: ( rule__UiOptions__Group_3__0 )
                    {
                     before(grammarAccess.getUiOptionsAccess().getGroup_3()); 
                    // InternalModel2Blockly.g:747:3: ( rule__UiOptions__Group_3__0 )
                    // InternalModel2Blockly.g:747:4: rule__UiOptions__Group_3__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__UiOptions__Group_3__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getUiOptionsAccess().getGroup_3()); 

                    }


                    }
                    break;
                case 5 :
                    // InternalModel2Blockly.g:751:2: ( ( rule__UiOptions__Group_4__0 ) )
                    {
                    // InternalModel2Blockly.g:751:2: ( ( rule__UiOptions__Group_4__0 ) )
                    // InternalModel2Blockly.g:752:3: ( rule__UiOptions__Group_4__0 )
                    {
                     before(grammarAccess.getUiOptionsAccess().getGroup_4()); 
                    // InternalModel2Blockly.g:753:3: ( rule__UiOptions__Group_4__0 )
                    // InternalModel2Blockly.g:753:4: rule__UiOptions__Group_4__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__UiOptions__Group_4__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getUiOptionsAccess().getGroup_4()); 

                    }


                    }
                    break;
                case 6 :
                    // InternalModel2Blockly.g:757:2: ( ( rule__UiOptions__Group_5__0 ) )
                    {
                    // InternalModel2Blockly.g:757:2: ( ( rule__UiOptions__Group_5__0 ) )
                    // InternalModel2Blockly.g:758:3: ( rule__UiOptions__Group_5__0 )
                    {
                     before(grammarAccess.getUiOptionsAccess().getGroup_5()); 
                    // InternalModel2Blockly.g:759:3: ( rule__UiOptions__Group_5__0 )
                    // InternalModel2Blockly.g:759:4: rule__UiOptions__Group_5__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__UiOptions__Group_5__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getUiOptionsAccess().getGroup_5()); 

                    }


                    }
                    break;
                case 7 :
                    // InternalModel2Blockly.g:763:2: ( ( rule__UiOptions__UiReadonlyAssignment_6 ) )
                    {
                    // InternalModel2Blockly.g:763:2: ( ( rule__UiOptions__UiReadonlyAssignment_6 ) )
                    // InternalModel2Blockly.g:764:3: ( rule__UiOptions__UiReadonlyAssignment_6 )
                    {
                     before(grammarAccess.getUiOptionsAccess().getUiReadonlyAssignment_6()); 
                    // InternalModel2Blockly.g:765:3: ( rule__UiOptions__UiReadonlyAssignment_6 )
                    // InternalModel2Blockly.g:765:4: rule__UiOptions__UiReadonlyAssignment_6
                    {
                    pushFollow(FOLLOW_2);
                    rule__UiOptions__UiReadonlyAssignment_6();

                    state._fsp--;


                    }

                     after(grammarAccess.getUiOptionsAccess().getUiReadonlyAssignment_6()); 

                    }


                    }
                    break;
                case 8 :
                    // InternalModel2Blockly.g:769:2: ( ( rule__UiOptions__UiHiddenAssignment_7 ) )
                    {
                    // InternalModel2Blockly.g:769:2: ( ( rule__UiOptions__UiHiddenAssignment_7 ) )
                    // InternalModel2Blockly.g:770:3: ( rule__UiOptions__UiHiddenAssignment_7 )
                    {
                     before(grammarAccess.getUiOptionsAccess().getUiHiddenAssignment_7()); 
                    // InternalModel2Blockly.g:771:3: ( rule__UiOptions__UiHiddenAssignment_7 )
                    // InternalModel2Blockly.g:771:4: rule__UiOptions__UiHiddenAssignment_7
                    {
                    pushFollow(FOLLOW_2);
                    rule__UiOptions__UiHiddenAssignment_7();

                    state._fsp--;


                    }

                     after(grammarAccess.getUiOptionsAccess().getUiHiddenAssignment_7()); 

                    }


                    }
                    break;
                case 9 :
                    // InternalModel2Blockly.g:775:2: ( ( rule__UiOptions__Group_8__0 ) )
                    {
                    // InternalModel2Blockly.g:775:2: ( ( rule__UiOptions__Group_8__0 ) )
                    // InternalModel2Blockly.g:776:3: ( rule__UiOptions__Group_8__0 )
                    {
                     before(grammarAccess.getUiOptionsAccess().getGroup_8()); 
                    // InternalModel2Blockly.g:777:3: ( rule__UiOptions__Group_8__0 )
                    // InternalModel2Blockly.g:777:4: rule__UiOptions__Group_8__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__UiOptions__Group_8__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getUiOptionsAccess().getGroup_8()); 

                    }


                    }
                    break;
                case 10 :
                    // InternalModel2Blockly.g:781:2: ( ( rule__UiOptions__Group_9__0 ) )
                    {
                    // InternalModel2Blockly.g:781:2: ( ( rule__UiOptions__Group_9__0 ) )
                    // InternalModel2Blockly.g:782:3: ( rule__UiOptions__Group_9__0 )
                    {
                     before(grammarAccess.getUiOptionsAccess().getGroup_9()); 
                    // InternalModel2Blockly.g:783:3: ( rule__UiOptions__Group_9__0 )
                    // InternalModel2Blockly.g:783:4: rule__UiOptions__Group_9__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__UiOptions__Group_9__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getUiOptionsAccess().getGroup_9()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UiOptions__Alternatives"


    // $ANTLR start "rule__AttributeType__Alternatives"
    // InternalModel2Blockly.g:791:1: rule__AttributeType__Alternatives : ( ( ruleSimpleType ) | ( ruleEnumType ) );
    public final void rule__AttributeType__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:795:1: ( ( ruleSimpleType ) | ( ruleEnumType ) )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( ((LA4_0>=20 && LA4_0<=21)||(LA4_0>=28 && LA4_0<=33)) ) {
                alt4=1;
            }
            else if ( (LA4_0==79) ) {
                alt4=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // InternalModel2Blockly.g:796:2: ( ruleSimpleType )
                    {
                    // InternalModel2Blockly.g:796:2: ( ruleSimpleType )
                    // InternalModel2Blockly.g:797:3: ruleSimpleType
                    {
                     before(grammarAccess.getAttributeTypeAccess().getSimpleTypeParserRuleCall_0()); 
                    pushFollow(FOLLOW_2);
                    ruleSimpleType();

                    state._fsp--;

                     after(grammarAccess.getAttributeTypeAccess().getSimpleTypeParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalModel2Blockly.g:802:2: ( ruleEnumType )
                    {
                    // InternalModel2Blockly.g:802:2: ( ruleEnumType )
                    // InternalModel2Blockly.g:803:3: ruleEnumType
                    {
                     before(grammarAccess.getAttributeTypeAccess().getEnumTypeParserRuleCall_1()); 
                    pushFollow(FOLLOW_2);
                    ruleEnumType();

                    state._fsp--;

                     after(grammarAccess.getAttributeTypeAccess().getEnumTypeParserRuleCall_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AttributeType__Alternatives"


    // $ANTLR start "rule__WorkspaceOption__Alternatives"
    // InternalModel2Blockly.g:812:1: rule__WorkspaceOption__Alternatives : ( ( ruleStringOption ) | ( ruleIntOption ) | ( ruleBoolOption ) | ( ruleObjectOption ) );
    public final void rule__WorkspaceOption__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:816:1: ( ( ruleStringOption ) | ( ruleIntOption ) | ( ruleBoolOption ) | ( ruleObjectOption ) )
            int alt5=4;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==RULE_ID) ) {
                int LA5_1 = input.LA(2);

                if ( (LA5_1==56) ) {
                    switch ( input.LA(3) ) {
                    case RULE_INT:
                        {
                        alt5=2;
                        }
                        break;
                    case 45:
                        {
                        alt5=4;
                        }
                        break;
                    case RULE_STRING:
                        {
                        alt5=1;
                        }
                        break;
                    case 38:
                    case 39:
                        {
                        alt5=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 5, 2, input);

                        throw nvae;
                    }

                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 5, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // InternalModel2Blockly.g:817:2: ( ruleStringOption )
                    {
                    // InternalModel2Blockly.g:817:2: ( ruleStringOption )
                    // InternalModel2Blockly.g:818:3: ruleStringOption
                    {
                     before(grammarAccess.getWorkspaceOptionAccess().getStringOptionParserRuleCall_0()); 
                    pushFollow(FOLLOW_2);
                    ruleStringOption();

                    state._fsp--;

                     after(grammarAccess.getWorkspaceOptionAccess().getStringOptionParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalModel2Blockly.g:823:2: ( ruleIntOption )
                    {
                    // InternalModel2Blockly.g:823:2: ( ruleIntOption )
                    // InternalModel2Blockly.g:824:3: ruleIntOption
                    {
                     before(grammarAccess.getWorkspaceOptionAccess().getIntOptionParserRuleCall_1()); 
                    pushFollow(FOLLOW_2);
                    ruleIntOption();

                    state._fsp--;

                     after(grammarAccess.getWorkspaceOptionAccess().getIntOptionParserRuleCall_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalModel2Blockly.g:829:2: ( ruleBoolOption )
                    {
                    // InternalModel2Blockly.g:829:2: ( ruleBoolOption )
                    // InternalModel2Blockly.g:830:3: ruleBoolOption
                    {
                     before(grammarAccess.getWorkspaceOptionAccess().getBoolOptionParserRuleCall_2()); 
                    pushFollow(FOLLOW_2);
                    ruleBoolOption();

                    state._fsp--;

                     after(grammarAccess.getWorkspaceOptionAccess().getBoolOptionParserRuleCall_2()); 

                    }


                    }
                    break;
                case 4 :
                    // InternalModel2Blockly.g:835:2: ( ruleObjectOption )
                    {
                    // InternalModel2Blockly.g:835:2: ( ruleObjectOption )
                    // InternalModel2Blockly.g:836:3: ruleObjectOption
                    {
                     before(grammarAccess.getWorkspaceOptionAccess().getObjectOptionParserRuleCall_3()); 
                    pushFollow(FOLLOW_2);
                    ruleObjectOption();

                    state._fsp--;

                     after(grammarAccess.getWorkspaceOptionAccess().getObjectOptionParserRuleCall_3()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WorkspaceOption__Alternatives"


    // $ANTLR start "rule__UiWidget__Alternatives"
    // InternalModel2Blockly.g:845:1: rule__UiWidget__Alternatives : ( ( ( 'text' ) ) | ( ( 'textarea' ) ) | ( ( 'number' ) ) | ( ( 'slider' ) ) | ( ( 'switch' ) ) | ( ( 'checkbox' ) ) | ( ( 'select' ) ) | ( ( 'radio' ) ) | ( ( 'color' ) ) | ( ( 'angle' ) ) | ( ( 'image' ) ) | ( ( 'reference-select' ) ) | ( ( 'slot' ) ) | ( ( 'expression-slot' ) ) );
    public final void rule__UiWidget__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:849:1: ( ( ( 'text' ) ) | ( ( 'textarea' ) ) | ( ( 'number' ) ) | ( ( 'slider' ) ) | ( ( 'switch' ) ) | ( ( 'checkbox' ) ) | ( ( 'select' ) ) | ( ( 'radio' ) ) | ( ( 'color' ) ) | ( ( 'angle' ) ) | ( ( 'image' ) ) | ( ( 'reference-select' ) ) | ( ( 'slot' ) ) | ( ( 'expression-slot' ) ) )
            int alt6=14;
            switch ( input.LA(1) ) {
            case 11:
                {
                alt6=1;
                }
                break;
            case 12:
                {
                alt6=2;
                }
                break;
            case 13:
                {
                alt6=3;
                }
                break;
            case 14:
                {
                alt6=4;
                }
                break;
            case 15:
                {
                alt6=5;
                }
                break;
            case 16:
                {
                alt6=6;
                }
                break;
            case 17:
                {
                alt6=7;
                }
                break;
            case 18:
                {
                alt6=8;
                }
                break;
            case 19:
                {
                alt6=9;
                }
                break;
            case 20:
                {
                alt6=10;
                }
                break;
            case 21:
                {
                alt6=11;
                }
                break;
            case 22:
                {
                alt6=12;
                }
                break;
            case 23:
                {
                alt6=13;
                }
                break;
            case 24:
                {
                alt6=14;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }

            switch (alt6) {
                case 1 :
                    // InternalModel2Blockly.g:850:2: ( ( 'text' ) )
                    {
                    // InternalModel2Blockly.g:850:2: ( ( 'text' ) )
                    // InternalModel2Blockly.g:851:3: ( 'text' )
                    {
                     before(grammarAccess.getUiWidgetAccess().getTEXTEnumLiteralDeclaration_0()); 
                    // InternalModel2Blockly.g:852:3: ( 'text' )
                    // InternalModel2Blockly.g:852:4: 'text'
                    {
                    match(input,11,FOLLOW_2); 

                    }

                     after(grammarAccess.getUiWidgetAccess().getTEXTEnumLiteralDeclaration_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalModel2Blockly.g:856:2: ( ( 'textarea' ) )
                    {
                    // InternalModel2Blockly.g:856:2: ( ( 'textarea' ) )
                    // InternalModel2Blockly.g:857:3: ( 'textarea' )
                    {
                     before(grammarAccess.getUiWidgetAccess().getTEXTAREAEnumLiteralDeclaration_1()); 
                    // InternalModel2Blockly.g:858:3: ( 'textarea' )
                    // InternalModel2Blockly.g:858:4: 'textarea'
                    {
                    match(input,12,FOLLOW_2); 

                    }

                     after(grammarAccess.getUiWidgetAccess().getTEXTAREAEnumLiteralDeclaration_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalModel2Blockly.g:862:2: ( ( 'number' ) )
                    {
                    // InternalModel2Blockly.g:862:2: ( ( 'number' ) )
                    // InternalModel2Blockly.g:863:3: ( 'number' )
                    {
                     before(grammarAccess.getUiWidgetAccess().getNUMBEREnumLiteralDeclaration_2()); 
                    // InternalModel2Blockly.g:864:3: ( 'number' )
                    // InternalModel2Blockly.g:864:4: 'number'
                    {
                    match(input,13,FOLLOW_2); 

                    }

                     after(grammarAccess.getUiWidgetAccess().getNUMBEREnumLiteralDeclaration_2()); 

                    }


                    }
                    break;
                case 4 :
                    // InternalModel2Blockly.g:868:2: ( ( 'slider' ) )
                    {
                    // InternalModel2Blockly.g:868:2: ( ( 'slider' ) )
                    // InternalModel2Blockly.g:869:3: ( 'slider' )
                    {
                     before(grammarAccess.getUiWidgetAccess().getSLIDEREnumLiteralDeclaration_3()); 
                    // InternalModel2Blockly.g:870:3: ( 'slider' )
                    // InternalModel2Blockly.g:870:4: 'slider'
                    {
                    match(input,14,FOLLOW_2); 

                    }

                     after(grammarAccess.getUiWidgetAccess().getSLIDEREnumLiteralDeclaration_3()); 

                    }


                    }
                    break;
                case 5 :
                    // InternalModel2Blockly.g:874:2: ( ( 'switch' ) )
                    {
                    // InternalModel2Blockly.g:874:2: ( ( 'switch' ) )
                    // InternalModel2Blockly.g:875:3: ( 'switch' )
                    {
                     before(grammarAccess.getUiWidgetAccess().getSWITCHEnumLiteralDeclaration_4()); 
                    // InternalModel2Blockly.g:876:3: ( 'switch' )
                    // InternalModel2Blockly.g:876:4: 'switch'
                    {
                    match(input,15,FOLLOW_2); 

                    }

                     after(grammarAccess.getUiWidgetAccess().getSWITCHEnumLiteralDeclaration_4()); 

                    }


                    }
                    break;
                case 6 :
                    // InternalModel2Blockly.g:880:2: ( ( 'checkbox' ) )
                    {
                    // InternalModel2Blockly.g:880:2: ( ( 'checkbox' ) )
                    // InternalModel2Blockly.g:881:3: ( 'checkbox' )
                    {
                     before(grammarAccess.getUiWidgetAccess().getCHECKBOXEnumLiteralDeclaration_5()); 
                    // InternalModel2Blockly.g:882:3: ( 'checkbox' )
                    // InternalModel2Blockly.g:882:4: 'checkbox'
                    {
                    match(input,16,FOLLOW_2); 

                    }

                     after(grammarAccess.getUiWidgetAccess().getCHECKBOXEnumLiteralDeclaration_5()); 

                    }


                    }
                    break;
                case 7 :
                    // InternalModel2Blockly.g:886:2: ( ( 'select' ) )
                    {
                    // InternalModel2Blockly.g:886:2: ( ( 'select' ) )
                    // InternalModel2Blockly.g:887:3: ( 'select' )
                    {
                     before(grammarAccess.getUiWidgetAccess().getSELECTEnumLiteralDeclaration_6()); 
                    // InternalModel2Blockly.g:888:3: ( 'select' )
                    // InternalModel2Blockly.g:888:4: 'select'
                    {
                    match(input,17,FOLLOW_2); 

                    }

                     after(grammarAccess.getUiWidgetAccess().getSELECTEnumLiteralDeclaration_6()); 

                    }


                    }
                    break;
                case 8 :
                    // InternalModel2Blockly.g:892:2: ( ( 'radio' ) )
                    {
                    // InternalModel2Blockly.g:892:2: ( ( 'radio' ) )
                    // InternalModel2Blockly.g:893:3: ( 'radio' )
                    {
                     before(grammarAccess.getUiWidgetAccess().getRADIOEnumLiteralDeclaration_7()); 
                    // InternalModel2Blockly.g:894:3: ( 'radio' )
                    // InternalModel2Blockly.g:894:4: 'radio'
                    {
                    match(input,18,FOLLOW_2); 

                    }

                     after(grammarAccess.getUiWidgetAccess().getRADIOEnumLiteralDeclaration_7()); 

                    }


                    }
                    break;
                case 9 :
                    // InternalModel2Blockly.g:898:2: ( ( 'color' ) )
                    {
                    // InternalModel2Blockly.g:898:2: ( ( 'color' ) )
                    // InternalModel2Blockly.g:899:3: ( 'color' )
                    {
                     before(grammarAccess.getUiWidgetAccess().getCOLOREnumLiteralDeclaration_8()); 
                    // InternalModel2Blockly.g:900:3: ( 'color' )
                    // InternalModel2Blockly.g:900:4: 'color'
                    {
                    match(input,19,FOLLOW_2); 

                    }

                     after(grammarAccess.getUiWidgetAccess().getCOLOREnumLiteralDeclaration_8()); 

                    }


                    }
                    break;
                case 10 :
                    // InternalModel2Blockly.g:904:2: ( ( 'angle' ) )
                    {
                    // InternalModel2Blockly.g:904:2: ( ( 'angle' ) )
                    // InternalModel2Blockly.g:905:3: ( 'angle' )
                    {
                     before(grammarAccess.getUiWidgetAccess().getANGLEEnumLiteralDeclaration_9()); 
                    // InternalModel2Blockly.g:906:3: ( 'angle' )
                    // InternalModel2Blockly.g:906:4: 'angle'
                    {
                    match(input,20,FOLLOW_2); 

                    }

                     after(grammarAccess.getUiWidgetAccess().getANGLEEnumLiteralDeclaration_9()); 

                    }


                    }
                    break;
                case 11 :
                    // InternalModel2Blockly.g:910:2: ( ( 'image' ) )
                    {
                    // InternalModel2Blockly.g:910:2: ( ( 'image' ) )
                    // InternalModel2Blockly.g:911:3: ( 'image' )
                    {
                     before(grammarAccess.getUiWidgetAccess().getIMAGEEnumLiteralDeclaration_10()); 
                    // InternalModel2Blockly.g:912:3: ( 'image' )
                    // InternalModel2Blockly.g:912:4: 'image'
                    {
                    match(input,21,FOLLOW_2); 

                    }

                     after(grammarAccess.getUiWidgetAccess().getIMAGEEnumLiteralDeclaration_10()); 

                    }


                    }
                    break;
                case 12 :
                    // InternalModel2Blockly.g:916:2: ( ( 'reference-select' ) )
                    {
                    // InternalModel2Blockly.g:916:2: ( ( 'reference-select' ) )
                    // InternalModel2Blockly.g:917:3: ( 'reference-select' )
                    {
                     before(grammarAccess.getUiWidgetAccess().getREFERENCE_SELECTEnumLiteralDeclaration_11()); 
                    // InternalModel2Blockly.g:918:3: ( 'reference-select' )
                    // InternalModel2Blockly.g:918:4: 'reference-select'
                    {
                    match(input,22,FOLLOW_2); 

                    }

                     after(grammarAccess.getUiWidgetAccess().getREFERENCE_SELECTEnumLiteralDeclaration_11()); 

                    }


                    }
                    break;
                case 13 :
                    // InternalModel2Blockly.g:922:2: ( ( 'slot' ) )
                    {
                    // InternalModel2Blockly.g:922:2: ( ( 'slot' ) )
                    // InternalModel2Blockly.g:923:3: ( 'slot' )
                    {
                     before(grammarAccess.getUiWidgetAccess().getSLOTEnumLiteralDeclaration_12()); 
                    // InternalModel2Blockly.g:924:3: ( 'slot' )
                    // InternalModel2Blockly.g:924:4: 'slot'
                    {
                    match(input,23,FOLLOW_2); 

                    }

                     after(grammarAccess.getUiWidgetAccess().getSLOTEnumLiteralDeclaration_12()); 

                    }


                    }
                    break;
                case 14 :
                    // InternalModel2Blockly.g:928:2: ( ( 'expression-slot' ) )
                    {
                    // InternalModel2Blockly.g:928:2: ( ( 'expression-slot' ) )
                    // InternalModel2Blockly.g:929:3: ( 'expression-slot' )
                    {
                     before(grammarAccess.getUiWidgetAccess().getEXPRESSION_SLOTEnumLiteralDeclaration_13()); 
                    // InternalModel2Blockly.g:930:3: ( 'expression-slot' )
                    // InternalModel2Blockly.g:930:4: 'expression-slot'
                    {
                    match(input,24,FOLLOW_2); 

                    }

                     after(grammarAccess.getUiWidgetAccess().getEXPRESSION_SLOTEnumLiteralDeclaration_13()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UiWidget__Alternatives"


    // $ANTLR start "rule__UiVariant__Alternatives"
    // InternalModel2Blockly.g:938:1: rule__UiVariant__Alternatives : ( ( ( 'default' ) ) | ( ( 'compact' ) ) | ( ( 'prominent' ) ) );
    public final void rule__UiVariant__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:942:1: ( ( ( 'default' ) ) | ( ( 'compact' ) ) | ( ( 'prominent' ) ) )
            int alt7=3;
            switch ( input.LA(1) ) {
            case 25:
                {
                alt7=1;
                }
                break;
            case 26:
                {
                alt7=2;
                }
                break;
            case 27:
                {
                alt7=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }

            switch (alt7) {
                case 1 :
                    // InternalModel2Blockly.g:943:2: ( ( 'default' ) )
                    {
                    // InternalModel2Blockly.g:943:2: ( ( 'default' ) )
                    // InternalModel2Blockly.g:944:3: ( 'default' )
                    {
                     before(grammarAccess.getUiVariantAccess().getDEFAULTEnumLiteralDeclaration_0()); 
                    // InternalModel2Blockly.g:945:3: ( 'default' )
                    // InternalModel2Blockly.g:945:4: 'default'
                    {
                    match(input,25,FOLLOW_2); 

                    }

                     after(grammarAccess.getUiVariantAccess().getDEFAULTEnumLiteralDeclaration_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalModel2Blockly.g:949:2: ( ( 'compact' ) )
                    {
                    // InternalModel2Blockly.g:949:2: ( ( 'compact' ) )
                    // InternalModel2Blockly.g:950:3: ( 'compact' )
                    {
                     before(grammarAccess.getUiVariantAccess().getCOMPACTEnumLiteralDeclaration_1()); 
                    // InternalModel2Blockly.g:951:3: ( 'compact' )
                    // InternalModel2Blockly.g:951:4: 'compact'
                    {
                    match(input,26,FOLLOW_2); 

                    }

                     after(grammarAccess.getUiVariantAccess().getCOMPACTEnumLiteralDeclaration_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalModel2Blockly.g:955:2: ( ( 'prominent' ) )
                    {
                    // InternalModel2Blockly.g:955:2: ( ( 'prominent' ) )
                    // InternalModel2Blockly.g:956:3: ( 'prominent' )
                    {
                     before(grammarAccess.getUiVariantAccess().getPROMINENTEnumLiteralDeclaration_2()); 
                    // InternalModel2Blockly.g:957:3: ( 'prominent' )
                    // InternalModel2Blockly.g:957:4: 'prominent'
                    {
                    match(input,27,FOLLOW_2); 

                    }

                     after(grammarAccess.getUiVariantAccess().getPROMINENTEnumLiteralDeclaration_2()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UiVariant__Alternatives"


    // $ANTLR start "rule__SimpleTypeName__Alternatives"
    // InternalModel2Blockly.g:965:1: rule__SimpleTypeName__Alternatives : ( ( ( 'string' ) ) | ( ( 'int' ) ) | ( ( 'boolean' ) ) | ( ( 'float' ) ) | ( ( 'colour' ) ) | ( ( 'angle' ) ) | ( ( 'image' ) ) | ( ( 'label' ) ) );
    public final void rule__SimpleTypeName__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:969:1: ( ( ( 'string' ) ) | ( ( 'int' ) ) | ( ( 'boolean' ) ) | ( ( 'float' ) ) | ( ( 'colour' ) ) | ( ( 'angle' ) ) | ( ( 'image' ) ) | ( ( 'label' ) ) )
            int alt8=8;
            switch ( input.LA(1) ) {
            case 28:
                {
                alt8=1;
                }
                break;
            case 29:
                {
                alt8=2;
                }
                break;
            case 30:
                {
                alt8=3;
                }
                break;
            case 31:
                {
                alt8=4;
                }
                break;
            case 32:
                {
                alt8=5;
                }
                break;
            case 20:
                {
                alt8=6;
                }
                break;
            case 21:
                {
                alt8=7;
                }
                break;
            case 33:
                {
                alt8=8;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }

            switch (alt8) {
                case 1 :
                    // InternalModel2Blockly.g:970:2: ( ( 'string' ) )
                    {
                    // InternalModel2Blockly.g:970:2: ( ( 'string' ) )
                    // InternalModel2Blockly.g:971:3: ( 'string' )
                    {
                     before(grammarAccess.getSimpleTypeNameAccess().getStringEnumLiteralDeclaration_0()); 
                    // InternalModel2Blockly.g:972:3: ( 'string' )
                    // InternalModel2Blockly.g:972:4: 'string'
                    {
                    match(input,28,FOLLOW_2); 

                    }

                     after(grammarAccess.getSimpleTypeNameAccess().getStringEnumLiteralDeclaration_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalModel2Blockly.g:976:2: ( ( 'int' ) )
                    {
                    // InternalModel2Blockly.g:976:2: ( ( 'int' ) )
                    // InternalModel2Blockly.g:977:3: ( 'int' )
                    {
                     before(grammarAccess.getSimpleTypeNameAccess().getIntEnumLiteralDeclaration_1()); 
                    // InternalModel2Blockly.g:978:3: ( 'int' )
                    // InternalModel2Blockly.g:978:4: 'int'
                    {
                    match(input,29,FOLLOW_2); 

                    }

                     after(grammarAccess.getSimpleTypeNameAccess().getIntEnumLiteralDeclaration_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalModel2Blockly.g:982:2: ( ( 'boolean' ) )
                    {
                    // InternalModel2Blockly.g:982:2: ( ( 'boolean' ) )
                    // InternalModel2Blockly.g:983:3: ( 'boolean' )
                    {
                     before(grammarAccess.getSimpleTypeNameAccess().getBooleanEnumLiteralDeclaration_2()); 
                    // InternalModel2Blockly.g:984:3: ( 'boolean' )
                    // InternalModel2Blockly.g:984:4: 'boolean'
                    {
                    match(input,30,FOLLOW_2); 

                    }

                     after(grammarAccess.getSimpleTypeNameAccess().getBooleanEnumLiteralDeclaration_2()); 

                    }


                    }
                    break;
                case 4 :
                    // InternalModel2Blockly.g:988:2: ( ( 'float' ) )
                    {
                    // InternalModel2Blockly.g:988:2: ( ( 'float' ) )
                    // InternalModel2Blockly.g:989:3: ( 'float' )
                    {
                     before(grammarAccess.getSimpleTypeNameAccess().getFloatEnumLiteralDeclaration_3()); 
                    // InternalModel2Blockly.g:990:3: ( 'float' )
                    // InternalModel2Blockly.g:990:4: 'float'
                    {
                    match(input,31,FOLLOW_2); 

                    }

                     after(grammarAccess.getSimpleTypeNameAccess().getFloatEnumLiteralDeclaration_3()); 

                    }


                    }
                    break;
                case 5 :
                    // InternalModel2Blockly.g:994:2: ( ( 'colour' ) )
                    {
                    // InternalModel2Blockly.g:994:2: ( ( 'colour' ) )
                    // InternalModel2Blockly.g:995:3: ( 'colour' )
                    {
                     before(grammarAccess.getSimpleTypeNameAccess().getColourEnumLiteralDeclaration_4()); 
                    // InternalModel2Blockly.g:996:3: ( 'colour' )
                    // InternalModel2Blockly.g:996:4: 'colour'
                    {
                    match(input,32,FOLLOW_2); 

                    }

                     after(grammarAccess.getSimpleTypeNameAccess().getColourEnumLiteralDeclaration_4()); 

                    }


                    }
                    break;
                case 6 :
                    // InternalModel2Blockly.g:1000:2: ( ( 'angle' ) )
                    {
                    // InternalModel2Blockly.g:1000:2: ( ( 'angle' ) )
                    // InternalModel2Blockly.g:1001:3: ( 'angle' )
                    {
                     before(grammarAccess.getSimpleTypeNameAccess().getAngleEnumLiteralDeclaration_5()); 
                    // InternalModel2Blockly.g:1002:3: ( 'angle' )
                    // InternalModel2Blockly.g:1002:4: 'angle'
                    {
                    match(input,20,FOLLOW_2); 

                    }

                     after(grammarAccess.getSimpleTypeNameAccess().getAngleEnumLiteralDeclaration_5()); 

                    }


                    }
                    break;
                case 7 :
                    // InternalModel2Blockly.g:1006:2: ( ( 'image' ) )
                    {
                    // InternalModel2Blockly.g:1006:2: ( ( 'image' ) )
                    // InternalModel2Blockly.g:1007:3: ( 'image' )
                    {
                     before(grammarAccess.getSimpleTypeNameAccess().getImageEnumLiteralDeclaration_6()); 
                    // InternalModel2Blockly.g:1008:3: ( 'image' )
                    // InternalModel2Blockly.g:1008:4: 'image'
                    {
                    match(input,21,FOLLOW_2); 

                    }

                     after(grammarAccess.getSimpleTypeNameAccess().getImageEnumLiteralDeclaration_6()); 

                    }


                    }
                    break;
                case 8 :
                    // InternalModel2Blockly.g:1012:2: ( ( 'label' ) )
                    {
                    // InternalModel2Blockly.g:1012:2: ( ( 'label' ) )
                    // InternalModel2Blockly.g:1013:3: ( 'label' )
                    {
                     before(grammarAccess.getSimpleTypeNameAccess().getLabelEnumLiteralDeclaration_7()); 
                    // InternalModel2Blockly.g:1014:3: ( 'label' )
                    // InternalModel2Blockly.g:1014:4: 'label'
                    {
                    match(input,33,FOLLOW_2); 

                    }

                     after(grammarAccess.getSimpleTypeNameAccess().getLabelEnumLiteralDeclaration_7()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SimpleTypeName__Alternatives"


    // $ANTLR start "rule__ValidationKind__Alternatives"
    // InternalModel2Blockly.g:1022:1: rule__ValidationKind__Alternatives : ( ( ( 'expression' ) ) | ( ( 'condition' ) ) | ( ( 'js' ) ) | ( ( 'ocl' ) ) );
    public final void rule__ValidationKind__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1026:1: ( ( ( 'expression' ) ) | ( ( 'condition' ) ) | ( ( 'js' ) ) | ( ( 'ocl' ) ) )
            int alt9=4;
            switch ( input.LA(1) ) {
            case 34:
                {
                alt9=1;
                }
                break;
            case 35:
                {
                alt9=2;
                }
                break;
            case 36:
                {
                alt9=3;
                }
                break;
            case 37:
                {
                alt9=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }

            switch (alt9) {
                case 1 :
                    // InternalModel2Blockly.g:1027:2: ( ( 'expression' ) )
                    {
                    // InternalModel2Blockly.g:1027:2: ( ( 'expression' ) )
                    // InternalModel2Blockly.g:1028:3: ( 'expression' )
                    {
                     before(grammarAccess.getValidationKindAccess().getEXPRESSIONEnumLiteralDeclaration_0()); 
                    // InternalModel2Blockly.g:1029:3: ( 'expression' )
                    // InternalModel2Blockly.g:1029:4: 'expression'
                    {
                    match(input,34,FOLLOW_2); 

                    }

                     after(grammarAccess.getValidationKindAccess().getEXPRESSIONEnumLiteralDeclaration_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalModel2Blockly.g:1033:2: ( ( 'condition' ) )
                    {
                    // InternalModel2Blockly.g:1033:2: ( ( 'condition' ) )
                    // InternalModel2Blockly.g:1034:3: ( 'condition' )
                    {
                     before(grammarAccess.getValidationKindAccess().getCONDITIONEnumLiteralDeclaration_1()); 
                    // InternalModel2Blockly.g:1035:3: ( 'condition' )
                    // InternalModel2Blockly.g:1035:4: 'condition'
                    {
                    match(input,35,FOLLOW_2); 

                    }

                     after(grammarAccess.getValidationKindAccess().getCONDITIONEnumLiteralDeclaration_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalModel2Blockly.g:1039:2: ( ( 'js' ) )
                    {
                    // InternalModel2Blockly.g:1039:2: ( ( 'js' ) )
                    // InternalModel2Blockly.g:1040:3: ( 'js' )
                    {
                     before(grammarAccess.getValidationKindAccess().getJSEnumLiteralDeclaration_2()); 
                    // InternalModel2Blockly.g:1041:3: ( 'js' )
                    // InternalModel2Blockly.g:1041:4: 'js'
                    {
                    match(input,36,FOLLOW_2); 

                    }

                     after(grammarAccess.getValidationKindAccess().getJSEnumLiteralDeclaration_2()); 

                    }


                    }
                    break;
                case 4 :
                    // InternalModel2Blockly.g:1045:2: ( ( 'ocl' ) )
                    {
                    // InternalModel2Blockly.g:1045:2: ( ( 'ocl' ) )
                    // InternalModel2Blockly.g:1046:3: ( 'ocl' )
                    {
                     before(grammarAccess.getValidationKindAccess().getOCLEnumLiteralDeclaration_3()); 
                    // InternalModel2Blockly.g:1047:3: ( 'ocl' )
                    // InternalModel2Blockly.g:1047:4: 'ocl'
                    {
                    match(input,37,FOLLOW_2); 

                    }

                     after(grammarAccess.getValidationKindAccess().getOCLEnumLiteralDeclaration_3()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ValidationKind__Alternatives"


    // $ANTLR start "rule__BoolVal__Alternatives"
    // InternalModel2Blockly.g:1055:1: rule__BoolVal__Alternatives : ( ( ( 'true' ) ) | ( ( 'false' ) ) );
    public final void rule__BoolVal__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1059:1: ( ( ( 'true' ) ) | ( ( 'false' ) ) )
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==38) ) {
                alt10=1;
            }
            else if ( (LA10_0==39) ) {
                alt10=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }
            switch (alt10) {
                case 1 :
                    // InternalModel2Blockly.g:1060:2: ( ( 'true' ) )
                    {
                    // InternalModel2Blockly.g:1060:2: ( ( 'true' ) )
                    // InternalModel2Blockly.g:1061:3: ( 'true' )
                    {
                     before(grammarAccess.getBoolValAccess().getTRUEEnumLiteralDeclaration_0()); 
                    // InternalModel2Blockly.g:1062:3: ( 'true' )
                    // InternalModel2Blockly.g:1062:4: 'true'
                    {
                    match(input,38,FOLLOW_2); 

                    }

                     after(grammarAccess.getBoolValAccess().getTRUEEnumLiteralDeclaration_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalModel2Blockly.g:1066:2: ( ( 'false' ) )
                    {
                    // InternalModel2Blockly.g:1066:2: ( ( 'false' ) )
                    // InternalModel2Blockly.g:1067:3: ( 'false' )
                    {
                     before(grammarAccess.getBoolValAccess().getFALSEEnumLiteralDeclaration_1()); 
                    // InternalModel2Blockly.g:1068:3: ( 'false' )
                    // InternalModel2Blockly.g:1068:4: 'false'
                    {
                    match(input,39,FOLLOW_2); 

                    }

                     after(grammarAccess.getBoolValAccess().getFALSEEnumLiteralDeclaration_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BoolVal__Alternatives"


    // $ANTLR start "rule__DomainModel__Group__0"
    // InternalModel2Blockly.g:1076:1: rule__DomainModel__Group__0 : rule__DomainModel__Group__0__Impl rule__DomainModel__Group__1 ;
    public final void rule__DomainModel__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1080:1: ( rule__DomainModel__Group__0__Impl rule__DomainModel__Group__1 )
            // InternalModel2Blockly.g:1081:2: rule__DomainModel__Group__0__Impl rule__DomainModel__Group__1
            {
            pushFollow(FOLLOW_4);
            rule__DomainModel__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__DomainModel__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DomainModel__Group__0"


    // $ANTLR start "rule__DomainModel__Group__0__Impl"
    // InternalModel2Blockly.g:1088:1: rule__DomainModel__Group__0__Impl : ( 'domain' ) ;
    public final void rule__DomainModel__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1092:1: ( ( 'domain' ) )
            // InternalModel2Blockly.g:1093:1: ( 'domain' )
            {
            // InternalModel2Blockly.g:1093:1: ( 'domain' )
            // InternalModel2Blockly.g:1094:2: 'domain'
            {
             before(grammarAccess.getDomainModelAccess().getDomainKeyword_0()); 
            match(input,40,FOLLOW_2); 
             after(grammarAccess.getDomainModelAccess().getDomainKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DomainModel__Group__0__Impl"


    // $ANTLR start "rule__DomainModel__Group__1"
    // InternalModel2Blockly.g:1103:1: rule__DomainModel__Group__1 : rule__DomainModel__Group__1__Impl rule__DomainModel__Group__2 ;
    public final void rule__DomainModel__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1107:1: ( rule__DomainModel__Group__1__Impl rule__DomainModel__Group__2 )
            // InternalModel2Blockly.g:1108:2: rule__DomainModel__Group__1__Impl rule__DomainModel__Group__2
            {
            pushFollow(FOLLOW_5);
            rule__DomainModel__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__DomainModel__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DomainModel__Group__1"


    // $ANTLR start "rule__DomainModel__Group__1__Impl"
    // InternalModel2Blockly.g:1115:1: rule__DomainModel__Group__1__Impl : ( ( rule__DomainModel__NameAssignment_1 ) ) ;
    public final void rule__DomainModel__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1119:1: ( ( ( rule__DomainModel__NameAssignment_1 ) ) )
            // InternalModel2Blockly.g:1120:1: ( ( rule__DomainModel__NameAssignment_1 ) )
            {
            // InternalModel2Blockly.g:1120:1: ( ( rule__DomainModel__NameAssignment_1 ) )
            // InternalModel2Blockly.g:1121:2: ( rule__DomainModel__NameAssignment_1 )
            {
             before(grammarAccess.getDomainModelAccess().getNameAssignment_1()); 
            // InternalModel2Blockly.g:1122:2: ( rule__DomainModel__NameAssignment_1 )
            // InternalModel2Blockly.g:1122:3: rule__DomainModel__NameAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__DomainModel__NameAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getDomainModelAccess().getNameAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DomainModel__Group__1__Impl"


    // $ANTLR start "rule__DomainModel__Group__2"
    // InternalModel2Blockly.g:1130:1: rule__DomainModel__Group__2 : rule__DomainModel__Group__2__Impl rule__DomainModel__Group__3 ;
    public final void rule__DomainModel__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1134:1: ( rule__DomainModel__Group__2__Impl rule__DomainModel__Group__3 )
            // InternalModel2Blockly.g:1135:2: rule__DomainModel__Group__2__Impl rule__DomainModel__Group__3
            {
            pushFollow(FOLLOW_5);
            rule__DomainModel__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__DomainModel__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DomainModel__Group__2"


    // $ANTLR start "rule__DomainModel__Group__2__Impl"
    // InternalModel2Blockly.g:1142:1: rule__DomainModel__Group__2__Impl : ( ( rule__DomainModel__Group_2__0 )? ) ;
    public final void rule__DomainModel__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1146:1: ( ( ( rule__DomainModel__Group_2__0 )? ) )
            // InternalModel2Blockly.g:1147:1: ( ( rule__DomainModel__Group_2__0 )? )
            {
            // InternalModel2Blockly.g:1147:1: ( ( rule__DomainModel__Group_2__0 )? )
            // InternalModel2Blockly.g:1148:2: ( rule__DomainModel__Group_2__0 )?
            {
             before(grammarAccess.getDomainModelAccess().getGroup_2()); 
            // InternalModel2Blockly.g:1149:2: ( rule__DomainModel__Group_2__0 )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==41) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // InternalModel2Blockly.g:1149:3: rule__DomainModel__Group_2__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__DomainModel__Group_2__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getDomainModelAccess().getGroup_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DomainModel__Group__2__Impl"


    // $ANTLR start "rule__DomainModel__Group__3"
    // InternalModel2Blockly.g:1157:1: rule__DomainModel__Group__3 : rule__DomainModel__Group__3__Impl rule__DomainModel__Group__4 ;
    public final void rule__DomainModel__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1161:1: ( rule__DomainModel__Group__3__Impl rule__DomainModel__Group__4 )
            // InternalModel2Blockly.g:1162:2: rule__DomainModel__Group__3__Impl rule__DomainModel__Group__4
            {
            pushFollow(FOLLOW_5);
            rule__DomainModel__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__DomainModel__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DomainModel__Group__3"


    // $ANTLR start "rule__DomainModel__Group__3__Impl"
    // InternalModel2Blockly.g:1169:1: rule__DomainModel__Group__3__Impl : ( ( rule__DomainModel__Group_3__0 )? ) ;
    public final void rule__DomainModel__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1173:1: ( ( ( rule__DomainModel__Group_3__0 )? ) )
            // InternalModel2Blockly.g:1174:1: ( ( rule__DomainModel__Group_3__0 )? )
            {
            // InternalModel2Blockly.g:1174:1: ( ( rule__DomainModel__Group_3__0 )? )
            // InternalModel2Blockly.g:1175:2: ( rule__DomainModel__Group_3__0 )?
            {
             before(grammarAccess.getDomainModelAccess().getGroup_3()); 
            // InternalModel2Blockly.g:1176:2: ( rule__DomainModel__Group_3__0 )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==42) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // InternalModel2Blockly.g:1176:3: rule__DomainModel__Group_3__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__DomainModel__Group_3__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getDomainModelAccess().getGroup_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DomainModel__Group__3__Impl"


    // $ANTLR start "rule__DomainModel__Group__4"
    // InternalModel2Blockly.g:1184:1: rule__DomainModel__Group__4 : rule__DomainModel__Group__4__Impl rule__DomainModel__Group__5 ;
    public final void rule__DomainModel__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1188:1: ( rule__DomainModel__Group__4__Impl rule__DomainModel__Group__5 )
            // InternalModel2Blockly.g:1189:2: rule__DomainModel__Group__4__Impl rule__DomainModel__Group__5
            {
            pushFollow(FOLLOW_5);
            rule__DomainModel__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__DomainModel__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DomainModel__Group__4"


    // $ANTLR start "rule__DomainModel__Group__4__Impl"
    // InternalModel2Blockly.g:1196:1: rule__DomainModel__Group__4__Impl : ( ( rule__DomainModel__Group_4__0 )? ) ;
    public final void rule__DomainModel__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1200:1: ( ( ( rule__DomainModel__Group_4__0 )? ) )
            // InternalModel2Blockly.g:1201:1: ( ( rule__DomainModel__Group_4__0 )? )
            {
            // InternalModel2Blockly.g:1201:1: ( ( rule__DomainModel__Group_4__0 )? )
            // InternalModel2Blockly.g:1202:2: ( rule__DomainModel__Group_4__0 )?
            {
             before(grammarAccess.getDomainModelAccess().getGroup_4()); 
            // InternalModel2Blockly.g:1203:2: ( rule__DomainModel__Group_4__0 )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==43) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // InternalModel2Blockly.g:1203:3: rule__DomainModel__Group_4__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__DomainModel__Group_4__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getDomainModelAccess().getGroup_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DomainModel__Group__4__Impl"


    // $ANTLR start "rule__DomainModel__Group__5"
    // InternalModel2Blockly.g:1211:1: rule__DomainModel__Group__5 : rule__DomainModel__Group__5__Impl rule__DomainModel__Group__6 ;
    public final void rule__DomainModel__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1215:1: ( rule__DomainModel__Group__5__Impl rule__DomainModel__Group__6 )
            // InternalModel2Blockly.g:1216:2: rule__DomainModel__Group__5__Impl rule__DomainModel__Group__6
            {
            pushFollow(FOLLOW_5);
            rule__DomainModel__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__DomainModel__Group__6();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DomainModel__Group__5"


    // $ANTLR start "rule__DomainModel__Group__5__Impl"
    // InternalModel2Blockly.g:1223:1: rule__DomainModel__Group__5__Impl : ( ( rule__DomainModel__WorkspaceAssignment_5 )? ) ;
    public final void rule__DomainModel__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1227:1: ( ( ( rule__DomainModel__WorkspaceAssignment_5 )? ) )
            // InternalModel2Blockly.g:1228:1: ( ( rule__DomainModel__WorkspaceAssignment_5 )? )
            {
            // InternalModel2Blockly.g:1228:1: ( ( rule__DomainModel__WorkspaceAssignment_5 )? )
            // InternalModel2Blockly.g:1229:2: ( rule__DomainModel__WorkspaceAssignment_5 )?
            {
             before(grammarAccess.getDomainModelAccess().getWorkspaceAssignment_5()); 
            // InternalModel2Blockly.g:1230:2: ( rule__DomainModel__WorkspaceAssignment_5 )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==88) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // InternalModel2Blockly.g:1230:3: rule__DomainModel__WorkspaceAssignment_5
                    {
                    pushFollow(FOLLOW_2);
                    rule__DomainModel__WorkspaceAssignment_5();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getDomainModelAccess().getWorkspaceAssignment_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DomainModel__Group__5__Impl"


    // $ANTLR start "rule__DomainModel__Group__6"
    // InternalModel2Blockly.g:1238:1: rule__DomainModel__Group__6 : rule__DomainModel__Group__6__Impl rule__DomainModel__Group__7 ;
    public final void rule__DomainModel__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1242:1: ( rule__DomainModel__Group__6__Impl rule__DomainModel__Group__7 )
            // InternalModel2Blockly.g:1243:2: rule__DomainModel__Group__6__Impl rule__DomainModel__Group__7
            {
            pushFollow(FOLLOW_5);
            rule__DomainModel__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__DomainModel__Group__7();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DomainModel__Group__6"


    // $ANTLR start "rule__DomainModel__Group__6__Impl"
    // InternalModel2Blockly.g:1250:1: rule__DomainModel__Group__6__Impl : ( ( rule__DomainModel__CategoriesAssignment_6 )* ) ;
    public final void rule__DomainModel__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1254:1: ( ( ( rule__DomainModel__CategoriesAssignment_6 )* ) )
            // InternalModel2Blockly.g:1255:1: ( ( rule__DomainModel__CategoriesAssignment_6 )* )
            {
            // InternalModel2Blockly.g:1255:1: ( ( rule__DomainModel__CategoriesAssignment_6 )* )
            // InternalModel2Blockly.g:1256:2: ( rule__DomainModel__CategoriesAssignment_6 )*
            {
             before(grammarAccess.getDomainModelAccess().getCategoriesAssignment_6()); 
            // InternalModel2Blockly.g:1257:2: ( rule__DomainModel__CategoriesAssignment_6 )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==44) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // InternalModel2Blockly.g:1257:3: rule__DomainModel__CategoriesAssignment_6
            	    {
            	    pushFollow(FOLLOW_6);
            	    rule__DomainModel__CategoriesAssignment_6();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);

             after(grammarAccess.getDomainModelAccess().getCategoriesAssignment_6()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DomainModel__Group__6__Impl"


    // $ANTLR start "rule__DomainModel__Group__7"
    // InternalModel2Blockly.g:1265:1: rule__DomainModel__Group__7 : rule__DomainModel__Group__7__Impl rule__DomainModel__Group__8 ;
    public final void rule__DomainModel__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1269:1: ( rule__DomainModel__Group__7__Impl rule__DomainModel__Group__8 )
            // InternalModel2Blockly.g:1270:2: rule__DomainModel__Group__7__Impl rule__DomainModel__Group__8
            {
            pushFollow(FOLLOW_5);
            rule__DomainModel__Group__7__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__DomainModel__Group__8();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DomainModel__Group__7"


    // $ANTLR start "rule__DomainModel__Group__7__Impl"
    // InternalModel2Blockly.g:1277:1: rule__DomainModel__Group__7__Impl : ( ( rule__DomainModel__ClassesAssignment_7 )* ) ;
    public final void rule__DomainModel__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1281:1: ( ( ( rule__DomainModel__ClassesAssignment_7 )* ) )
            // InternalModel2Blockly.g:1282:1: ( ( rule__DomainModel__ClassesAssignment_7 )* )
            {
            // InternalModel2Blockly.g:1282:1: ( ( rule__DomainModel__ClassesAssignment_7 )* )
            // InternalModel2Blockly.g:1283:2: ( rule__DomainModel__ClassesAssignment_7 )*
            {
             before(grammarAccess.getDomainModelAccess().getClassesAssignment_7()); 
            // InternalModel2Blockly.g:1284:2: ( rule__DomainModel__ClassesAssignment_7 )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==47||(LA16_0>=89 && LA16_0<=90)) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // InternalModel2Blockly.g:1284:3: rule__DomainModel__ClassesAssignment_7
            	    {
            	    pushFollow(FOLLOW_7);
            	    rule__DomainModel__ClassesAssignment_7();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop16;
                }
            } while (true);

             after(grammarAccess.getDomainModelAccess().getClassesAssignment_7()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DomainModel__Group__7__Impl"


    // $ANTLR start "rule__DomainModel__Group__8"
    // InternalModel2Blockly.g:1292:1: rule__DomainModel__Group__8 : rule__DomainModel__Group__8__Impl rule__DomainModel__Group__9 ;
    public final void rule__DomainModel__Group__8() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1296:1: ( rule__DomainModel__Group__8__Impl rule__DomainModel__Group__9 )
            // InternalModel2Blockly.g:1297:2: rule__DomainModel__Group__8__Impl rule__DomainModel__Group__9
            {
            pushFollow(FOLLOW_5);
            rule__DomainModel__Group__8__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__DomainModel__Group__9();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DomainModel__Group__8"


    // $ANTLR start "rule__DomainModel__Group__8__Impl"
    // InternalModel2Blockly.g:1304:1: rule__DomainModel__Group__8__Impl : ( ( rule__DomainModel__ConstraintsAssignment_8 )* ) ;
    public final void rule__DomainModel__Group__8__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1308:1: ( ( ( rule__DomainModel__ConstraintsAssignment_8 )* ) )
            // InternalModel2Blockly.g:1309:1: ( ( rule__DomainModel__ConstraintsAssignment_8 )* )
            {
            // InternalModel2Blockly.g:1309:1: ( ( rule__DomainModel__ConstraintsAssignment_8 )* )
            // InternalModel2Blockly.g:1310:2: ( rule__DomainModel__ConstraintsAssignment_8 )*
            {
             before(grammarAccess.getDomainModelAccess().getConstraintsAssignment_8()); 
            // InternalModel2Blockly.g:1311:2: ( rule__DomainModel__ConstraintsAssignment_8 )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==82) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // InternalModel2Blockly.g:1311:3: rule__DomainModel__ConstraintsAssignment_8
            	    {
            	    pushFollow(FOLLOW_8);
            	    rule__DomainModel__ConstraintsAssignment_8();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop17;
                }
            } while (true);

             after(grammarAccess.getDomainModelAccess().getConstraintsAssignment_8()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DomainModel__Group__8__Impl"


    // $ANTLR start "rule__DomainModel__Group__9"
    // InternalModel2Blockly.g:1319:1: rule__DomainModel__Group__9 : rule__DomainModel__Group__9__Impl ;
    public final void rule__DomainModel__Group__9() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1323:1: ( rule__DomainModel__Group__9__Impl )
            // InternalModel2Blockly.g:1324:2: rule__DomainModel__Group__9__Impl
            {
            pushFollow(FOLLOW_2);
            rule__DomainModel__Group__9__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DomainModel__Group__9"


    // $ANTLR start "rule__DomainModel__Group__9__Impl"
    // InternalModel2Blockly.g:1330:1: rule__DomainModel__Group__9__Impl : ( ( rule__DomainModel__ValidationsAssignment_9 )* ) ;
    public final void rule__DomainModel__Group__9__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1334:1: ( ( ( rule__DomainModel__ValidationsAssignment_9 )* ) )
            // InternalModel2Blockly.g:1335:1: ( ( rule__DomainModel__ValidationsAssignment_9 )* )
            {
            // InternalModel2Blockly.g:1335:1: ( ( rule__DomainModel__ValidationsAssignment_9 )* )
            // InternalModel2Blockly.g:1336:2: ( rule__DomainModel__ValidationsAssignment_9 )*
            {
             before(grammarAccess.getDomainModelAccess().getValidationsAssignment_9()); 
            // InternalModel2Blockly.g:1337:2: ( rule__DomainModel__ValidationsAssignment_9 )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==86) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // InternalModel2Blockly.g:1337:3: rule__DomainModel__ValidationsAssignment_9
            	    {
            	    pushFollow(FOLLOW_9);
            	    rule__DomainModel__ValidationsAssignment_9();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop18;
                }
            } while (true);

             after(grammarAccess.getDomainModelAccess().getValidationsAssignment_9()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DomainModel__Group__9__Impl"


    // $ANTLR start "rule__DomainModel__Group_2__0"
    // InternalModel2Blockly.g:1346:1: rule__DomainModel__Group_2__0 : rule__DomainModel__Group_2__0__Impl rule__DomainModel__Group_2__1 ;
    public final void rule__DomainModel__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1350:1: ( rule__DomainModel__Group_2__0__Impl rule__DomainModel__Group_2__1 )
            // InternalModel2Blockly.g:1351:2: rule__DomainModel__Group_2__0__Impl rule__DomainModel__Group_2__1
            {
            pushFollow(FOLLOW_10);
            rule__DomainModel__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__DomainModel__Group_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DomainModel__Group_2__0"


    // $ANTLR start "rule__DomainModel__Group_2__0__Impl"
    // InternalModel2Blockly.g:1358:1: rule__DomainModel__Group_2__0__Impl : ( 'codeLanguage' ) ;
    public final void rule__DomainModel__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1362:1: ( ( 'codeLanguage' ) )
            // InternalModel2Blockly.g:1363:1: ( 'codeLanguage' )
            {
            // InternalModel2Blockly.g:1363:1: ( 'codeLanguage' )
            // InternalModel2Blockly.g:1364:2: 'codeLanguage'
            {
             before(grammarAccess.getDomainModelAccess().getCodeLanguageKeyword_2_0()); 
            match(input,41,FOLLOW_2); 
             after(grammarAccess.getDomainModelAccess().getCodeLanguageKeyword_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DomainModel__Group_2__0__Impl"


    // $ANTLR start "rule__DomainModel__Group_2__1"
    // InternalModel2Blockly.g:1373:1: rule__DomainModel__Group_2__1 : rule__DomainModel__Group_2__1__Impl ;
    public final void rule__DomainModel__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1377:1: ( rule__DomainModel__Group_2__1__Impl )
            // InternalModel2Blockly.g:1378:2: rule__DomainModel__Group_2__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__DomainModel__Group_2__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DomainModel__Group_2__1"


    // $ANTLR start "rule__DomainModel__Group_2__1__Impl"
    // InternalModel2Blockly.g:1384:1: rule__DomainModel__Group_2__1__Impl : ( ( rule__DomainModel__CodeLanguageAssignment_2_1 ) ) ;
    public final void rule__DomainModel__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1388:1: ( ( ( rule__DomainModel__CodeLanguageAssignment_2_1 ) ) )
            // InternalModel2Blockly.g:1389:1: ( ( rule__DomainModel__CodeLanguageAssignment_2_1 ) )
            {
            // InternalModel2Blockly.g:1389:1: ( ( rule__DomainModel__CodeLanguageAssignment_2_1 ) )
            // InternalModel2Blockly.g:1390:2: ( rule__DomainModel__CodeLanguageAssignment_2_1 )
            {
             before(grammarAccess.getDomainModelAccess().getCodeLanguageAssignment_2_1()); 
            // InternalModel2Blockly.g:1391:2: ( rule__DomainModel__CodeLanguageAssignment_2_1 )
            // InternalModel2Blockly.g:1391:3: rule__DomainModel__CodeLanguageAssignment_2_1
            {
            pushFollow(FOLLOW_2);
            rule__DomainModel__CodeLanguageAssignment_2_1();

            state._fsp--;


            }

             after(grammarAccess.getDomainModelAccess().getCodeLanguageAssignment_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DomainModel__Group_2__1__Impl"


    // $ANTLR start "rule__DomainModel__Group_3__0"
    // InternalModel2Blockly.g:1400:1: rule__DomainModel__Group_3__0 : rule__DomainModel__Group_3__0__Impl rule__DomainModel__Group_3__1 ;
    public final void rule__DomainModel__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1404:1: ( rule__DomainModel__Group_3__0__Impl rule__DomainModel__Group_3__1 )
            // InternalModel2Blockly.g:1405:2: rule__DomainModel__Group_3__0__Impl rule__DomainModel__Group_3__1
            {
            pushFollow(FOLLOW_10);
            rule__DomainModel__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__DomainModel__Group_3__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DomainModel__Group_3__0"


    // $ANTLR start "rule__DomainModel__Group_3__0__Impl"
    // InternalModel2Blockly.g:1412:1: rule__DomainModel__Group_3__0__Impl : ( 'codeFileExtension' ) ;
    public final void rule__DomainModel__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1416:1: ( ( 'codeFileExtension' ) )
            // InternalModel2Blockly.g:1417:1: ( 'codeFileExtension' )
            {
            // InternalModel2Blockly.g:1417:1: ( 'codeFileExtension' )
            // InternalModel2Blockly.g:1418:2: 'codeFileExtension'
            {
             before(grammarAccess.getDomainModelAccess().getCodeFileExtensionKeyword_3_0()); 
            match(input,42,FOLLOW_2); 
             after(grammarAccess.getDomainModelAccess().getCodeFileExtensionKeyword_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DomainModel__Group_3__0__Impl"


    // $ANTLR start "rule__DomainModel__Group_3__1"
    // InternalModel2Blockly.g:1427:1: rule__DomainModel__Group_3__1 : rule__DomainModel__Group_3__1__Impl ;
    public final void rule__DomainModel__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1431:1: ( rule__DomainModel__Group_3__1__Impl )
            // InternalModel2Blockly.g:1432:2: rule__DomainModel__Group_3__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__DomainModel__Group_3__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DomainModel__Group_3__1"


    // $ANTLR start "rule__DomainModel__Group_3__1__Impl"
    // InternalModel2Blockly.g:1438:1: rule__DomainModel__Group_3__1__Impl : ( ( rule__DomainModel__CodeFileExtensionAssignment_3_1 ) ) ;
    public final void rule__DomainModel__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1442:1: ( ( ( rule__DomainModel__CodeFileExtensionAssignment_3_1 ) ) )
            // InternalModel2Blockly.g:1443:1: ( ( rule__DomainModel__CodeFileExtensionAssignment_3_1 ) )
            {
            // InternalModel2Blockly.g:1443:1: ( ( rule__DomainModel__CodeFileExtensionAssignment_3_1 ) )
            // InternalModel2Blockly.g:1444:2: ( rule__DomainModel__CodeFileExtensionAssignment_3_1 )
            {
             before(grammarAccess.getDomainModelAccess().getCodeFileExtensionAssignment_3_1()); 
            // InternalModel2Blockly.g:1445:2: ( rule__DomainModel__CodeFileExtensionAssignment_3_1 )
            // InternalModel2Blockly.g:1445:3: rule__DomainModel__CodeFileExtensionAssignment_3_1
            {
            pushFollow(FOLLOW_2);
            rule__DomainModel__CodeFileExtensionAssignment_3_1();

            state._fsp--;


            }

             after(grammarAccess.getDomainModelAccess().getCodeFileExtensionAssignment_3_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DomainModel__Group_3__1__Impl"


    // $ANTLR start "rule__DomainModel__Group_4__0"
    // InternalModel2Blockly.g:1454:1: rule__DomainModel__Group_4__0 : rule__DomainModel__Group_4__0__Impl rule__DomainModel__Group_4__1 ;
    public final void rule__DomainModel__Group_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1458:1: ( rule__DomainModel__Group_4__0__Impl rule__DomainModel__Group_4__1 )
            // InternalModel2Blockly.g:1459:2: rule__DomainModel__Group_4__0__Impl rule__DomainModel__Group_4__1
            {
            pushFollow(FOLLOW_10);
            rule__DomainModel__Group_4__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__DomainModel__Group_4__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DomainModel__Group_4__0"


    // $ANTLR start "rule__DomainModel__Group_4__0__Impl"
    // InternalModel2Blockly.g:1466:1: rule__DomainModel__Group_4__0__Impl : ( 'runtimeKind' ) ;
    public final void rule__DomainModel__Group_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1470:1: ( ( 'runtimeKind' ) )
            // InternalModel2Blockly.g:1471:1: ( 'runtimeKind' )
            {
            // InternalModel2Blockly.g:1471:1: ( 'runtimeKind' )
            // InternalModel2Blockly.g:1472:2: 'runtimeKind'
            {
             before(grammarAccess.getDomainModelAccess().getRuntimeKindKeyword_4_0()); 
            match(input,43,FOLLOW_2); 
             after(grammarAccess.getDomainModelAccess().getRuntimeKindKeyword_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DomainModel__Group_4__0__Impl"


    // $ANTLR start "rule__DomainModel__Group_4__1"
    // InternalModel2Blockly.g:1481:1: rule__DomainModel__Group_4__1 : rule__DomainModel__Group_4__1__Impl ;
    public final void rule__DomainModel__Group_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1485:1: ( rule__DomainModel__Group_4__1__Impl )
            // InternalModel2Blockly.g:1486:2: rule__DomainModel__Group_4__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__DomainModel__Group_4__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DomainModel__Group_4__1"


    // $ANTLR start "rule__DomainModel__Group_4__1__Impl"
    // InternalModel2Blockly.g:1492:1: rule__DomainModel__Group_4__1__Impl : ( ( rule__DomainModel__RuntimeKindAssignment_4_1 ) ) ;
    public final void rule__DomainModel__Group_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1496:1: ( ( ( rule__DomainModel__RuntimeKindAssignment_4_1 ) ) )
            // InternalModel2Blockly.g:1497:1: ( ( rule__DomainModel__RuntimeKindAssignment_4_1 ) )
            {
            // InternalModel2Blockly.g:1497:1: ( ( rule__DomainModel__RuntimeKindAssignment_4_1 ) )
            // InternalModel2Blockly.g:1498:2: ( rule__DomainModel__RuntimeKindAssignment_4_1 )
            {
             before(grammarAccess.getDomainModelAccess().getRuntimeKindAssignment_4_1()); 
            // InternalModel2Blockly.g:1499:2: ( rule__DomainModel__RuntimeKindAssignment_4_1 )
            // InternalModel2Blockly.g:1499:3: rule__DomainModel__RuntimeKindAssignment_4_1
            {
            pushFollow(FOLLOW_2);
            rule__DomainModel__RuntimeKindAssignment_4_1();

            state._fsp--;


            }

             after(grammarAccess.getDomainModelAccess().getRuntimeKindAssignment_4_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DomainModel__Group_4__1__Impl"


    // $ANTLR start "rule__CategoryDef__Group__0"
    // InternalModel2Blockly.g:1508:1: rule__CategoryDef__Group__0 : rule__CategoryDef__Group__0__Impl rule__CategoryDef__Group__1 ;
    public final void rule__CategoryDef__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1512:1: ( rule__CategoryDef__Group__0__Impl rule__CategoryDef__Group__1 )
            // InternalModel2Blockly.g:1513:2: rule__CategoryDef__Group__0__Impl rule__CategoryDef__Group__1
            {
            pushFollow(FOLLOW_4);
            rule__CategoryDef__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__CategoryDef__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CategoryDef__Group__0"


    // $ANTLR start "rule__CategoryDef__Group__0__Impl"
    // InternalModel2Blockly.g:1520:1: rule__CategoryDef__Group__0__Impl : ( 'category' ) ;
    public final void rule__CategoryDef__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1524:1: ( ( 'category' ) )
            // InternalModel2Blockly.g:1525:1: ( 'category' )
            {
            // InternalModel2Blockly.g:1525:1: ( 'category' )
            // InternalModel2Blockly.g:1526:2: 'category'
            {
             before(grammarAccess.getCategoryDefAccess().getCategoryKeyword_0()); 
            match(input,44,FOLLOW_2); 
             after(grammarAccess.getCategoryDefAccess().getCategoryKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CategoryDef__Group__0__Impl"


    // $ANTLR start "rule__CategoryDef__Group__1"
    // InternalModel2Blockly.g:1535:1: rule__CategoryDef__Group__1 : rule__CategoryDef__Group__1__Impl rule__CategoryDef__Group__2 ;
    public final void rule__CategoryDef__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1539:1: ( rule__CategoryDef__Group__1__Impl rule__CategoryDef__Group__2 )
            // InternalModel2Blockly.g:1540:2: rule__CategoryDef__Group__1__Impl rule__CategoryDef__Group__2
            {
            pushFollow(FOLLOW_11);
            rule__CategoryDef__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__CategoryDef__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CategoryDef__Group__1"


    // $ANTLR start "rule__CategoryDef__Group__1__Impl"
    // InternalModel2Blockly.g:1547:1: rule__CategoryDef__Group__1__Impl : ( ( rule__CategoryDef__NameAssignment_1 ) ) ;
    public final void rule__CategoryDef__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1551:1: ( ( ( rule__CategoryDef__NameAssignment_1 ) ) )
            // InternalModel2Blockly.g:1552:1: ( ( rule__CategoryDef__NameAssignment_1 ) )
            {
            // InternalModel2Blockly.g:1552:1: ( ( rule__CategoryDef__NameAssignment_1 ) )
            // InternalModel2Blockly.g:1553:2: ( rule__CategoryDef__NameAssignment_1 )
            {
             before(grammarAccess.getCategoryDefAccess().getNameAssignment_1()); 
            // InternalModel2Blockly.g:1554:2: ( rule__CategoryDef__NameAssignment_1 )
            // InternalModel2Blockly.g:1554:3: rule__CategoryDef__NameAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__CategoryDef__NameAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getCategoryDefAccess().getNameAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CategoryDef__Group__1__Impl"


    // $ANTLR start "rule__CategoryDef__Group__2"
    // InternalModel2Blockly.g:1562:1: rule__CategoryDef__Group__2 : rule__CategoryDef__Group__2__Impl rule__CategoryDef__Group__3 ;
    public final void rule__CategoryDef__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1566:1: ( rule__CategoryDef__Group__2__Impl rule__CategoryDef__Group__3 )
            // InternalModel2Blockly.g:1567:2: rule__CategoryDef__Group__2__Impl rule__CategoryDef__Group__3
            {
            pushFollow(FOLLOW_11);
            rule__CategoryDef__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__CategoryDef__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CategoryDef__Group__2"


    // $ANTLR start "rule__CategoryDef__Group__2__Impl"
    // InternalModel2Blockly.g:1574:1: rule__CategoryDef__Group__2__Impl : ( ( rule__CategoryDef__Group_2__0 )? ) ;
    public final void rule__CategoryDef__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1578:1: ( ( ( rule__CategoryDef__Group_2__0 )? ) )
            // InternalModel2Blockly.g:1579:1: ( ( rule__CategoryDef__Group_2__0 )? )
            {
            // InternalModel2Blockly.g:1579:1: ( ( rule__CategoryDef__Group_2__0 )? )
            // InternalModel2Blockly.g:1580:2: ( rule__CategoryDef__Group_2__0 )?
            {
             before(grammarAccess.getCategoryDefAccess().getGroup_2()); 
            // InternalModel2Blockly.g:1581:2: ( rule__CategoryDef__Group_2__0 )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==33) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // InternalModel2Blockly.g:1581:3: rule__CategoryDef__Group_2__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__CategoryDef__Group_2__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getCategoryDefAccess().getGroup_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CategoryDef__Group__2__Impl"


    // $ANTLR start "rule__CategoryDef__Group__3"
    // InternalModel2Blockly.g:1589:1: rule__CategoryDef__Group__3 : rule__CategoryDef__Group__3__Impl rule__CategoryDef__Group__4 ;
    public final void rule__CategoryDef__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1593:1: ( rule__CategoryDef__Group__3__Impl rule__CategoryDef__Group__4 )
            // InternalModel2Blockly.g:1594:2: rule__CategoryDef__Group__3__Impl rule__CategoryDef__Group__4
            {
            pushFollow(FOLLOW_11);
            rule__CategoryDef__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__CategoryDef__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CategoryDef__Group__3"


    // $ANTLR start "rule__CategoryDef__Group__3__Impl"
    // InternalModel2Blockly.g:1601:1: rule__CategoryDef__Group__3__Impl : ( ( rule__CategoryDef__Group_3__0 )? ) ;
    public final void rule__CategoryDef__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1605:1: ( ( ( rule__CategoryDef__Group_3__0 )? ) )
            // InternalModel2Blockly.g:1606:1: ( ( rule__CategoryDef__Group_3__0 )? )
            {
            // InternalModel2Blockly.g:1606:1: ( ( rule__CategoryDef__Group_3__0 )? )
            // InternalModel2Blockly.g:1607:2: ( rule__CategoryDef__Group_3__0 )?
            {
             before(grammarAccess.getCategoryDefAccess().getGroup_3()); 
            // InternalModel2Blockly.g:1608:2: ( rule__CategoryDef__Group_3__0 )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==32) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // InternalModel2Blockly.g:1608:3: rule__CategoryDef__Group_3__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__CategoryDef__Group_3__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getCategoryDefAccess().getGroup_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CategoryDef__Group__3__Impl"


    // $ANTLR start "rule__CategoryDef__Group__4"
    // InternalModel2Blockly.g:1616:1: rule__CategoryDef__Group__4 : rule__CategoryDef__Group__4__Impl ;
    public final void rule__CategoryDef__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1620:1: ( rule__CategoryDef__Group__4__Impl )
            // InternalModel2Blockly.g:1621:2: rule__CategoryDef__Group__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__CategoryDef__Group__4__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CategoryDef__Group__4"


    // $ANTLR start "rule__CategoryDef__Group__4__Impl"
    // InternalModel2Blockly.g:1627:1: rule__CategoryDef__Group__4__Impl : ( ( rule__CategoryDef__Group_4__0 )? ) ;
    public final void rule__CategoryDef__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1631:1: ( ( ( rule__CategoryDef__Group_4__0 )? ) )
            // InternalModel2Blockly.g:1632:1: ( ( rule__CategoryDef__Group_4__0 )? )
            {
            // InternalModel2Blockly.g:1632:1: ( ( rule__CategoryDef__Group_4__0 )? )
            // InternalModel2Blockly.g:1633:2: ( rule__CategoryDef__Group_4__0 )?
            {
             before(grammarAccess.getCategoryDefAccess().getGroup_4()); 
            // InternalModel2Blockly.g:1634:2: ( rule__CategoryDef__Group_4__0 )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==45) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // InternalModel2Blockly.g:1634:3: rule__CategoryDef__Group_4__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__CategoryDef__Group_4__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getCategoryDefAccess().getGroup_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CategoryDef__Group__4__Impl"


    // $ANTLR start "rule__CategoryDef__Group_2__0"
    // InternalModel2Blockly.g:1643:1: rule__CategoryDef__Group_2__0 : rule__CategoryDef__Group_2__0__Impl rule__CategoryDef__Group_2__1 ;
    public final void rule__CategoryDef__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1647:1: ( rule__CategoryDef__Group_2__0__Impl rule__CategoryDef__Group_2__1 )
            // InternalModel2Blockly.g:1648:2: rule__CategoryDef__Group_2__0__Impl rule__CategoryDef__Group_2__1
            {
            pushFollow(FOLLOW_10);
            rule__CategoryDef__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__CategoryDef__Group_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CategoryDef__Group_2__0"


    // $ANTLR start "rule__CategoryDef__Group_2__0__Impl"
    // InternalModel2Blockly.g:1655:1: rule__CategoryDef__Group_2__0__Impl : ( 'label' ) ;
    public final void rule__CategoryDef__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1659:1: ( ( 'label' ) )
            // InternalModel2Blockly.g:1660:1: ( 'label' )
            {
            // InternalModel2Blockly.g:1660:1: ( 'label' )
            // InternalModel2Blockly.g:1661:2: 'label'
            {
             before(grammarAccess.getCategoryDefAccess().getLabelKeyword_2_0()); 
            match(input,33,FOLLOW_2); 
             after(grammarAccess.getCategoryDefAccess().getLabelKeyword_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CategoryDef__Group_2__0__Impl"


    // $ANTLR start "rule__CategoryDef__Group_2__1"
    // InternalModel2Blockly.g:1670:1: rule__CategoryDef__Group_2__1 : rule__CategoryDef__Group_2__1__Impl ;
    public final void rule__CategoryDef__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1674:1: ( rule__CategoryDef__Group_2__1__Impl )
            // InternalModel2Blockly.g:1675:2: rule__CategoryDef__Group_2__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__CategoryDef__Group_2__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CategoryDef__Group_2__1"


    // $ANTLR start "rule__CategoryDef__Group_2__1__Impl"
    // InternalModel2Blockly.g:1681:1: rule__CategoryDef__Group_2__1__Impl : ( ( rule__CategoryDef__LabelAssignment_2_1 ) ) ;
    public final void rule__CategoryDef__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1685:1: ( ( ( rule__CategoryDef__LabelAssignment_2_1 ) ) )
            // InternalModel2Blockly.g:1686:1: ( ( rule__CategoryDef__LabelAssignment_2_1 ) )
            {
            // InternalModel2Blockly.g:1686:1: ( ( rule__CategoryDef__LabelAssignment_2_1 ) )
            // InternalModel2Blockly.g:1687:2: ( rule__CategoryDef__LabelAssignment_2_1 )
            {
             before(grammarAccess.getCategoryDefAccess().getLabelAssignment_2_1()); 
            // InternalModel2Blockly.g:1688:2: ( rule__CategoryDef__LabelAssignment_2_1 )
            // InternalModel2Blockly.g:1688:3: rule__CategoryDef__LabelAssignment_2_1
            {
            pushFollow(FOLLOW_2);
            rule__CategoryDef__LabelAssignment_2_1();

            state._fsp--;


            }

             after(grammarAccess.getCategoryDefAccess().getLabelAssignment_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CategoryDef__Group_2__1__Impl"


    // $ANTLR start "rule__CategoryDef__Group_3__0"
    // InternalModel2Blockly.g:1697:1: rule__CategoryDef__Group_3__0 : rule__CategoryDef__Group_3__0__Impl rule__CategoryDef__Group_3__1 ;
    public final void rule__CategoryDef__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1701:1: ( rule__CategoryDef__Group_3__0__Impl rule__CategoryDef__Group_3__1 )
            // InternalModel2Blockly.g:1702:2: rule__CategoryDef__Group_3__0__Impl rule__CategoryDef__Group_3__1
            {
            pushFollow(FOLLOW_12);
            rule__CategoryDef__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__CategoryDef__Group_3__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CategoryDef__Group_3__0"


    // $ANTLR start "rule__CategoryDef__Group_3__0__Impl"
    // InternalModel2Blockly.g:1709:1: rule__CategoryDef__Group_3__0__Impl : ( 'colour' ) ;
    public final void rule__CategoryDef__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1713:1: ( ( 'colour' ) )
            // InternalModel2Blockly.g:1714:1: ( 'colour' )
            {
            // InternalModel2Blockly.g:1714:1: ( 'colour' )
            // InternalModel2Blockly.g:1715:2: 'colour'
            {
             before(grammarAccess.getCategoryDefAccess().getColourKeyword_3_0()); 
            match(input,32,FOLLOW_2); 
             after(grammarAccess.getCategoryDefAccess().getColourKeyword_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CategoryDef__Group_3__0__Impl"


    // $ANTLR start "rule__CategoryDef__Group_3__1"
    // InternalModel2Blockly.g:1724:1: rule__CategoryDef__Group_3__1 : rule__CategoryDef__Group_3__1__Impl ;
    public final void rule__CategoryDef__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1728:1: ( rule__CategoryDef__Group_3__1__Impl )
            // InternalModel2Blockly.g:1729:2: rule__CategoryDef__Group_3__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__CategoryDef__Group_3__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CategoryDef__Group_3__1"


    // $ANTLR start "rule__CategoryDef__Group_3__1__Impl"
    // InternalModel2Blockly.g:1735:1: rule__CategoryDef__Group_3__1__Impl : ( ( rule__CategoryDef__ColourAssignment_3_1 ) ) ;
    public final void rule__CategoryDef__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1739:1: ( ( ( rule__CategoryDef__ColourAssignment_3_1 ) ) )
            // InternalModel2Blockly.g:1740:1: ( ( rule__CategoryDef__ColourAssignment_3_1 ) )
            {
            // InternalModel2Blockly.g:1740:1: ( ( rule__CategoryDef__ColourAssignment_3_1 ) )
            // InternalModel2Blockly.g:1741:2: ( rule__CategoryDef__ColourAssignment_3_1 )
            {
             before(grammarAccess.getCategoryDefAccess().getColourAssignment_3_1()); 
            // InternalModel2Blockly.g:1742:2: ( rule__CategoryDef__ColourAssignment_3_1 )
            // InternalModel2Blockly.g:1742:3: rule__CategoryDef__ColourAssignment_3_1
            {
            pushFollow(FOLLOW_2);
            rule__CategoryDef__ColourAssignment_3_1();

            state._fsp--;


            }

             after(grammarAccess.getCategoryDefAccess().getColourAssignment_3_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CategoryDef__Group_3__1__Impl"


    // $ANTLR start "rule__CategoryDef__Group_4__0"
    // InternalModel2Blockly.g:1751:1: rule__CategoryDef__Group_4__0 : rule__CategoryDef__Group_4__0__Impl rule__CategoryDef__Group_4__1 ;
    public final void rule__CategoryDef__Group_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1755:1: ( rule__CategoryDef__Group_4__0__Impl rule__CategoryDef__Group_4__1 )
            // InternalModel2Blockly.g:1756:2: rule__CategoryDef__Group_4__0__Impl rule__CategoryDef__Group_4__1
            {
            pushFollow(FOLLOW_13);
            rule__CategoryDef__Group_4__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__CategoryDef__Group_4__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CategoryDef__Group_4__0"


    // $ANTLR start "rule__CategoryDef__Group_4__0__Impl"
    // InternalModel2Blockly.g:1763:1: rule__CategoryDef__Group_4__0__Impl : ( '{' ) ;
    public final void rule__CategoryDef__Group_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1767:1: ( ( '{' ) )
            // InternalModel2Blockly.g:1768:1: ( '{' )
            {
            // InternalModel2Blockly.g:1768:1: ( '{' )
            // InternalModel2Blockly.g:1769:2: '{'
            {
             before(grammarAccess.getCategoryDefAccess().getLeftCurlyBracketKeyword_4_0()); 
            match(input,45,FOLLOW_2); 
             after(grammarAccess.getCategoryDefAccess().getLeftCurlyBracketKeyword_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CategoryDef__Group_4__0__Impl"


    // $ANTLR start "rule__CategoryDef__Group_4__1"
    // InternalModel2Blockly.g:1778:1: rule__CategoryDef__Group_4__1 : rule__CategoryDef__Group_4__1__Impl rule__CategoryDef__Group_4__2 ;
    public final void rule__CategoryDef__Group_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1782:1: ( rule__CategoryDef__Group_4__1__Impl rule__CategoryDef__Group_4__2 )
            // InternalModel2Blockly.g:1783:2: rule__CategoryDef__Group_4__1__Impl rule__CategoryDef__Group_4__2
            {
            pushFollow(FOLLOW_13);
            rule__CategoryDef__Group_4__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__CategoryDef__Group_4__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CategoryDef__Group_4__1"


    // $ANTLR start "rule__CategoryDef__Group_4__1__Impl"
    // InternalModel2Blockly.g:1790:1: rule__CategoryDef__Group_4__1__Impl : ( ( rule__CategoryDef__SubcategoriesAssignment_4_1 )* ) ;
    public final void rule__CategoryDef__Group_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1794:1: ( ( ( rule__CategoryDef__SubcategoriesAssignment_4_1 )* ) )
            // InternalModel2Blockly.g:1795:1: ( ( rule__CategoryDef__SubcategoriesAssignment_4_1 )* )
            {
            // InternalModel2Blockly.g:1795:1: ( ( rule__CategoryDef__SubcategoriesAssignment_4_1 )* )
            // InternalModel2Blockly.g:1796:2: ( rule__CategoryDef__SubcategoriesAssignment_4_1 )*
            {
             before(grammarAccess.getCategoryDefAccess().getSubcategoriesAssignment_4_1()); 
            // InternalModel2Blockly.g:1797:2: ( rule__CategoryDef__SubcategoriesAssignment_4_1 )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==44) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // InternalModel2Blockly.g:1797:3: rule__CategoryDef__SubcategoriesAssignment_4_1
            	    {
            	    pushFollow(FOLLOW_6);
            	    rule__CategoryDef__SubcategoriesAssignment_4_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop22;
                }
            } while (true);

             after(grammarAccess.getCategoryDefAccess().getSubcategoriesAssignment_4_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CategoryDef__Group_4__1__Impl"


    // $ANTLR start "rule__CategoryDef__Group_4__2"
    // InternalModel2Blockly.g:1805:1: rule__CategoryDef__Group_4__2 : rule__CategoryDef__Group_4__2__Impl ;
    public final void rule__CategoryDef__Group_4__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1809:1: ( rule__CategoryDef__Group_4__2__Impl )
            // InternalModel2Blockly.g:1810:2: rule__CategoryDef__Group_4__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__CategoryDef__Group_4__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CategoryDef__Group_4__2"


    // $ANTLR start "rule__CategoryDef__Group_4__2__Impl"
    // InternalModel2Blockly.g:1816:1: rule__CategoryDef__Group_4__2__Impl : ( '}' ) ;
    public final void rule__CategoryDef__Group_4__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1820:1: ( ( '}' ) )
            // InternalModel2Blockly.g:1821:1: ( '}' )
            {
            // InternalModel2Blockly.g:1821:1: ( '}' )
            // InternalModel2Blockly.g:1822:2: '}'
            {
             before(grammarAccess.getCategoryDefAccess().getRightCurlyBracketKeyword_4_2()); 
            match(input,46,FOLLOW_2); 
             after(grammarAccess.getCategoryDefAccess().getRightCurlyBracketKeyword_4_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CategoryDef__Group_4__2__Impl"


    // $ANTLR start "rule__ClassDef__Group__0"
    // InternalModel2Blockly.g:1832:1: rule__ClassDef__Group__0 : rule__ClassDef__Group__0__Impl rule__ClassDef__Group__1 ;
    public final void rule__ClassDef__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1836:1: ( rule__ClassDef__Group__0__Impl rule__ClassDef__Group__1 )
            // InternalModel2Blockly.g:1837:2: rule__ClassDef__Group__0__Impl rule__ClassDef__Group__1
            {
            pushFollow(FOLLOW_14);
            rule__ClassDef__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ClassDef__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group__0"


    // $ANTLR start "rule__ClassDef__Group__0__Impl"
    // InternalModel2Blockly.g:1844:1: rule__ClassDef__Group__0__Impl : ( ( rule__ClassDef__AbstractAssignment_0 )? ) ;
    public final void rule__ClassDef__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1848:1: ( ( ( rule__ClassDef__AbstractAssignment_0 )? ) )
            // InternalModel2Blockly.g:1849:1: ( ( rule__ClassDef__AbstractAssignment_0 )? )
            {
            // InternalModel2Blockly.g:1849:1: ( ( rule__ClassDef__AbstractAssignment_0 )? )
            // InternalModel2Blockly.g:1850:2: ( rule__ClassDef__AbstractAssignment_0 )?
            {
             before(grammarAccess.getClassDefAccess().getAbstractAssignment_0()); 
            // InternalModel2Blockly.g:1851:2: ( rule__ClassDef__AbstractAssignment_0 )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==89) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // InternalModel2Blockly.g:1851:3: rule__ClassDef__AbstractAssignment_0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ClassDef__AbstractAssignment_0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getClassDefAccess().getAbstractAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group__0__Impl"


    // $ANTLR start "rule__ClassDef__Group__1"
    // InternalModel2Blockly.g:1859:1: rule__ClassDef__Group__1 : rule__ClassDef__Group__1__Impl rule__ClassDef__Group__2 ;
    public final void rule__ClassDef__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1863:1: ( rule__ClassDef__Group__1__Impl rule__ClassDef__Group__2 )
            // InternalModel2Blockly.g:1864:2: rule__ClassDef__Group__1__Impl rule__ClassDef__Group__2
            {
            pushFollow(FOLLOW_14);
            rule__ClassDef__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ClassDef__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group__1"


    // $ANTLR start "rule__ClassDef__Group__1__Impl"
    // InternalModel2Blockly.g:1871:1: rule__ClassDef__Group__1__Impl : ( ( rule__ClassDef__Group_1__0 )? ) ;
    public final void rule__ClassDef__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1875:1: ( ( ( rule__ClassDef__Group_1__0 )? ) )
            // InternalModel2Blockly.g:1876:1: ( ( rule__ClassDef__Group_1__0 )? )
            {
            // InternalModel2Blockly.g:1876:1: ( ( rule__ClassDef__Group_1__0 )? )
            // InternalModel2Blockly.g:1877:2: ( rule__ClassDef__Group_1__0 )?
            {
             before(grammarAccess.getClassDefAccess().getGroup_1()); 
            // InternalModel2Blockly.g:1878:2: ( rule__ClassDef__Group_1__0 )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==90) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // InternalModel2Blockly.g:1878:3: rule__ClassDef__Group_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ClassDef__Group_1__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getClassDefAccess().getGroup_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group__1__Impl"


    // $ANTLR start "rule__ClassDef__Group__2"
    // InternalModel2Blockly.g:1886:1: rule__ClassDef__Group__2 : rule__ClassDef__Group__2__Impl rule__ClassDef__Group__3 ;
    public final void rule__ClassDef__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1890:1: ( rule__ClassDef__Group__2__Impl rule__ClassDef__Group__3 )
            // InternalModel2Blockly.g:1891:2: rule__ClassDef__Group__2__Impl rule__ClassDef__Group__3
            {
            pushFollow(FOLLOW_4);
            rule__ClassDef__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ClassDef__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group__2"


    // $ANTLR start "rule__ClassDef__Group__2__Impl"
    // InternalModel2Blockly.g:1898:1: rule__ClassDef__Group__2__Impl : ( 'class' ) ;
    public final void rule__ClassDef__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1902:1: ( ( 'class' ) )
            // InternalModel2Blockly.g:1903:1: ( 'class' )
            {
            // InternalModel2Blockly.g:1903:1: ( 'class' )
            // InternalModel2Blockly.g:1904:2: 'class'
            {
             before(grammarAccess.getClassDefAccess().getClassKeyword_2()); 
            match(input,47,FOLLOW_2); 
             after(grammarAccess.getClassDefAccess().getClassKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group__2__Impl"


    // $ANTLR start "rule__ClassDef__Group__3"
    // InternalModel2Blockly.g:1913:1: rule__ClassDef__Group__3 : rule__ClassDef__Group__3__Impl rule__ClassDef__Group__4 ;
    public final void rule__ClassDef__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1917:1: ( rule__ClassDef__Group__3__Impl rule__ClassDef__Group__4 )
            // InternalModel2Blockly.g:1918:2: rule__ClassDef__Group__3__Impl rule__ClassDef__Group__4
            {
            pushFollow(FOLLOW_15);
            rule__ClassDef__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ClassDef__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group__3"


    // $ANTLR start "rule__ClassDef__Group__3__Impl"
    // InternalModel2Blockly.g:1925:1: rule__ClassDef__Group__3__Impl : ( ( rule__ClassDef__NameAssignment_3 ) ) ;
    public final void rule__ClassDef__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1929:1: ( ( ( rule__ClassDef__NameAssignment_3 ) ) )
            // InternalModel2Blockly.g:1930:1: ( ( rule__ClassDef__NameAssignment_3 ) )
            {
            // InternalModel2Blockly.g:1930:1: ( ( rule__ClassDef__NameAssignment_3 ) )
            // InternalModel2Blockly.g:1931:2: ( rule__ClassDef__NameAssignment_3 )
            {
             before(grammarAccess.getClassDefAccess().getNameAssignment_3()); 
            // InternalModel2Blockly.g:1932:2: ( rule__ClassDef__NameAssignment_3 )
            // InternalModel2Blockly.g:1932:3: rule__ClassDef__NameAssignment_3
            {
            pushFollow(FOLLOW_2);
            rule__ClassDef__NameAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getClassDefAccess().getNameAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group__3__Impl"


    // $ANTLR start "rule__ClassDef__Group__4"
    // InternalModel2Blockly.g:1940:1: rule__ClassDef__Group__4 : rule__ClassDef__Group__4__Impl rule__ClassDef__Group__5 ;
    public final void rule__ClassDef__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1944:1: ( rule__ClassDef__Group__4__Impl rule__ClassDef__Group__5 )
            // InternalModel2Blockly.g:1945:2: rule__ClassDef__Group__4__Impl rule__ClassDef__Group__5
            {
            pushFollow(FOLLOW_15);
            rule__ClassDef__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ClassDef__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group__4"


    // $ANTLR start "rule__ClassDef__Group__4__Impl"
    // InternalModel2Blockly.g:1952:1: rule__ClassDef__Group__4__Impl : ( ( rule__ClassDef__Group_4__0 )? ) ;
    public final void rule__ClassDef__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1956:1: ( ( ( rule__ClassDef__Group_4__0 )? ) )
            // InternalModel2Blockly.g:1957:1: ( ( rule__ClassDef__Group_4__0 )? )
            {
            // InternalModel2Blockly.g:1957:1: ( ( rule__ClassDef__Group_4__0 )? )
            // InternalModel2Blockly.g:1958:2: ( rule__ClassDef__Group_4__0 )?
            {
             before(grammarAccess.getClassDefAccess().getGroup_4()); 
            // InternalModel2Blockly.g:1959:2: ( rule__ClassDef__Group_4__0 )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==49) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // InternalModel2Blockly.g:1959:3: rule__ClassDef__Group_4__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ClassDef__Group_4__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getClassDefAccess().getGroup_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group__4__Impl"


    // $ANTLR start "rule__ClassDef__Group__5"
    // InternalModel2Blockly.g:1967:1: rule__ClassDef__Group__5 : rule__ClassDef__Group__5__Impl rule__ClassDef__Group__6 ;
    public final void rule__ClassDef__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1971:1: ( rule__ClassDef__Group__5__Impl rule__ClassDef__Group__6 )
            // InternalModel2Blockly.g:1972:2: rule__ClassDef__Group__5__Impl rule__ClassDef__Group__6
            {
            pushFollow(FOLLOW_15);
            rule__ClassDef__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ClassDef__Group__6();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group__5"


    // $ANTLR start "rule__ClassDef__Group__5__Impl"
    // InternalModel2Blockly.g:1979:1: rule__ClassDef__Group__5__Impl : ( ( rule__ClassDef__Group_5__0 )? ) ;
    public final void rule__ClassDef__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1983:1: ( ( ( rule__ClassDef__Group_5__0 )? ) )
            // InternalModel2Blockly.g:1984:1: ( ( rule__ClassDef__Group_5__0 )? )
            {
            // InternalModel2Blockly.g:1984:1: ( ( rule__ClassDef__Group_5__0 )? )
            // InternalModel2Blockly.g:1985:2: ( rule__ClassDef__Group_5__0 )?
            {
             before(grammarAccess.getClassDefAccess().getGroup_5()); 
            // InternalModel2Blockly.g:1986:2: ( rule__ClassDef__Group_5__0 )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==44) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // InternalModel2Blockly.g:1986:3: rule__ClassDef__Group_5__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ClassDef__Group_5__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getClassDefAccess().getGroup_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group__5__Impl"


    // $ANTLR start "rule__ClassDef__Group__6"
    // InternalModel2Blockly.g:1994:1: rule__ClassDef__Group__6 : rule__ClassDef__Group__6__Impl rule__ClassDef__Group__7 ;
    public final void rule__ClassDef__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:1998:1: ( rule__ClassDef__Group__6__Impl rule__ClassDef__Group__7 )
            // InternalModel2Blockly.g:1999:2: rule__ClassDef__Group__6__Impl rule__ClassDef__Group__7
            {
            pushFollow(FOLLOW_15);
            rule__ClassDef__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ClassDef__Group__7();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group__6"


    // $ANTLR start "rule__ClassDef__Group__6__Impl"
    // InternalModel2Blockly.g:2006:1: rule__ClassDef__Group__6__Impl : ( ( rule__ClassDef__Group_6__0 )? ) ;
    public final void rule__ClassDef__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2010:1: ( ( ( rule__ClassDef__Group_6__0 )? ) )
            // InternalModel2Blockly.g:2011:1: ( ( rule__ClassDef__Group_6__0 )? )
            {
            // InternalModel2Blockly.g:2011:1: ( ( rule__ClassDef__Group_6__0 )? )
            // InternalModel2Blockly.g:2012:2: ( rule__ClassDef__Group_6__0 )?
            {
             before(grammarAccess.getClassDefAccess().getGroup_6()); 
            // InternalModel2Blockly.g:2013:2: ( rule__ClassDef__Group_6__0 )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==32) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // InternalModel2Blockly.g:2013:3: rule__ClassDef__Group_6__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ClassDef__Group_6__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getClassDefAccess().getGroup_6()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group__6__Impl"


    // $ANTLR start "rule__ClassDef__Group__7"
    // InternalModel2Blockly.g:2021:1: rule__ClassDef__Group__7 : rule__ClassDef__Group__7__Impl rule__ClassDef__Group__8 ;
    public final void rule__ClassDef__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2025:1: ( rule__ClassDef__Group__7__Impl rule__ClassDef__Group__8 )
            // InternalModel2Blockly.g:2026:2: rule__ClassDef__Group__7__Impl rule__ClassDef__Group__8
            {
            pushFollow(FOLLOW_15);
            rule__ClassDef__Group__7__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ClassDef__Group__8();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group__7"


    // $ANTLR start "rule__ClassDef__Group__7__Impl"
    // InternalModel2Blockly.g:2033:1: rule__ClassDef__Group__7__Impl : ( ( rule__ClassDef__Group_7__0 )? ) ;
    public final void rule__ClassDef__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2037:1: ( ( ( rule__ClassDef__Group_7__0 )? ) )
            // InternalModel2Blockly.g:2038:1: ( ( rule__ClassDef__Group_7__0 )? )
            {
            // InternalModel2Blockly.g:2038:1: ( ( rule__ClassDef__Group_7__0 )? )
            // InternalModel2Blockly.g:2039:2: ( rule__ClassDef__Group_7__0 )?
            {
             before(grammarAccess.getClassDefAccess().getGroup_7()); 
            // InternalModel2Blockly.g:2040:2: ( rule__ClassDef__Group_7__0 )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==33) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // InternalModel2Blockly.g:2040:3: rule__ClassDef__Group_7__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ClassDef__Group_7__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getClassDefAccess().getGroup_7()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group__7__Impl"


    // $ANTLR start "rule__ClassDef__Group__8"
    // InternalModel2Blockly.g:2048:1: rule__ClassDef__Group__8 : rule__ClassDef__Group__8__Impl rule__ClassDef__Group__9 ;
    public final void rule__ClassDef__Group__8() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2052:1: ( rule__ClassDef__Group__8__Impl rule__ClassDef__Group__9 )
            // InternalModel2Blockly.g:2053:2: rule__ClassDef__Group__8__Impl rule__ClassDef__Group__9
            {
            pushFollow(FOLLOW_15);
            rule__ClassDef__Group__8__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ClassDef__Group__9();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group__8"


    // $ANTLR start "rule__ClassDef__Group__8__Impl"
    // InternalModel2Blockly.g:2060:1: rule__ClassDef__Group__8__Impl : ( ( rule__ClassDef__Group_8__0 )? ) ;
    public final void rule__ClassDef__Group__8__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2064:1: ( ( ( rule__ClassDef__Group_8__0 )? ) )
            // InternalModel2Blockly.g:2065:1: ( ( rule__ClassDef__Group_8__0 )? )
            {
            // InternalModel2Blockly.g:2065:1: ( ( rule__ClassDef__Group_8__0 )? )
            // InternalModel2Blockly.g:2066:2: ( rule__ClassDef__Group_8__0 )?
            {
             before(grammarAccess.getClassDefAccess().getGroup_8()); 
            // InternalModel2Blockly.g:2067:2: ( rule__ClassDef__Group_8__0 )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==50) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // InternalModel2Blockly.g:2067:3: rule__ClassDef__Group_8__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ClassDef__Group_8__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getClassDefAccess().getGroup_8()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group__8__Impl"


    // $ANTLR start "rule__ClassDef__Group__9"
    // InternalModel2Blockly.g:2075:1: rule__ClassDef__Group__9 : rule__ClassDef__Group__9__Impl rule__ClassDef__Group__10 ;
    public final void rule__ClassDef__Group__9() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2079:1: ( rule__ClassDef__Group__9__Impl rule__ClassDef__Group__10 )
            // InternalModel2Blockly.g:2080:2: rule__ClassDef__Group__9__Impl rule__ClassDef__Group__10
            {
            pushFollow(FOLLOW_15);
            rule__ClassDef__Group__9__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ClassDef__Group__10();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group__9"


    // $ANTLR start "rule__ClassDef__Group__9__Impl"
    // InternalModel2Blockly.g:2087:1: rule__ClassDef__Group__9__Impl : ( ( rule__ClassDef__Group_9__0 )? ) ;
    public final void rule__ClassDef__Group__9__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2091:1: ( ( ( rule__ClassDef__Group_9__0 )? ) )
            // InternalModel2Blockly.g:2092:1: ( ( rule__ClassDef__Group_9__0 )? )
            {
            // InternalModel2Blockly.g:2092:1: ( ( rule__ClassDef__Group_9__0 )? )
            // InternalModel2Blockly.g:2093:2: ( rule__ClassDef__Group_9__0 )?
            {
             before(grammarAccess.getClassDefAccess().getGroup_9()); 
            // InternalModel2Blockly.g:2094:2: ( rule__ClassDef__Group_9__0 )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==51) ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // InternalModel2Blockly.g:2094:3: rule__ClassDef__Group_9__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ClassDef__Group_9__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getClassDefAccess().getGroup_9()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group__9__Impl"


    // $ANTLR start "rule__ClassDef__Group__10"
    // InternalModel2Blockly.g:2102:1: rule__ClassDef__Group__10 : rule__ClassDef__Group__10__Impl rule__ClassDef__Group__11 ;
    public final void rule__ClassDef__Group__10() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2106:1: ( rule__ClassDef__Group__10__Impl rule__ClassDef__Group__11 )
            // InternalModel2Blockly.g:2107:2: rule__ClassDef__Group__10__Impl rule__ClassDef__Group__11
            {
            pushFollow(FOLLOW_15);
            rule__ClassDef__Group__10__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ClassDef__Group__11();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group__10"


    // $ANTLR start "rule__ClassDef__Group__10__Impl"
    // InternalModel2Blockly.g:2114:1: rule__ClassDef__Group__10__Impl : ( ( rule__ClassDef__Group_10__0 )? ) ;
    public final void rule__ClassDef__Group__10__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2118:1: ( ( ( rule__ClassDef__Group_10__0 )? ) )
            // InternalModel2Blockly.g:2119:1: ( ( rule__ClassDef__Group_10__0 )? )
            {
            // InternalModel2Blockly.g:2119:1: ( ( rule__ClassDef__Group_10__0 )? )
            // InternalModel2Blockly.g:2120:2: ( rule__ClassDef__Group_10__0 )?
            {
             before(grammarAccess.getClassDefAccess().getGroup_10()); 
            // InternalModel2Blockly.g:2121:2: ( rule__ClassDef__Group_10__0 )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==52) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // InternalModel2Blockly.g:2121:3: rule__ClassDef__Group_10__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ClassDef__Group_10__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getClassDefAccess().getGroup_10()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group__10__Impl"


    // $ANTLR start "rule__ClassDef__Group__11"
    // InternalModel2Blockly.g:2129:1: rule__ClassDef__Group__11 : rule__ClassDef__Group__11__Impl rule__ClassDef__Group__12 ;
    public final void rule__ClassDef__Group__11() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2133:1: ( rule__ClassDef__Group__11__Impl rule__ClassDef__Group__12 )
            // InternalModel2Blockly.g:2134:2: rule__ClassDef__Group__11__Impl rule__ClassDef__Group__12
            {
            pushFollow(FOLLOW_15);
            rule__ClassDef__Group__11__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ClassDef__Group__12();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group__11"


    // $ANTLR start "rule__ClassDef__Group__11__Impl"
    // InternalModel2Blockly.g:2141:1: rule__ClassDef__Group__11__Impl : ( ( rule__ClassDef__Group_11__0 )? ) ;
    public final void rule__ClassDef__Group__11__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2145:1: ( ( ( rule__ClassDef__Group_11__0 )? ) )
            // InternalModel2Blockly.g:2146:1: ( ( rule__ClassDef__Group_11__0 )? )
            {
            // InternalModel2Blockly.g:2146:1: ( ( rule__ClassDef__Group_11__0 )? )
            // InternalModel2Blockly.g:2147:2: ( rule__ClassDef__Group_11__0 )?
            {
             before(grammarAccess.getClassDefAccess().getGroup_11()); 
            // InternalModel2Blockly.g:2148:2: ( rule__ClassDef__Group_11__0 )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==53) ) {
                alt32=1;
            }
            switch (alt32) {
                case 1 :
                    // InternalModel2Blockly.g:2148:3: rule__ClassDef__Group_11__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ClassDef__Group_11__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getClassDefAccess().getGroup_11()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group__11__Impl"


    // $ANTLR start "rule__ClassDef__Group__12"
    // InternalModel2Blockly.g:2156:1: rule__ClassDef__Group__12 : rule__ClassDef__Group__12__Impl rule__ClassDef__Group__13 ;
    public final void rule__ClassDef__Group__12() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2160:1: ( rule__ClassDef__Group__12__Impl rule__ClassDef__Group__13 )
            // InternalModel2Blockly.g:2161:2: rule__ClassDef__Group__12__Impl rule__ClassDef__Group__13
            {
            pushFollow(FOLLOW_15);
            rule__ClassDef__Group__12__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ClassDef__Group__13();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group__12"


    // $ANTLR start "rule__ClassDef__Group__12__Impl"
    // InternalModel2Blockly.g:2168:1: rule__ClassDef__Group__12__Impl : ( ( rule__ClassDef__InlineAssignment_12 )? ) ;
    public final void rule__ClassDef__Group__12__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2172:1: ( ( ( rule__ClassDef__InlineAssignment_12 )? ) )
            // InternalModel2Blockly.g:2173:1: ( ( rule__ClassDef__InlineAssignment_12 )? )
            {
            // InternalModel2Blockly.g:2173:1: ( ( rule__ClassDef__InlineAssignment_12 )? )
            // InternalModel2Blockly.g:2174:2: ( rule__ClassDef__InlineAssignment_12 )?
            {
             before(grammarAccess.getClassDefAccess().getInlineAssignment_12()); 
            // InternalModel2Blockly.g:2175:2: ( rule__ClassDef__InlineAssignment_12 )?
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==91) ) {
                alt33=1;
            }
            switch (alt33) {
                case 1 :
                    // InternalModel2Blockly.g:2175:3: rule__ClassDef__InlineAssignment_12
                    {
                    pushFollow(FOLLOW_2);
                    rule__ClassDef__InlineAssignment_12();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getClassDefAccess().getInlineAssignment_12()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group__12__Impl"


    // $ANTLR start "rule__ClassDef__Group__13"
    // InternalModel2Blockly.g:2183:1: rule__ClassDef__Group__13 : rule__ClassDef__Group__13__Impl rule__ClassDef__Group__14 ;
    public final void rule__ClassDef__Group__13() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2187:1: ( rule__ClassDef__Group__13__Impl rule__ClassDef__Group__14 )
            // InternalModel2Blockly.g:2188:2: rule__ClassDef__Group__13__Impl rule__ClassDef__Group__14
            {
            pushFollow(FOLLOW_15);
            rule__ClassDef__Group__13__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ClassDef__Group__14();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group__13"


    // $ANTLR start "rule__ClassDef__Group__13__Impl"
    // InternalModel2Blockly.g:2195:1: rule__ClassDef__Group__13__Impl : ( ( rule__ClassDef__Group_13__0 )? ) ;
    public final void rule__ClassDef__Group__13__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2199:1: ( ( ( rule__ClassDef__Group_13__0 )? ) )
            // InternalModel2Blockly.g:2200:1: ( ( rule__ClassDef__Group_13__0 )? )
            {
            // InternalModel2Blockly.g:2200:1: ( ( rule__ClassDef__Group_13__0 )? )
            // InternalModel2Blockly.g:2201:2: ( rule__ClassDef__Group_13__0 )?
            {
             before(grammarAccess.getClassDefAccess().getGroup_13()); 
            // InternalModel2Blockly.g:2202:2: ( rule__ClassDef__Group_13__0 )?
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==54) ) {
                alt34=1;
            }
            switch (alt34) {
                case 1 :
                    // InternalModel2Blockly.g:2202:3: rule__ClassDef__Group_13__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ClassDef__Group_13__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getClassDefAccess().getGroup_13()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group__13__Impl"


    // $ANTLR start "rule__ClassDef__Group__14"
    // InternalModel2Blockly.g:2210:1: rule__ClassDef__Group__14 : rule__ClassDef__Group__14__Impl rule__ClassDef__Group__15 ;
    public final void rule__ClassDef__Group__14() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2214:1: ( rule__ClassDef__Group__14__Impl rule__ClassDef__Group__15 )
            // InternalModel2Blockly.g:2215:2: rule__ClassDef__Group__14__Impl rule__ClassDef__Group__15
            {
            pushFollow(FOLLOW_16);
            rule__ClassDef__Group__14__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ClassDef__Group__15();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group__14"


    // $ANTLR start "rule__ClassDef__Group__14__Impl"
    // InternalModel2Blockly.g:2222:1: rule__ClassDef__Group__14__Impl : ( '{' ) ;
    public final void rule__ClassDef__Group__14__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2226:1: ( ( '{' ) )
            // InternalModel2Blockly.g:2227:1: ( '{' )
            {
            // InternalModel2Blockly.g:2227:1: ( '{' )
            // InternalModel2Blockly.g:2228:2: '{'
            {
             before(grammarAccess.getClassDefAccess().getLeftCurlyBracketKeyword_14()); 
            match(input,45,FOLLOW_2); 
             after(grammarAccess.getClassDefAccess().getLeftCurlyBracketKeyword_14()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group__14__Impl"


    // $ANTLR start "rule__ClassDef__Group__15"
    // InternalModel2Blockly.g:2237:1: rule__ClassDef__Group__15 : rule__ClassDef__Group__15__Impl rule__ClassDef__Group__16 ;
    public final void rule__ClassDef__Group__15() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2241:1: ( rule__ClassDef__Group__15__Impl rule__ClassDef__Group__16 )
            // InternalModel2Blockly.g:2242:2: rule__ClassDef__Group__15__Impl rule__ClassDef__Group__16
            {
            pushFollow(FOLLOW_16);
            rule__ClassDef__Group__15__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ClassDef__Group__16();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group__15"


    // $ANTLR start "rule__ClassDef__Group__15__Impl"
    // InternalModel2Blockly.g:2249:1: rule__ClassDef__Group__15__Impl : ( ( rule__ClassDef__FeaturesAssignment_15 )* ) ;
    public final void rule__ClassDef__Group__15__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2253:1: ( ( ( rule__ClassDef__FeaturesAssignment_15 )* ) )
            // InternalModel2Blockly.g:2254:1: ( ( rule__ClassDef__FeaturesAssignment_15 )* )
            {
            // InternalModel2Blockly.g:2254:1: ( ( rule__ClassDef__FeaturesAssignment_15 )* )
            // InternalModel2Blockly.g:2255:2: ( rule__ClassDef__FeaturesAssignment_15 )*
            {
             before(grammarAccess.getClassDefAccess().getFeaturesAssignment_15()); 
            // InternalModel2Blockly.g:2256:2: ( rule__ClassDef__FeaturesAssignment_15 )*
            loop35:
            do {
                int alt35=2;
                int LA35_0 = input.LA(1);

                if ( (LA35_0==55||LA35_0==63||LA35_0==67||LA35_0==69) ) {
                    alt35=1;
                }


                switch (alt35) {
            	case 1 :
            	    // InternalModel2Blockly.g:2256:3: rule__ClassDef__FeaturesAssignment_15
            	    {
            	    pushFollow(FOLLOW_17);
            	    rule__ClassDef__FeaturesAssignment_15();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop35;
                }
            } while (true);

             after(grammarAccess.getClassDefAccess().getFeaturesAssignment_15()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group__15__Impl"


    // $ANTLR start "rule__ClassDef__Group__16"
    // InternalModel2Blockly.g:2264:1: rule__ClassDef__Group__16 : rule__ClassDef__Group__16__Impl ;
    public final void rule__ClassDef__Group__16() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2268:1: ( rule__ClassDef__Group__16__Impl )
            // InternalModel2Blockly.g:2269:2: rule__ClassDef__Group__16__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ClassDef__Group__16__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group__16"


    // $ANTLR start "rule__ClassDef__Group__16__Impl"
    // InternalModel2Blockly.g:2275:1: rule__ClassDef__Group__16__Impl : ( '}' ) ;
    public final void rule__ClassDef__Group__16__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2279:1: ( ( '}' ) )
            // InternalModel2Blockly.g:2280:1: ( '}' )
            {
            // InternalModel2Blockly.g:2280:1: ( '}' )
            // InternalModel2Blockly.g:2281:2: '}'
            {
             before(grammarAccess.getClassDefAccess().getRightCurlyBracketKeyword_16()); 
            match(input,46,FOLLOW_2); 
             after(grammarAccess.getClassDefAccess().getRightCurlyBracketKeyword_16()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group__16__Impl"


    // $ANTLR start "rule__ClassDef__Group_1__0"
    // InternalModel2Blockly.g:2291:1: rule__ClassDef__Group_1__0 : rule__ClassDef__Group_1__0__Impl rule__ClassDef__Group_1__1 ;
    public final void rule__ClassDef__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2295:1: ( rule__ClassDef__Group_1__0__Impl rule__ClassDef__Group_1__1 )
            // InternalModel2Blockly.g:2296:2: rule__ClassDef__Group_1__0__Impl rule__ClassDef__Group_1__1
            {
            pushFollow(FOLLOW_18);
            rule__ClassDef__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ClassDef__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group_1__0"


    // $ANTLR start "rule__ClassDef__Group_1__0__Impl"
    // InternalModel2Blockly.g:2303:1: rule__ClassDef__Group_1__0__Impl : ( ( rule__ClassDef__OutputAssignment_1_0 ) ) ;
    public final void rule__ClassDef__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2307:1: ( ( ( rule__ClassDef__OutputAssignment_1_0 ) ) )
            // InternalModel2Blockly.g:2308:1: ( ( rule__ClassDef__OutputAssignment_1_0 ) )
            {
            // InternalModel2Blockly.g:2308:1: ( ( rule__ClassDef__OutputAssignment_1_0 ) )
            // InternalModel2Blockly.g:2309:2: ( rule__ClassDef__OutputAssignment_1_0 )
            {
             before(grammarAccess.getClassDefAccess().getOutputAssignment_1_0()); 
            // InternalModel2Blockly.g:2310:2: ( rule__ClassDef__OutputAssignment_1_0 )
            // InternalModel2Blockly.g:2310:3: rule__ClassDef__OutputAssignment_1_0
            {
            pushFollow(FOLLOW_2);
            rule__ClassDef__OutputAssignment_1_0();

            state._fsp--;


            }

             after(grammarAccess.getClassDefAccess().getOutputAssignment_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group_1__0__Impl"


    // $ANTLR start "rule__ClassDef__Group_1__1"
    // InternalModel2Blockly.g:2318:1: rule__ClassDef__Group_1__1 : rule__ClassDef__Group_1__1__Impl ;
    public final void rule__ClassDef__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2322:1: ( rule__ClassDef__Group_1__1__Impl )
            // InternalModel2Blockly.g:2323:2: rule__ClassDef__Group_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ClassDef__Group_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group_1__1"


    // $ANTLR start "rule__ClassDef__Group_1__1__Impl"
    // InternalModel2Blockly.g:2329:1: rule__ClassDef__Group_1__1__Impl : ( ( rule__ClassDef__Group_1_1__0 )? ) ;
    public final void rule__ClassDef__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2333:1: ( ( ( rule__ClassDef__Group_1_1__0 )? ) )
            // InternalModel2Blockly.g:2334:1: ( ( rule__ClassDef__Group_1_1__0 )? )
            {
            // InternalModel2Blockly.g:2334:1: ( ( rule__ClassDef__Group_1_1__0 )? )
            // InternalModel2Blockly.g:2335:2: ( rule__ClassDef__Group_1_1__0 )?
            {
             before(grammarAccess.getClassDefAccess().getGroup_1_1()); 
            // InternalModel2Blockly.g:2336:2: ( rule__ClassDef__Group_1_1__0 )?
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( (LA36_0==48) ) {
                alt36=1;
            }
            switch (alt36) {
                case 1 :
                    // InternalModel2Blockly.g:2336:3: rule__ClassDef__Group_1_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ClassDef__Group_1_1__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getClassDefAccess().getGroup_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group_1__1__Impl"


    // $ANTLR start "rule__ClassDef__Group_1_1__0"
    // InternalModel2Blockly.g:2345:1: rule__ClassDef__Group_1_1__0 : rule__ClassDef__Group_1_1__0__Impl rule__ClassDef__Group_1_1__1 ;
    public final void rule__ClassDef__Group_1_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2349:1: ( rule__ClassDef__Group_1_1__0__Impl rule__ClassDef__Group_1_1__1 )
            // InternalModel2Blockly.g:2350:2: rule__ClassDef__Group_1_1__0__Impl rule__ClassDef__Group_1_1__1
            {
            pushFollow(FOLLOW_4);
            rule__ClassDef__Group_1_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ClassDef__Group_1_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group_1_1__0"


    // $ANTLR start "rule__ClassDef__Group_1_1__0__Impl"
    // InternalModel2Blockly.g:2357:1: rule__ClassDef__Group_1_1__0__Impl : ( 'as' ) ;
    public final void rule__ClassDef__Group_1_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2361:1: ( ( 'as' ) )
            // InternalModel2Blockly.g:2362:1: ( 'as' )
            {
            // InternalModel2Blockly.g:2362:1: ( 'as' )
            // InternalModel2Blockly.g:2363:2: 'as'
            {
             before(grammarAccess.getClassDefAccess().getAsKeyword_1_1_0()); 
            match(input,48,FOLLOW_2); 
             after(grammarAccess.getClassDefAccess().getAsKeyword_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group_1_1__0__Impl"


    // $ANTLR start "rule__ClassDef__Group_1_1__1"
    // InternalModel2Blockly.g:2372:1: rule__ClassDef__Group_1_1__1 : rule__ClassDef__Group_1_1__1__Impl ;
    public final void rule__ClassDef__Group_1_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2376:1: ( rule__ClassDef__Group_1_1__1__Impl )
            // InternalModel2Blockly.g:2377:2: rule__ClassDef__Group_1_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ClassDef__Group_1_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group_1_1__1"


    // $ANTLR start "rule__ClassDef__Group_1_1__1__Impl"
    // InternalModel2Blockly.g:2383:1: rule__ClassDef__Group_1_1__1__Impl : ( ( rule__ClassDef__OutputTypeAssignment_1_1_1 ) ) ;
    public final void rule__ClassDef__Group_1_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2387:1: ( ( ( rule__ClassDef__OutputTypeAssignment_1_1_1 ) ) )
            // InternalModel2Blockly.g:2388:1: ( ( rule__ClassDef__OutputTypeAssignment_1_1_1 ) )
            {
            // InternalModel2Blockly.g:2388:1: ( ( rule__ClassDef__OutputTypeAssignment_1_1_1 ) )
            // InternalModel2Blockly.g:2389:2: ( rule__ClassDef__OutputTypeAssignment_1_1_1 )
            {
             before(grammarAccess.getClassDefAccess().getOutputTypeAssignment_1_1_1()); 
            // InternalModel2Blockly.g:2390:2: ( rule__ClassDef__OutputTypeAssignment_1_1_1 )
            // InternalModel2Blockly.g:2390:3: rule__ClassDef__OutputTypeAssignment_1_1_1
            {
            pushFollow(FOLLOW_2);
            rule__ClassDef__OutputTypeAssignment_1_1_1();

            state._fsp--;


            }

             after(grammarAccess.getClassDefAccess().getOutputTypeAssignment_1_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group_1_1__1__Impl"


    // $ANTLR start "rule__ClassDef__Group_4__0"
    // InternalModel2Blockly.g:2399:1: rule__ClassDef__Group_4__0 : rule__ClassDef__Group_4__0__Impl rule__ClassDef__Group_4__1 ;
    public final void rule__ClassDef__Group_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2403:1: ( rule__ClassDef__Group_4__0__Impl rule__ClassDef__Group_4__1 )
            // InternalModel2Blockly.g:2404:2: rule__ClassDef__Group_4__0__Impl rule__ClassDef__Group_4__1
            {
            pushFollow(FOLLOW_4);
            rule__ClassDef__Group_4__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ClassDef__Group_4__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group_4__0"


    // $ANTLR start "rule__ClassDef__Group_4__0__Impl"
    // InternalModel2Blockly.g:2411:1: rule__ClassDef__Group_4__0__Impl : ( 'extends' ) ;
    public final void rule__ClassDef__Group_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2415:1: ( ( 'extends' ) )
            // InternalModel2Blockly.g:2416:1: ( 'extends' )
            {
            // InternalModel2Blockly.g:2416:1: ( 'extends' )
            // InternalModel2Blockly.g:2417:2: 'extends'
            {
             before(grammarAccess.getClassDefAccess().getExtendsKeyword_4_0()); 
            match(input,49,FOLLOW_2); 
             after(grammarAccess.getClassDefAccess().getExtendsKeyword_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group_4__0__Impl"


    // $ANTLR start "rule__ClassDef__Group_4__1"
    // InternalModel2Blockly.g:2426:1: rule__ClassDef__Group_4__1 : rule__ClassDef__Group_4__1__Impl ;
    public final void rule__ClassDef__Group_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2430:1: ( rule__ClassDef__Group_4__1__Impl )
            // InternalModel2Blockly.g:2431:2: rule__ClassDef__Group_4__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ClassDef__Group_4__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group_4__1"


    // $ANTLR start "rule__ClassDef__Group_4__1__Impl"
    // InternalModel2Blockly.g:2437:1: rule__ClassDef__Group_4__1__Impl : ( ( rule__ClassDef__SuperClassAssignment_4_1 ) ) ;
    public final void rule__ClassDef__Group_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2441:1: ( ( ( rule__ClassDef__SuperClassAssignment_4_1 ) ) )
            // InternalModel2Blockly.g:2442:1: ( ( rule__ClassDef__SuperClassAssignment_4_1 ) )
            {
            // InternalModel2Blockly.g:2442:1: ( ( rule__ClassDef__SuperClassAssignment_4_1 ) )
            // InternalModel2Blockly.g:2443:2: ( rule__ClassDef__SuperClassAssignment_4_1 )
            {
             before(grammarAccess.getClassDefAccess().getSuperClassAssignment_4_1()); 
            // InternalModel2Blockly.g:2444:2: ( rule__ClassDef__SuperClassAssignment_4_1 )
            // InternalModel2Blockly.g:2444:3: rule__ClassDef__SuperClassAssignment_4_1
            {
            pushFollow(FOLLOW_2);
            rule__ClassDef__SuperClassAssignment_4_1();

            state._fsp--;


            }

             after(grammarAccess.getClassDefAccess().getSuperClassAssignment_4_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group_4__1__Impl"


    // $ANTLR start "rule__ClassDef__Group_5__0"
    // InternalModel2Blockly.g:2453:1: rule__ClassDef__Group_5__0 : rule__ClassDef__Group_5__0__Impl rule__ClassDef__Group_5__1 ;
    public final void rule__ClassDef__Group_5__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2457:1: ( rule__ClassDef__Group_5__0__Impl rule__ClassDef__Group_5__1 )
            // InternalModel2Blockly.g:2458:2: rule__ClassDef__Group_5__0__Impl rule__ClassDef__Group_5__1
            {
            pushFollow(FOLLOW_4);
            rule__ClassDef__Group_5__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ClassDef__Group_5__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group_5__0"


    // $ANTLR start "rule__ClassDef__Group_5__0__Impl"
    // InternalModel2Blockly.g:2465:1: rule__ClassDef__Group_5__0__Impl : ( 'category' ) ;
    public final void rule__ClassDef__Group_5__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2469:1: ( ( 'category' ) )
            // InternalModel2Blockly.g:2470:1: ( 'category' )
            {
            // InternalModel2Blockly.g:2470:1: ( 'category' )
            // InternalModel2Blockly.g:2471:2: 'category'
            {
             before(grammarAccess.getClassDefAccess().getCategoryKeyword_5_0()); 
            match(input,44,FOLLOW_2); 
             after(grammarAccess.getClassDefAccess().getCategoryKeyword_5_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group_5__0__Impl"


    // $ANTLR start "rule__ClassDef__Group_5__1"
    // InternalModel2Blockly.g:2480:1: rule__ClassDef__Group_5__1 : rule__ClassDef__Group_5__1__Impl ;
    public final void rule__ClassDef__Group_5__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2484:1: ( rule__ClassDef__Group_5__1__Impl )
            // InternalModel2Blockly.g:2485:2: rule__ClassDef__Group_5__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ClassDef__Group_5__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group_5__1"


    // $ANTLR start "rule__ClassDef__Group_5__1__Impl"
    // InternalModel2Blockly.g:2491:1: rule__ClassDef__Group_5__1__Impl : ( ( rule__ClassDef__CategoryAssignment_5_1 ) ) ;
    public final void rule__ClassDef__Group_5__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2495:1: ( ( ( rule__ClassDef__CategoryAssignment_5_1 ) ) )
            // InternalModel2Blockly.g:2496:1: ( ( rule__ClassDef__CategoryAssignment_5_1 ) )
            {
            // InternalModel2Blockly.g:2496:1: ( ( rule__ClassDef__CategoryAssignment_5_1 ) )
            // InternalModel2Blockly.g:2497:2: ( rule__ClassDef__CategoryAssignment_5_1 )
            {
             before(grammarAccess.getClassDefAccess().getCategoryAssignment_5_1()); 
            // InternalModel2Blockly.g:2498:2: ( rule__ClassDef__CategoryAssignment_5_1 )
            // InternalModel2Blockly.g:2498:3: rule__ClassDef__CategoryAssignment_5_1
            {
            pushFollow(FOLLOW_2);
            rule__ClassDef__CategoryAssignment_5_1();

            state._fsp--;


            }

             after(grammarAccess.getClassDefAccess().getCategoryAssignment_5_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group_5__1__Impl"


    // $ANTLR start "rule__ClassDef__Group_6__0"
    // InternalModel2Blockly.g:2507:1: rule__ClassDef__Group_6__0 : rule__ClassDef__Group_6__0__Impl rule__ClassDef__Group_6__1 ;
    public final void rule__ClassDef__Group_6__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2511:1: ( rule__ClassDef__Group_6__0__Impl rule__ClassDef__Group_6__1 )
            // InternalModel2Blockly.g:2512:2: rule__ClassDef__Group_6__0__Impl rule__ClassDef__Group_6__1
            {
            pushFollow(FOLLOW_12);
            rule__ClassDef__Group_6__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ClassDef__Group_6__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group_6__0"


    // $ANTLR start "rule__ClassDef__Group_6__0__Impl"
    // InternalModel2Blockly.g:2519:1: rule__ClassDef__Group_6__0__Impl : ( 'colour' ) ;
    public final void rule__ClassDef__Group_6__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2523:1: ( ( 'colour' ) )
            // InternalModel2Blockly.g:2524:1: ( 'colour' )
            {
            // InternalModel2Blockly.g:2524:1: ( 'colour' )
            // InternalModel2Blockly.g:2525:2: 'colour'
            {
             before(grammarAccess.getClassDefAccess().getColourKeyword_6_0()); 
            match(input,32,FOLLOW_2); 
             after(grammarAccess.getClassDefAccess().getColourKeyword_6_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group_6__0__Impl"


    // $ANTLR start "rule__ClassDef__Group_6__1"
    // InternalModel2Blockly.g:2534:1: rule__ClassDef__Group_6__1 : rule__ClassDef__Group_6__1__Impl ;
    public final void rule__ClassDef__Group_6__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2538:1: ( rule__ClassDef__Group_6__1__Impl )
            // InternalModel2Blockly.g:2539:2: rule__ClassDef__Group_6__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ClassDef__Group_6__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group_6__1"


    // $ANTLR start "rule__ClassDef__Group_6__1__Impl"
    // InternalModel2Blockly.g:2545:1: rule__ClassDef__Group_6__1__Impl : ( ( rule__ClassDef__ColourAssignment_6_1 ) ) ;
    public final void rule__ClassDef__Group_6__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2549:1: ( ( ( rule__ClassDef__ColourAssignment_6_1 ) ) )
            // InternalModel2Blockly.g:2550:1: ( ( rule__ClassDef__ColourAssignment_6_1 ) )
            {
            // InternalModel2Blockly.g:2550:1: ( ( rule__ClassDef__ColourAssignment_6_1 ) )
            // InternalModel2Blockly.g:2551:2: ( rule__ClassDef__ColourAssignment_6_1 )
            {
             before(grammarAccess.getClassDefAccess().getColourAssignment_6_1()); 
            // InternalModel2Blockly.g:2552:2: ( rule__ClassDef__ColourAssignment_6_1 )
            // InternalModel2Blockly.g:2552:3: rule__ClassDef__ColourAssignment_6_1
            {
            pushFollow(FOLLOW_2);
            rule__ClassDef__ColourAssignment_6_1();

            state._fsp--;


            }

             after(grammarAccess.getClassDefAccess().getColourAssignment_6_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group_6__1__Impl"


    // $ANTLR start "rule__ClassDef__Group_7__0"
    // InternalModel2Blockly.g:2561:1: rule__ClassDef__Group_7__0 : rule__ClassDef__Group_7__0__Impl rule__ClassDef__Group_7__1 ;
    public final void rule__ClassDef__Group_7__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2565:1: ( rule__ClassDef__Group_7__0__Impl rule__ClassDef__Group_7__1 )
            // InternalModel2Blockly.g:2566:2: rule__ClassDef__Group_7__0__Impl rule__ClassDef__Group_7__1
            {
            pushFollow(FOLLOW_10);
            rule__ClassDef__Group_7__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ClassDef__Group_7__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group_7__0"


    // $ANTLR start "rule__ClassDef__Group_7__0__Impl"
    // InternalModel2Blockly.g:2573:1: rule__ClassDef__Group_7__0__Impl : ( 'label' ) ;
    public final void rule__ClassDef__Group_7__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2577:1: ( ( 'label' ) )
            // InternalModel2Blockly.g:2578:1: ( 'label' )
            {
            // InternalModel2Blockly.g:2578:1: ( 'label' )
            // InternalModel2Blockly.g:2579:2: 'label'
            {
             before(grammarAccess.getClassDefAccess().getLabelKeyword_7_0()); 
            match(input,33,FOLLOW_2); 
             after(grammarAccess.getClassDefAccess().getLabelKeyword_7_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group_7__0__Impl"


    // $ANTLR start "rule__ClassDef__Group_7__1"
    // InternalModel2Blockly.g:2588:1: rule__ClassDef__Group_7__1 : rule__ClassDef__Group_7__1__Impl ;
    public final void rule__ClassDef__Group_7__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2592:1: ( rule__ClassDef__Group_7__1__Impl )
            // InternalModel2Blockly.g:2593:2: rule__ClassDef__Group_7__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ClassDef__Group_7__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group_7__1"


    // $ANTLR start "rule__ClassDef__Group_7__1__Impl"
    // InternalModel2Blockly.g:2599:1: rule__ClassDef__Group_7__1__Impl : ( ( rule__ClassDef__LabelAssignment_7_1 ) ) ;
    public final void rule__ClassDef__Group_7__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2603:1: ( ( ( rule__ClassDef__LabelAssignment_7_1 ) ) )
            // InternalModel2Blockly.g:2604:1: ( ( rule__ClassDef__LabelAssignment_7_1 ) )
            {
            // InternalModel2Blockly.g:2604:1: ( ( rule__ClassDef__LabelAssignment_7_1 ) )
            // InternalModel2Blockly.g:2605:2: ( rule__ClassDef__LabelAssignment_7_1 )
            {
             before(grammarAccess.getClassDefAccess().getLabelAssignment_7_1()); 
            // InternalModel2Blockly.g:2606:2: ( rule__ClassDef__LabelAssignment_7_1 )
            // InternalModel2Blockly.g:2606:3: rule__ClassDef__LabelAssignment_7_1
            {
            pushFollow(FOLLOW_2);
            rule__ClassDef__LabelAssignment_7_1();

            state._fsp--;


            }

             after(grammarAccess.getClassDefAccess().getLabelAssignment_7_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group_7__1__Impl"


    // $ANTLR start "rule__ClassDef__Group_8__0"
    // InternalModel2Blockly.g:2615:1: rule__ClassDef__Group_8__0 : rule__ClassDef__Group_8__0__Impl rule__ClassDef__Group_8__1 ;
    public final void rule__ClassDef__Group_8__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2619:1: ( rule__ClassDef__Group_8__0__Impl rule__ClassDef__Group_8__1 )
            // InternalModel2Blockly.g:2620:2: rule__ClassDef__Group_8__0__Impl rule__ClassDef__Group_8__1
            {
            pushFollow(FOLLOW_10);
            rule__ClassDef__Group_8__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ClassDef__Group_8__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group_8__0"


    // $ANTLR start "rule__ClassDef__Group_8__0__Impl"
    // InternalModel2Blockly.g:2627:1: rule__ClassDef__Group_8__0__Impl : ( 'message0' ) ;
    public final void rule__ClassDef__Group_8__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2631:1: ( ( 'message0' ) )
            // InternalModel2Blockly.g:2632:1: ( 'message0' )
            {
            // InternalModel2Blockly.g:2632:1: ( 'message0' )
            // InternalModel2Blockly.g:2633:2: 'message0'
            {
             before(grammarAccess.getClassDefAccess().getMessage0Keyword_8_0()); 
            match(input,50,FOLLOW_2); 
             after(grammarAccess.getClassDefAccess().getMessage0Keyword_8_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group_8__0__Impl"


    // $ANTLR start "rule__ClassDef__Group_8__1"
    // InternalModel2Blockly.g:2642:1: rule__ClassDef__Group_8__1 : rule__ClassDef__Group_8__1__Impl ;
    public final void rule__ClassDef__Group_8__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2646:1: ( rule__ClassDef__Group_8__1__Impl )
            // InternalModel2Blockly.g:2647:2: rule__ClassDef__Group_8__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ClassDef__Group_8__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group_8__1"


    // $ANTLR start "rule__ClassDef__Group_8__1__Impl"
    // InternalModel2Blockly.g:2653:1: rule__ClassDef__Group_8__1__Impl : ( ( rule__ClassDef__Message0Assignment_8_1 ) ) ;
    public final void rule__ClassDef__Group_8__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2657:1: ( ( ( rule__ClassDef__Message0Assignment_8_1 ) ) )
            // InternalModel2Blockly.g:2658:1: ( ( rule__ClassDef__Message0Assignment_8_1 ) )
            {
            // InternalModel2Blockly.g:2658:1: ( ( rule__ClassDef__Message0Assignment_8_1 ) )
            // InternalModel2Blockly.g:2659:2: ( rule__ClassDef__Message0Assignment_8_1 )
            {
             before(grammarAccess.getClassDefAccess().getMessage0Assignment_8_1()); 
            // InternalModel2Blockly.g:2660:2: ( rule__ClassDef__Message0Assignment_8_1 )
            // InternalModel2Blockly.g:2660:3: rule__ClassDef__Message0Assignment_8_1
            {
            pushFollow(FOLLOW_2);
            rule__ClassDef__Message0Assignment_8_1();

            state._fsp--;


            }

             after(grammarAccess.getClassDefAccess().getMessage0Assignment_8_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group_8__1__Impl"


    // $ANTLR start "rule__ClassDef__Group_9__0"
    // InternalModel2Blockly.g:2669:1: rule__ClassDef__Group_9__0 : rule__ClassDef__Group_9__0__Impl rule__ClassDef__Group_9__1 ;
    public final void rule__ClassDef__Group_9__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2673:1: ( rule__ClassDef__Group_9__0__Impl rule__ClassDef__Group_9__1 )
            // InternalModel2Blockly.g:2674:2: rule__ClassDef__Group_9__0__Impl rule__ClassDef__Group_9__1
            {
            pushFollow(FOLLOW_10);
            rule__ClassDef__Group_9__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ClassDef__Group_9__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group_9__0"


    // $ANTLR start "rule__ClassDef__Group_9__0__Impl"
    // InternalModel2Blockly.g:2681:1: rule__ClassDef__Group_9__0__Impl : ( 'tooltip' ) ;
    public final void rule__ClassDef__Group_9__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2685:1: ( ( 'tooltip' ) )
            // InternalModel2Blockly.g:2686:1: ( 'tooltip' )
            {
            // InternalModel2Blockly.g:2686:1: ( 'tooltip' )
            // InternalModel2Blockly.g:2687:2: 'tooltip'
            {
             before(grammarAccess.getClassDefAccess().getTooltipKeyword_9_0()); 
            match(input,51,FOLLOW_2); 
             after(grammarAccess.getClassDefAccess().getTooltipKeyword_9_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group_9__0__Impl"


    // $ANTLR start "rule__ClassDef__Group_9__1"
    // InternalModel2Blockly.g:2696:1: rule__ClassDef__Group_9__1 : rule__ClassDef__Group_9__1__Impl ;
    public final void rule__ClassDef__Group_9__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2700:1: ( rule__ClassDef__Group_9__1__Impl )
            // InternalModel2Blockly.g:2701:2: rule__ClassDef__Group_9__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ClassDef__Group_9__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group_9__1"


    // $ANTLR start "rule__ClassDef__Group_9__1__Impl"
    // InternalModel2Blockly.g:2707:1: rule__ClassDef__Group_9__1__Impl : ( ( rule__ClassDef__TooltipAssignment_9_1 ) ) ;
    public final void rule__ClassDef__Group_9__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2711:1: ( ( ( rule__ClassDef__TooltipAssignment_9_1 ) ) )
            // InternalModel2Blockly.g:2712:1: ( ( rule__ClassDef__TooltipAssignment_9_1 ) )
            {
            // InternalModel2Blockly.g:2712:1: ( ( rule__ClassDef__TooltipAssignment_9_1 ) )
            // InternalModel2Blockly.g:2713:2: ( rule__ClassDef__TooltipAssignment_9_1 )
            {
             before(grammarAccess.getClassDefAccess().getTooltipAssignment_9_1()); 
            // InternalModel2Blockly.g:2714:2: ( rule__ClassDef__TooltipAssignment_9_1 )
            // InternalModel2Blockly.g:2714:3: rule__ClassDef__TooltipAssignment_9_1
            {
            pushFollow(FOLLOW_2);
            rule__ClassDef__TooltipAssignment_9_1();

            state._fsp--;


            }

             after(grammarAccess.getClassDefAccess().getTooltipAssignment_9_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group_9__1__Impl"


    // $ANTLR start "rule__ClassDef__Group_10__0"
    // InternalModel2Blockly.g:2723:1: rule__ClassDef__Group_10__0 : rule__ClassDef__Group_10__0__Impl rule__ClassDef__Group_10__1 ;
    public final void rule__ClassDef__Group_10__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2727:1: ( rule__ClassDef__Group_10__0__Impl rule__ClassDef__Group_10__1 )
            // InternalModel2Blockly.g:2728:2: rule__ClassDef__Group_10__0__Impl rule__ClassDef__Group_10__1
            {
            pushFollow(FOLLOW_10);
            rule__ClassDef__Group_10__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ClassDef__Group_10__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group_10__0"


    // $ANTLR start "rule__ClassDef__Group_10__0__Impl"
    // InternalModel2Blockly.g:2735:1: rule__ClassDef__Group_10__0__Impl : ( 'helpUrl' ) ;
    public final void rule__ClassDef__Group_10__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2739:1: ( ( 'helpUrl' ) )
            // InternalModel2Blockly.g:2740:1: ( 'helpUrl' )
            {
            // InternalModel2Blockly.g:2740:1: ( 'helpUrl' )
            // InternalModel2Blockly.g:2741:2: 'helpUrl'
            {
             before(grammarAccess.getClassDefAccess().getHelpUrlKeyword_10_0()); 
            match(input,52,FOLLOW_2); 
             after(grammarAccess.getClassDefAccess().getHelpUrlKeyword_10_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group_10__0__Impl"


    // $ANTLR start "rule__ClassDef__Group_10__1"
    // InternalModel2Blockly.g:2750:1: rule__ClassDef__Group_10__1 : rule__ClassDef__Group_10__1__Impl ;
    public final void rule__ClassDef__Group_10__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2754:1: ( rule__ClassDef__Group_10__1__Impl )
            // InternalModel2Blockly.g:2755:2: rule__ClassDef__Group_10__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ClassDef__Group_10__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group_10__1"


    // $ANTLR start "rule__ClassDef__Group_10__1__Impl"
    // InternalModel2Blockly.g:2761:1: rule__ClassDef__Group_10__1__Impl : ( ( rule__ClassDef__HelpUrlAssignment_10_1 ) ) ;
    public final void rule__ClassDef__Group_10__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2765:1: ( ( ( rule__ClassDef__HelpUrlAssignment_10_1 ) ) )
            // InternalModel2Blockly.g:2766:1: ( ( rule__ClassDef__HelpUrlAssignment_10_1 ) )
            {
            // InternalModel2Blockly.g:2766:1: ( ( rule__ClassDef__HelpUrlAssignment_10_1 ) )
            // InternalModel2Blockly.g:2767:2: ( rule__ClassDef__HelpUrlAssignment_10_1 )
            {
             before(grammarAccess.getClassDefAccess().getHelpUrlAssignment_10_1()); 
            // InternalModel2Blockly.g:2768:2: ( rule__ClassDef__HelpUrlAssignment_10_1 )
            // InternalModel2Blockly.g:2768:3: rule__ClassDef__HelpUrlAssignment_10_1
            {
            pushFollow(FOLLOW_2);
            rule__ClassDef__HelpUrlAssignment_10_1();

            state._fsp--;


            }

             after(grammarAccess.getClassDefAccess().getHelpUrlAssignment_10_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group_10__1__Impl"


    // $ANTLR start "rule__ClassDef__Group_11__0"
    // InternalModel2Blockly.g:2777:1: rule__ClassDef__Group_11__0 : rule__ClassDef__Group_11__0__Impl rule__ClassDef__Group_11__1 ;
    public final void rule__ClassDef__Group_11__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2781:1: ( rule__ClassDef__Group_11__0__Impl rule__ClassDef__Group_11__1 )
            // InternalModel2Blockly.g:2782:2: rule__ClassDef__Group_11__0__Impl rule__ClassDef__Group_11__1
            {
            pushFollow(FOLLOW_19);
            rule__ClassDef__Group_11__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ClassDef__Group_11__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group_11__0"


    // $ANTLR start "rule__ClassDef__Group_11__0__Impl"
    // InternalModel2Blockly.g:2789:1: rule__ClassDef__Group_11__0__Impl : ( 'inputsInline' ) ;
    public final void rule__ClassDef__Group_11__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2793:1: ( ( 'inputsInline' ) )
            // InternalModel2Blockly.g:2794:1: ( 'inputsInline' )
            {
            // InternalModel2Blockly.g:2794:1: ( 'inputsInline' )
            // InternalModel2Blockly.g:2795:2: 'inputsInline'
            {
             before(grammarAccess.getClassDefAccess().getInputsInlineKeyword_11_0()); 
            match(input,53,FOLLOW_2); 
             after(grammarAccess.getClassDefAccess().getInputsInlineKeyword_11_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group_11__0__Impl"


    // $ANTLR start "rule__ClassDef__Group_11__1"
    // InternalModel2Blockly.g:2804:1: rule__ClassDef__Group_11__1 : rule__ClassDef__Group_11__1__Impl ;
    public final void rule__ClassDef__Group_11__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2808:1: ( rule__ClassDef__Group_11__1__Impl )
            // InternalModel2Blockly.g:2809:2: rule__ClassDef__Group_11__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ClassDef__Group_11__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group_11__1"


    // $ANTLR start "rule__ClassDef__Group_11__1__Impl"
    // InternalModel2Blockly.g:2815:1: rule__ClassDef__Group_11__1__Impl : ( ( rule__ClassDef__InputsInlineAssignment_11_1 ) ) ;
    public final void rule__ClassDef__Group_11__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2819:1: ( ( ( rule__ClassDef__InputsInlineAssignment_11_1 ) ) )
            // InternalModel2Blockly.g:2820:1: ( ( rule__ClassDef__InputsInlineAssignment_11_1 ) )
            {
            // InternalModel2Blockly.g:2820:1: ( ( rule__ClassDef__InputsInlineAssignment_11_1 ) )
            // InternalModel2Blockly.g:2821:2: ( rule__ClassDef__InputsInlineAssignment_11_1 )
            {
             before(grammarAccess.getClassDefAccess().getInputsInlineAssignment_11_1()); 
            // InternalModel2Blockly.g:2822:2: ( rule__ClassDef__InputsInlineAssignment_11_1 )
            // InternalModel2Blockly.g:2822:3: rule__ClassDef__InputsInlineAssignment_11_1
            {
            pushFollow(FOLLOW_2);
            rule__ClassDef__InputsInlineAssignment_11_1();

            state._fsp--;


            }

             after(grammarAccess.getClassDefAccess().getInputsInlineAssignment_11_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group_11__1__Impl"


    // $ANTLR start "rule__ClassDef__Group_13__0"
    // InternalModel2Blockly.g:2831:1: rule__ClassDef__Group_13__0 : rule__ClassDef__Group_13__0__Impl rule__ClassDef__Group_13__1 ;
    public final void rule__ClassDef__Group_13__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2835:1: ( rule__ClassDef__Group_13__0__Impl rule__ClassDef__Group_13__1 )
            // InternalModel2Blockly.g:2836:2: rule__ClassDef__Group_13__0__Impl rule__ClassDef__Group_13__1
            {
            pushFollow(FOLLOW_10);
            rule__ClassDef__Group_13__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ClassDef__Group_13__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group_13__0"


    // $ANTLR start "rule__ClassDef__Group_13__0__Impl"
    // InternalModel2Blockly.g:2843:1: rule__ClassDef__Group_13__0__Impl : ( 'code' ) ;
    public final void rule__ClassDef__Group_13__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2847:1: ( ( 'code' ) )
            // InternalModel2Blockly.g:2848:1: ( 'code' )
            {
            // InternalModel2Blockly.g:2848:1: ( 'code' )
            // InternalModel2Blockly.g:2849:2: 'code'
            {
             before(grammarAccess.getClassDefAccess().getCodeKeyword_13_0()); 
            match(input,54,FOLLOW_2); 
             after(grammarAccess.getClassDefAccess().getCodeKeyword_13_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group_13__0__Impl"


    // $ANTLR start "rule__ClassDef__Group_13__1"
    // InternalModel2Blockly.g:2858:1: rule__ClassDef__Group_13__1 : rule__ClassDef__Group_13__1__Impl ;
    public final void rule__ClassDef__Group_13__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2862:1: ( rule__ClassDef__Group_13__1__Impl )
            // InternalModel2Blockly.g:2863:2: rule__ClassDef__Group_13__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ClassDef__Group_13__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group_13__1"


    // $ANTLR start "rule__ClassDef__Group_13__1__Impl"
    // InternalModel2Blockly.g:2869:1: rule__ClassDef__Group_13__1__Impl : ( ( rule__ClassDef__CodeTemplateAssignment_13_1 ) ) ;
    public final void rule__ClassDef__Group_13__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2873:1: ( ( ( rule__ClassDef__CodeTemplateAssignment_13_1 ) ) )
            // InternalModel2Blockly.g:2874:1: ( ( rule__ClassDef__CodeTemplateAssignment_13_1 ) )
            {
            // InternalModel2Blockly.g:2874:1: ( ( rule__ClassDef__CodeTemplateAssignment_13_1 ) )
            // InternalModel2Blockly.g:2875:2: ( rule__ClassDef__CodeTemplateAssignment_13_1 )
            {
             before(grammarAccess.getClassDefAccess().getCodeTemplateAssignment_13_1()); 
            // InternalModel2Blockly.g:2876:2: ( rule__ClassDef__CodeTemplateAssignment_13_1 )
            // InternalModel2Blockly.g:2876:3: rule__ClassDef__CodeTemplateAssignment_13_1
            {
            pushFollow(FOLLOW_2);
            rule__ClassDef__CodeTemplateAssignment_13_1();

            state._fsp--;


            }

             after(grammarAccess.getClassDefAccess().getCodeTemplateAssignment_13_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Group_13__1__Impl"


    // $ANTLR start "rule__Attribute__Group__0"
    // InternalModel2Blockly.g:2885:1: rule__Attribute__Group__0 : rule__Attribute__Group__0__Impl rule__Attribute__Group__1 ;
    public final void rule__Attribute__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2889:1: ( rule__Attribute__Group__0__Impl rule__Attribute__Group__1 )
            // InternalModel2Blockly.g:2890:2: rule__Attribute__Group__0__Impl rule__Attribute__Group__1
            {
            pushFollow(FOLLOW_4);
            rule__Attribute__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Attribute__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group__0"


    // $ANTLR start "rule__Attribute__Group__0__Impl"
    // InternalModel2Blockly.g:2897:1: rule__Attribute__Group__0__Impl : ( 'attribute' ) ;
    public final void rule__Attribute__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2901:1: ( ( 'attribute' ) )
            // InternalModel2Blockly.g:2902:1: ( 'attribute' )
            {
            // InternalModel2Blockly.g:2902:1: ( 'attribute' )
            // InternalModel2Blockly.g:2903:2: 'attribute'
            {
             before(grammarAccess.getAttributeAccess().getAttributeKeyword_0()); 
            match(input,55,FOLLOW_2); 
             after(grammarAccess.getAttributeAccess().getAttributeKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group__0__Impl"


    // $ANTLR start "rule__Attribute__Group__1"
    // InternalModel2Blockly.g:2912:1: rule__Attribute__Group__1 : rule__Attribute__Group__1__Impl rule__Attribute__Group__2 ;
    public final void rule__Attribute__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2916:1: ( rule__Attribute__Group__1__Impl rule__Attribute__Group__2 )
            // InternalModel2Blockly.g:2917:2: rule__Attribute__Group__1__Impl rule__Attribute__Group__2
            {
            pushFollow(FOLLOW_20);
            rule__Attribute__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Attribute__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group__1"


    // $ANTLR start "rule__Attribute__Group__1__Impl"
    // InternalModel2Blockly.g:2924:1: rule__Attribute__Group__1__Impl : ( ( rule__Attribute__NameAssignment_1 ) ) ;
    public final void rule__Attribute__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2928:1: ( ( ( rule__Attribute__NameAssignment_1 ) ) )
            // InternalModel2Blockly.g:2929:1: ( ( rule__Attribute__NameAssignment_1 ) )
            {
            // InternalModel2Blockly.g:2929:1: ( ( rule__Attribute__NameAssignment_1 ) )
            // InternalModel2Blockly.g:2930:2: ( rule__Attribute__NameAssignment_1 )
            {
             before(grammarAccess.getAttributeAccess().getNameAssignment_1()); 
            // InternalModel2Blockly.g:2931:2: ( rule__Attribute__NameAssignment_1 )
            // InternalModel2Blockly.g:2931:3: rule__Attribute__NameAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__Attribute__NameAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getAttributeAccess().getNameAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group__1__Impl"


    // $ANTLR start "rule__Attribute__Group__2"
    // InternalModel2Blockly.g:2939:1: rule__Attribute__Group__2 : rule__Attribute__Group__2__Impl rule__Attribute__Group__3 ;
    public final void rule__Attribute__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2943:1: ( rule__Attribute__Group__2__Impl rule__Attribute__Group__3 )
            // InternalModel2Blockly.g:2944:2: rule__Attribute__Group__2__Impl rule__Attribute__Group__3
            {
            pushFollow(FOLLOW_21);
            rule__Attribute__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Attribute__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group__2"


    // $ANTLR start "rule__Attribute__Group__2__Impl"
    // InternalModel2Blockly.g:2951:1: rule__Attribute__Group__2__Impl : ( ':' ) ;
    public final void rule__Attribute__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2955:1: ( ( ':' ) )
            // InternalModel2Blockly.g:2956:1: ( ':' )
            {
            // InternalModel2Blockly.g:2956:1: ( ':' )
            // InternalModel2Blockly.g:2957:2: ':'
            {
             before(grammarAccess.getAttributeAccess().getColonKeyword_2()); 
            match(input,56,FOLLOW_2); 
             after(grammarAccess.getAttributeAccess().getColonKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group__2__Impl"


    // $ANTLR start "rule__Attribute__Group__3"
    // InternalModel2Blockly.g:2966:1: rule__Attribute__Group__3 : rule__Attribute__Group__3__Impl rule__Attribute__Group__4 ;
    public final void rule__Attribute__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2970:1: ( rule__Attribute__Group__3__Impl rule__Attribute__Group__4 )
            // InternalModel2Blockly.g:2971:2: rule__Attribute__Group__3__Impl rule__Attribute__Group__4
            {
            pushFollow(FOLLOW_22);
            rule__Attribute__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Attribute__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group__3"


    // $ANTLR start "rule__Attribute__Group__3__Impl"
    // InternalModel2Blockly.g:2978:1: rule__Attribute__Group__3__Impl : ( ( rule__Attribute__TypeAssignment_3 ) ) ;
    public final void rule__Attribute__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2982:1: ( ( ( rule__Attribute__TypeAssignment_3 ) ) )
            // InternalModel2Blockly.g:2983:1: ( ( rule__Attribute__TypeAssignment_3 ) )
            {
            // InternalModel2Blockly.g:2983:1: ( ( rule__Attribute__TypeAssignment_3 ) )
            // InternalModel2Blockly.g:2984:2: ( rule__Attribute__TypeAssignment_3 )
            {
             before(grammarAccess.getAttributeAccess().getTypeAssignment_3()); 
            // InternalModel2Blockly.g:2985:2: ( rule__Attribute__TypeAssignment_3 )
            // InternalModel2Blockly.g:2985:3: rule__Attribute__TypeAssignment_3
            {
            pushFollow(FOLLOW_2);
            rule__Attribute__TypeAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getAttributeAccess().getTypeAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group__3__Impl"


    // $ANTLR start "rule__Attribute__Group__4"
    // InternalModel2Blockly.g:2993:1: rule__Attribute__Group__4 : rule__Attribute__Group__4__Impl rule__Attribute__Group__5 ;
    public final void rule__Attribute__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:2997:1: ( rule__Attribute__Group__4__Impl rule__Attribute__Group__5 )
            // InternalModel2Blockly.g:2998:2: rule__Attribute__Group__4__Impl rule__Attribute__Group__5
            {
            pushFollow(FOLLOW_22);
            rule__Attribute__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Attribute__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group__4"


    // $ANTLR start "rule__Attribute__Group__4__Impl"
    // InternalModel2Blockly.g:3005:1: rule__Attribute__Group__4__Impl : ( ( rule__Attribute__CardinalityAssignment_4 )? ) ;
    public final void rule__Attribute__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3009:1: ( ( ( rule__Attribute__CardinalityAssignment_4 )? ) )
            // InternalModel2Blockly.g:3010:1: ( ( rule__Attribute__CardinalityAssignment_4 )? )
            {
            // InternalModel2Blockly.g:3010:1: ( ( rule__Attribute__CardinalityAssignment_4 )? )
            // InternalModel2Blockly.g:3011:2: ( rule__Attribute__CardinalityAssignment_4 )?
            {
             before(grammarAccess.getAttributeAccess().getCardinalityAssignment_4()); 
            // InternalModel2Blockly.g:3012:2: ( rule__Attribute__CardinalityAssignment_4 )?
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( (LA37_0==64) ) {
                alt37=1;
            }
            switch (alt37) {
                case 1 :
                    // InternalModel2Blockly.g:3012:3: rule__Attribute__CardinalityAssignment_4
                    {
                    pushFollow(FOLLOW_2);
                    rule__Attribute__CardinalityAssignment_4();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getAttributeAccess().getCardinalityAssignment_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group__4__Impl"


    // $ANTLR start "rule__Attribute__Group__5"
    // InternalModel2Blockly.g:3020:1: rule__Attribute__Group__5 : rule__Attribute__Group__5__Impl rule__Attribute__Group__6 ;
    public final void rule__Attribute__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3024:1: ( rule__Attribute__Group__5__Impl rule__Attribute__Group__6 )
            // InternalModel2Blockly.g:3025:2: rule__Attribute__Group__5__Impl rule__Attribute__Group__6
            {
            pushFollow(FOLLOW_22);
            rule__Attribute__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Attribute__Group__6();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group__5"


    // $ANTLR start "rule__Attribute__Group__5__Impl"
    // InternalModel2Blockly.g:3032:1: rule__Attribute__Group__5__Impl : ( ( rule__Attribute__Group_5__0 )? ) ;
    public final void rule__Attribute__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3036:1: ( ( ( rule__Attribute__Group_5__0 )? ) )
            // InternalModel2Blockly.g:3037:1: ( ( rule__Attribute__Group_5__0 )? )
            {
            // InternalModel2Blockly.g:3037:1: ( ( rule__Attribute__Group_5__0 )? )
            // InternalModel2Blockly.g:3038:2: ( rule__Attribute__Group_5__0 )?
            {
             before(grammarAccess.getAttributeAccess().getGroup_5()); 
            // InternalModel2Blockly.g:3039:2: ( rule__Attribute__Group_5__0 )?
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==25) ) {
                alt38=1;
            }
            switch (alt38) {
                case 1 :
                    // InternalModel2Blockly.g:3039:3: rule__Attribute__Group_5__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Attribute__Group_5__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getAttributeAccess().getGroup_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group__5__Impl"


    // $ANTLR start "rule__Attribute__Group__6"
    // InternalModel2Blockly.g:3047:1: rule__Attribute__Group__6 : rule__Attribute__Group__6__Impl rule__Attribute__Group__7 ;
    public final void rule__Attribute__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3051:1: ( rule__Attribute__Group__6__Impl rule__Attribute__Group__7 )
            // InternalModel2Blockly.g:3052:2: rule__Attribute__Group__6__Impl rule__Attribute__Group__7
            {
            pushFollow(FOLLOW_22);
            rule__Attribute__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Attribute__Group__7();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group__6"


    // $ANTLR start "rule__Attribute__Group__6__Impl"
    // InternalModel2Blockly.g:3059:1: rule__Attribute__Group__6__Impl : ( ( rule__Attribute__Group_6__0 )? ) ;
    public final void rule__Attribute__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3063:1: ( ( ( rule__Attribute__Group_6__0 )? ) )
            // InternalModel2Blockly.g:3064:1: ( ( rule__Attribute__Group_6__0 )? )
            {
            // InternalModel2Blockly.g:3064:1: ( ( rule__Attribute__Group_6__0 )? )
            // InternalModel2Blockly.g:3065:2: ( rule__Attribute__Group_6__0 )?
            {
             before(grammarAccess.getAttributeAccess().getGroup_6()); 
            // InternalModel2Blockly.g:3066:2: ( rule__Attribute__Group_6__0 )?
            int alt39=2;
            int LA39_0 = input.LA(1);

            if ( (LA39_0==57) ) {
                alt39=1;
            }
            switch (alt39) {
                case 1 :
                    // InternalModel2Blockly.g:3066:3: rule__Attribute__Group_6__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Attribute__Group_6__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getAttributeAccess().getGroup_6()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group__6__Impl"


    // $ANTLR start "rule__Attribute__Group__7"
    // InternalModel2Blockly.g:3074:1: rule__Attribute__Group__7 : rule__Attribute__Group__7__Impl rule__Attribute__Group__8 ;
    public final void rule__Attribute__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3078:1: ( rule__Attribute__Group__7__Impl rule__Attribute__Group__8 )
            // InternalModel2Blockly.g:3079:2: rule__Attribute__Group__7__Impl rule__Attribute__Group__8
            {
            pushFollow(FOLLOW_22);
            rule__Attribute__Group__7__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Attribute__Group__8();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group__7"


    // $ANTLR start "rule__Attribute__Group__7__Impl"
    // InternalModel2Blockly.g:3086:1: rule__Attribute__Group__7__Impl : ( ( rule__Attribute__Group_7__0 )? ) ;
    public final void rule__Attribute__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3090:1: ( ( ( rule__Attribute__Group_7__0 )? ) )
            // InternalModel2Blockly.g:3091:1: ( ( rule__Attribute__Group_7__0 )? )
            {
            // InternalModel2Blockly.g:3091:1: ( ( rule__Attribute__Group_7__0 )? )
            // InternalModel2Blockly.g:3092:2: ( rule__Attribute__Group_7__0 )?
            {
             before(grammarAccess.getAttributeAccess().getGroup_7()); 
            // InternalModel2Blockly.g:3093:2: ( rule__Attribute__Group_7__0 )?
            int alt40=2;
            int LA40_0 = input.LA(1);

            if ( (LA40_0==58) ) {
                alt40=1;
            }
            switch (alt40) {
                case 1 :
                    // InternalModel2Blockly.g:3093:3: rule__Attribute__Group_7__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Attribute__Group_7__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getAttributeAccess().getGroup_7()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group__7__Impl"


    // $ANTLR start "rule__Attribute__Group__8"
    // InternalModel2Blockly.g:3101:1: rule__Attribute__Group__8 : rule__Attribute__Group__8__Impl rule__Attribute__Group__9 ;
    public final void rule__Attribute__Group__8() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3105:1: ( rule__Attribute__Group__8__Impl rule__Attribute__Group__9 )
            // InternalModel2Blockly.g:3106:2: rule__Attribute__Group__8__Impl rule__Attribute__Group__9
            {
            pushFollow(FOLLOW_22);
            rule__Attribute__Group__8__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Attribute__Group__9();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group__8"


    // $ANTLR start "rule__Attribute__Group__8__Impl"
    // InternalModel2Blockly.g:3113:1: rule__Attribute__Group__8__Impl : ( ( rule__Attribute__Group_8__0 )? ) ;
    public final void rule__Attribute__Group__8__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3117:1: ( ( ( rule__Attribute__Group_8__0 )? ) )
            // InternalModel2Blockly.g:3118:1: ( ( rule__Attribute__Group_8__0 )? )
            {
            // InternalModel2Blockly.g:3118:1: ( ( rule__Attribute__Group_8__0 )? )
            // InternalModel2Blockly.g:3119:2: ( rule__Attribute__Group_8__0 )?
            {
             before(grammarAccess.getAttributeAccess().getGroup_8()); 
            // InternalModel2Blockly.g:3120:2: ( rule__Attribute__Group_8__0 )?
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==59) ) {
                alt41=1;
            }
            switch (alt41) {
                case 1 :
                    // InternalModel2Blockly.g:3120:3: rule__Attribute__Group_8__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Attribute__Group_8__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getAttributeAccess().getGroup_8()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group__8__Impl"


    // $ANTLR start "rule__Attribute__Group__9"
    // InternalModel2Blockly.g:3128:1: rule__Attribute__Group__9 : rule__Attribute__Group__9__Impl rule__Attribute__Group__10 ;
    public final void rule__Attribute__Group__9() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3132:1: ( rule__Attribute__Group__9__Impl rule__Attribute__Group__10 )
            // InternalModel2Blockly.g:3133:2: rule__Attribute__Group__9__Impl rule__Attribute__Group__10
            {
            pushFollow(FOLLOW_22);
            rule__Attribute__Group__9__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Attribute__Group__10();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group__9"


    // $ANTLR start "rule__Attribute__Group__9__Impl"
    // InternalModel2Blockly.g:3140:1: rule__Attribute__Group__9__Impl : ( ( rule__Attribute__Group_9__0 )? ) ;
    public final void rule__Attribute__Group__9__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3144:1: ( ( ( rule__Attribute__Group_9__0 )? ) )
            // InternalModel2Blockly.g:3145:1: ( ( rule__Attribute__Group_9__0 )? )
            {
            // InternalModel2Blockly.g:3145:1: ( ( rule__Attribute__Group_9__0 )? )
            // InternalModel2Blockly.g:3146:2: ( rule__Attribute__Group_9__0 )?
            {
             before(grammarAccess.getAttributeAccess().getGroup_9()); 
            // InternalModel2Blockly.g:3147:2: ( rule__Attribute__Group_9__0 )?
            int alt42=2;
            int LA42_0 = input.LA(1);

            if ( (LA42_0==60) ) {
                alt42=1;
            }
            switch (alt42) {
                case 1 :
                    // InternalModel2Blockly.g:3147:3: rule__Attribute__Group_9__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Attribute__Group_9__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getAttributeAccess().getGroup_9()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group__9__Impl"


    // $ANTLR start "rule__Attribute__Group__10"
    // InternalModel2Blockly.g:3155:1: rule__Attribute__Group__10 : rule__Attribute__Group__10__Impl rule__Attribute__Group__11 ;
    public final void rule__Attribute__Group__10() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3159:1: ( rule__Attribute__Group__10__Impl rule__Attribute__Group__11 )
            // InternalModel2Blockly.g:3160:2: rule__Attribute__Group__10__Impl rule__Attribute__Group__11
            {
            pushFollow(FOLLOW_22);
            rule__Attribute__Group__10__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Attribute__Group__11();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group__10"


    // $ANTLR start "rule__Attribute__Group__10__Impl"
    // InternalModel2Blockly.g:3167:1: rule__Attribute__Group__10__Impl : ( ( rule__Attribute__Group_10__0 )? ) ;
    public final void rule__Attribute__Group__10__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3171:1: ( ( ( rule__Attribute__Group_10__0 )? ) )
            // InternalModel2Blockly.g:3172:1: ( ( rule__Attribute__Group_10__0 )? )
            {
            // InternalModel2Blockly.g:3172:1: ( ( rule__Attribute__Group_10__0 )? )
            // InternalModel2Blockly.g:3173:2: ( rule__Attribute__Group_10__0 )?
            {
             before(grammarAccess.getAttributeAccess().getGroup_10()); 
            // InternalModel2Blockly.g:3174:2: ( rule__Attribute__Group_10__0 )?
            int alt43=2;
            int LA43_0 = input.LA(1);

            if ( (LA43_0==61) ) {
                alt43=1;
            }
            switch (alt43) {
                case 1 :
                    // InternalModel2Blockly.g:3174:3: rule__Attribute__Group_10__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Attribute__Group_10__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getAttributeAccess().getGroup_10()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group__10__Impl"


    // $ANTLR start "rule__Attribute__Group__11"
    // InternalModel2Blockly.g:3182:1: rule__Attribute__Group__11 : rule__Attribute__Group__11__Impl rule__Attribute__Group__12 ;
    public final void rule__Attribute__Group__11() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3186:1: ( rule__Attribute__Group__11__Impl rule__Attribute__Group__12 )
            // InternalModel2Blockly.g:3187:2: rule__Attribute__Group__11__Impl rule__Attribute__Group__12
            {
            pushFollow(FOLLOW_22);
            rule__Attribute__Group__11__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Attribute__Group__12();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group__11"


    // $ANTLR start "rule__Attribute__Group__11__Impl"
    // InternalModel2Blockly.g:3194:1: rule__Attribute__Group__11__Impl : ( ( rule__Attribute__Group_11__0 )? ) ;
    public final void rule__Attribute__Group__11__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3198:1: ( ( ( rule__Attribute__Group_11__0 )? ) )
            // InternalModel2Blockly.g:3199:1: ( ( rule__Attribute__Group_11__0 )? )
            {
            // InternalModel2Blockly.g:3199:1: ( ( rule__Attribute__Group_11__0 )? )
            // InternalModel2Blockly.g:3200:2: ( rule__Attribute__Group_11__0 )?
            {
             before(grammarAccess.getAttributeAccess().getGroup_11()); 
            // InternalModel2Blockly.g:3201:2: ( rule__Attribute__Group_11__0 )?
            int alt44=2;
            int LA44_0 = input.LA(1);

            if ( (LA44_0==62) ) {
                alt44=1;
            }
            switch (alt44) {
                case 1 :
                    // InternalModel2Blockly.g:3201:3: rule__Attribute__Group_11__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Attribute__Group_11__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getAttributeAccess().getGroup_11()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group__11__Impl"


    // $ANTLR start "rule__Attribute__Group__12"
    // InternalModel2Blockly.g:3209:1: rule__Attribute__Group__12 : rule__Attribute__Group__12__Impl rule__Attribute__Group__13 ;
    public final void rule__Attribute__Group__12() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3213:1: ( rule__Attribute__Group__12__Impl rule__Attribute__Group__13 )
            // InternalModel2Blockly.g:3214:2: rule__Attribute__Group__12__Impl rule__Attribute__Group__13
            {
            pushFollow(FOLLOW_22);
            rule__Attribute__Group__12__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Attribute__Group__13();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group__12"


    // $ANTLR start "rule__Attribute__Group__12__Impl"
    // InternalModel2Blockly.g:3221:1: rule__Attribute__Group__12__Impl : ( ( rule__Attribute__RequiredAssignment_12 )? ) ;
    public final void rule__Attribute__Group__12__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3225:1: ( ( ( rule__Attribute__RequiredAssignment_12 )? ) )
            // InternalModel2Blockly.g:3226:1: ( ( rule__Attribute__RequiredAssignment_12 )? )
            {
            // InternalModel2Blockly.g:3226:1: ( ( rule__Attribute__RequiredAssignment_12 )? )
            // InternalModel2Blockly.g:3227:2: ( rule__Attribute__RequiredAssignment_12 )?
            {
             before(grammarAccess.getAttributeAccess().getRequiredAssignment_12()); 
            // InternalModel2Blockly.g:3228:2: ( rule__Attribute__RequiredAssignment_12 )?
            int alt45=2;
            int LA45_0 = input.LA(1);

            if ( (LA45_0==92) ) {
                alt45=1;
            }
            switch (alt45) {
                case 1 :
                    // InternalModel2Blockly.g:3228:3: rule__Attribute__RequiredAssignment_12
                    {
                    pushFollow(FOLLOW_2);
                    rule__Attribute__RequiredAssignment_12();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getAttributeAccess().getRequiredAssignment_12()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group__12__Impl"


    // $ANTLR start "rule__Attribute__Group__13"
    // InternalModel2Blockly.g:3236:1: rule__Attribute__Group__13 : rule__Attribute__Group__13__Impl rule__Attribute__Group__14 ;
    public final void rule__Attribute__Group__13() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3240:1: ( rule__Attribute__Group__13__Impl rule__Attribute__Group__14 )
            // InternalModel2Blockly.g:3241:2: rule__Attribute__Group__13__Impl rule__Attribute__Group__14
            {
            pushFollow(FOLLOW_22);
            rule__Attribute__Group__13__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Attribute__Group__14();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group__13"


    // $ANTLR start "rule__Attribute__Group__13__Impl"
    // InternalModel2Blockly.g:3248:1: rule__Attribute__Group__13__Impl : ( ( rule__Attribute__IdAssignment_13 )? ) ;
    public final void rule__Attribute__Group__13__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3252:1: ( ( ( rule__Attribute__IdAssignment_13 )? ) )
            // InternalModel2Blockly.g:3253:1: ( ( rule__Attribute__IdAssignment_13 )? )
            {
            // InternalModel2Blockly.g:3253:1: ( ( rule__Attribute__IdAssignment_13 )? )
            // InternalModel2Blockly.g:3254:2: ( rule__Attribute__IdAssignment_13 )?
            {
             before(grammarAccess.getAttributeAccess().getIdAssignment_13()); 
            // InternalModel2Blockly.g:3255:2: ( rule__Attribute__IdAssignment_13 )?
            int alt46=2;
            int LA46_0 = input.LA(1);

            if ( (LA46_0==93) ) {
                alt46=1;
            }
            switch (alt46) {
                case 1 :
                    // InternalModel2Blockly.g:3255:3: rule__Attribute__IdAssignment_13
                    {
                    pushFollow(FOLLOW_2);
                    rule__Attribute__IdAssignment_13();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getAttributeAccess().getIdAssignment_13()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group__13__Impl"


    // $ANTLR start "rule__Attribute__Group__14"
    // InternalModel2Blockly.g:3263:1: rule__Attribute__Group__14 : rule__Attribute__Group__14__Impl rule__Attribute__Group__15 ;
    public final void rule__Attribute__Group__14() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3267:1: ( rule__Attribute__Group__14__Impl rule__Attribute__Group__15 )
            // InternalModel2Blockly.g:3268:2: rule__Attribute__Group__14__Impl rule__Attribute__Group__15
            {
            pushFollow(FOLLOW_22);
            rule__Attribute__Group__14__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Attribute__Group__15();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group__14"


    // $ANTLR start "rule__Attribute__Group__14__Impl"
    // InternalModel2Blockly.g:3275:1: rule__Attribute__Group__14__Impl : ( ( rule__Attribute__UniqueAssignment_14 )? ) ;
    public final void rule__Attribute__Group__14__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3279:1: ( ( ( rule__Attribute__UniqueAssignment_14 )? ) )
            // InternalModel2Blockly.g:3280:1: ( ( rule__Attribute__UniqueAssignment_14 )? )
            {
            // InternalModel2Blockly.g:3280:1: ( ( rule__Attribute__UniqueAssignment_14 )? )
            // InternalModel2Blockly.g:3281:2: ( rule__Attribute__UniqueAssignment_14 )?
            {
             before(grammarAccess.getAttributeAccess().getUniqueAssignment_14()); 
            // InternalModel2Blockly.g:3282:2: ( rule__Attribute__UniqueAssignment_14 )?
            int alt47=2;
            int LA47_0 = input.LA(1);

            if ( (LA47_0==94) ) {
                alt47=1;
            }
            switch (alt47) {
                case 1 :
                    // InternalModel2Blockly.g:3282:3: rule__Attribute__UniqueAssignment_14
                    {
                    pushFollow(FOLLOW_2);
                    rule__Attribute__UniqueAssignment_14();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getAttributeAccess().getUniqueAssignment_14()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group__14__Impl"


    // $ANTLR start "rule__Attribute__Group__15"
    // InternalModel2Blockly.g:3290:1: rule__Attribute__Group__15 : rule__Attribute__Group__15__Impl rule__Attribute__Group__16 ;
    public final void rule__Attribute__Group__15() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3294:1: ( rule__Attribute__Group__15__Impl rule__Attribute__Group__16 )
            // InternalModel2Blockly.g:3295:2: rule__Attribute__Group__15__Impl rule__Attribute__Group__16
            {
            pushFollow(FOLLOW_22);
            rule__Attribute__Group__15__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Attribute__Group__16();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group__15"


    // $ANTLR start "rule__Attribute__Group__15__Impl"
    // InternalModel2Blockly.g:3302:1: rule__Attribute__Group__15__Impl : ( ( rule__Attribute__NonUniqueAssignment_15 )? ) ;
    public final void rule__Attribute__Group__15__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3306:1: ( ( ( rule__Attribute__NonUniqueAssignment_15 )? ) )
            // InternalModel2Blockly.g:3307:1: ( ( rule__Attribute__NonUniqueAssignment_15 )? )
            {
            // InternalModel2Blockly.g:3307:1: ( ( rule__Attribute__NonUniqueAssignment_15 )? )
            // InternalModel2Blockly.g:3308:2: ( rule__Attribute__NonUniqueAssignment_15 )?
            {
             before(grammarAccess.getAttributeAccess().getNonUniqueAssignment_15()); 
            // InternalModel2Blockly.g:3309:2: ( rule__Attribute__NonUniqueAssignment_15 )?
            int alt48=2;
            int LA48_0 = input.LA(1);

            if ( (LA48_0==95) ) {
                alt48=1;
            }
            switch (alt48) {
                case 1 :
                    // InternalModel2Blockly.g:3309:3: rule__Attribute__NonUniqueAssignment_15
                    {
                    pushFollow(FOLLOW_2);
                    rule__Attribute__NonUniqueAssignment_15();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getAttributeAccess().getNonUniqueAssignment_15()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group__15__Impl"


    // $ANTLR start "rule__Attribute__Group__16"
    // InternalModel2Blockly.g:3317:1: rule__Attribute__Group__16 : rule__Attribute__Group__16__Impl rule__Attribute__Group__17 ;
    public final void rule__Attribute__Group__16() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3321:1: ( rule__Attribute__Group__16__Impl rule__Attribute__Group__17 )
            // InternalModel2Blockly.g:3322:2: rule__Attribute__Group__16__Impl rule__Attribute__Group__17
            {
            pushFollow(FOLLOW_22);
            rule__Attribute__Group__16__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Attribute__Group__17();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group__16"


    // $ANTLR start "rule__Attribute__Group__16__Impl"
    // InternalModel2Blockly.g:3329:1: rule__Attribute__Group__16__Impl : ( ( rule__Attribute__OrderedAssignment_16 )? ) ;
    public final void rule__Attribute__Group__16__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3333:1: ( ( ( rule__Attribute__OrderedAssignment_16 )? ) )
            // InternalModel2Blockly.g:3334:1: ( ( rule__Attribute__OrderedAssignment_16 )? )
            {
            // InternalModel2Blockly.g:3334:1: ( ( rule__Attribute__OrderedAssignment_16 )? )
            // InternalModel2Blockly.g:3335:2: ( rule__Attribute__OrderedAssignment_16 )?
            {
             before(grammarAccess.getAttributeAccess().getOrderedAssignment_16()); 
            // InternalModel2Blockly.g:3336:2: ( rule__Attribute__OrderedAssignment_16 )?
            int alt49=2;
            int LA49_0 = input.LA(1);

            if ( (LA49_0==96) ) {
                alt49=1;
            }
            switch (alt49) {
                case 1 :
                    // InternalModel2Blockly.g:3336:3: rule__Attribute__OrderedAssignment_16
                    {
                    pushFollow(FOLLOW_2);
                    rule__Attribute__OrderedAssignment_16();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getAttributeAccess().getOrderedAssignment_16()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group__16__Impl"


    // $ANTLR start "rule__Attribute__Group__17"
    // InternalModel2Blockly.g:3344:1: rule__Attribute__Group__17 : rule__Attribute__Group__17__Impl rule__Attribute__Group__18 ;
    public final void rule__Attribute__Group__17() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3348:1: ( rule__Attribute__Group__17__Impl rule__Attribute__Group__18 )
            // InternalModel2Blockly.g:3349:2: rule__Attribute__Group__17__Impl rule__Attribute__Group__18
            {
            pushFollow(FOLLOW_22);
            rule__Attribute__Group__17__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Attribute__Group__18();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group__17"


    // $ANTLR start "rule__Attribute__Group__17__Impl"
    // InternalModel2Blockly.g:3356:1: rule__Attribute__Group__17__Impl : ( ( rule__Attribute__UnorderedAssignment_17 )? ) ;
    public final void rule__Attribute__Group__17__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3360:1: ( ( ( rule__Attribute__UnorderedAssignment_17 )? ) )
            // InternalModel2Blockly.g:3361:1: ( ( rule__Attribute__UnorderedAssignment_17 )? )
            {
            // InternalModel2Blockly.g:3361:1: ( ( rule__Attribute__UnorderedAssignment_17 )? )
            // InternalModel2Blockly.g:3362:2: ( rule__Attribute__UnorderedAssignment_17 )?
            {
             before(grammarAccess.getAttributeAccess().getUnorderedAssignment_17()); 
            // InternalModel2Blockly.g:3363:2: ( rule__Attribute__UnorderedAssignment_17 )?
            int alt50=2;
            int LA50_0 = input.LA(1);

            if ( (LA50_0==97) ) {
                alt50=1;
            }
            switch (alt50) {
                case 1 :
                    // InternalModel2Blockly.g:3363:3: rule__Attribute__UnorderedAssignment_17
                    {
                    pushFollow(FOLLOW_2);
                    rule__Attribute__UnorderedAssignment_17();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getAttributeAccess().getUnorderedAssignment_17()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group__17__Impl"


    // $ANTLR start "rule__Attribute__Group__18"
    // InternalModel2Blockly.g:3371:1: rule__Attribute__Group__18 : rule__Attribute__Group__18__Impl ;
    public final void rule__Attribute__Group__18() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3375:1: ( rule__Attribute__Group__18__Impl )
            // InternalModel2Blockly.g:3376:2: rule__Attribute__Group__18__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Attribute__Group__18__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group__18"


    // $ANTLR start "rule__Attribute__Group__18__Impl"
    // InternalModel2Blockly.g:3382:1: rule__Attribute__Group__18__Impl : ( ( rule__Attribute__UiAssignment_18 )? ) ;
    public final void rule__Attribute__Group__18__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3386:1: ( ( ( rule__Attribute__UiAssignment_18 )? ) )
            // InternalModel2Blockly.g:3387:1: ( ( rule__Attribute__UiAssignment_18 )? )
            {
            // InternalModel2Blockly.g:3387:1: ( ( rule__Attribute__UiAssignment_18 )? )
            // InternalModel2Blockly.g:3388:2: ( rule__Attribute__UiAssignment_18 )?
            {
             before(grammarAccess.getAttributeAccess().getUiAssignment_18()); 
            // InternalModel2Blockly.g:3389:2: ( rule__Attribute__UiAssignment_18 )?
            int alt51=2;
            int LA51_0 = input.LA(1);

            if ( ((LA51_0>=71 && LA51_0<=78)||(LA51_0>=98 && LA51_0<=99)) ) {
                alt51=1;
            }
            switch (alt51) {
                case 1 :
                    // InternalModel2Blockly.g:3389:3: rule__Attribute__UiAssignment_18
                    {
                    pushFollow(FOLLOW_2);
                    rule__Attribute__UiAssignment_18();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getAttributeAccess().getUiAssignment_18()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group__18__Impl"


    // $ANTLR start "rule__Attribute__Group_5__0"
    // InternalModel2Blockly.g:3398:1: rule__Attribute__Group_5__0 : rule__Attribute__Group_5__0__Impl rule__Attribute__Group_5__1 ;
    public final void rule__Attribute__Group_5__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3402:1: ( rule__Attribute__Group_5__0__Impl rule__Attribute__Group_5__1 )
            // InternalModel2Blockly.g:3403:2: rule__Attribute__Group_5__0__Impl rule__Attribute__Group_5__1
            {
            pushFollow(FOLLOW_10);
            rule__Attribute__Group_5__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Attribute__Group_5__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group_5__0"


    // $ANTLR start "rule__Attribute__Group_5__0__Impl"
    // InternalModel2Blockly.g:3410:1: rule__Attribute__Group_5__0__Impl : ( 'default' ) ;
    public final void rule__Attribute__Group_5__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3414:1: ( ( 'default' ) )
            // InternalModel2Blockly.g:3415:1: ( 'default' )
            {
            // InternalModel2Blockly.g:3415:1: ( 'default' )
            // InternalModel2Blockly.g:3416:2: 'default'
            {
             before(grammarAccess.getAttributeAccess().getDefaultKeyword_5_0()); 
            match(input,25,FOLLOW_2); 
             after(grammarAccess.getAttributeAccess().getDefaultKeyword_5_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group_5__0__Impl"


    // $ANTLR start "rule__Attribute__Group_5__1"
    // InternalModel2Blockly.g:3425:1: rule__Attribute__Group_5__1 : rule__Attribute__Group_5__1__Impl ;
    public final void rule__Attribute__Group_5__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3429:1: ( rule__Attribute__Group_5__1__Impl )
            // InternalModel2Blockly.g:3430:2: rule__Attribute__Group_5__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Attribute__Group_5__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group_5__1"


    // $ANTLR start "rule__Attribute__Group_5__1__Impl"
    // InternalModel2Blockly.g:3436:1: rule__Attribute__Group_5__1__Impl : ( ( rule__Attribute__DefaultValueAssignment_5_1 ) ) ;
    public final void rule__Attribute__Group_5__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3440:1: ( ( ( rule__Attribute__DefaultValueAssignment_5_1 ) ) )
            // InternalModel2Blockly.g:3441:1: ( ( rule__Attribute__DefaultValueAssignment_5_1 ) )
            {
            // InternalModel2Blockly.g:3441:1: ( ( rule__Attribute__DefaultValueAssignment_5_1 ) )
            // InternalModel2Blockly.g:3442:2: ( rule__Attribute__DefaultValueAssignment_5_1 )
            {
             before(grammarAccess.getAttributeAccess().getDefaultValueAssignment_5_1()); 
            // InternalModel2Blockly.g:3443:2: ( rule__Attribute__DefaultValueAssignment_5_1 )
            // InternalModel2Blockly.g:3443:3: rule__Attribute__DefaultValueAssignment_5_1
            {
            pushFollow(FOLLOW_2);
            rule__Attribute__DefaultValueAssignment_5_1();

            state._fsp--;


            }

             after(grammarAccess.getAttributeAccess().getDefaultValueAssignment_5_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group_5__1__Impl"


    // $ANTLR start "rule__Attribute__Group_6__0"
    // InternalModel2Blockly.g:3452:1: rule__Attribute__Group_6__0 : rule__Attribute__Group_6__0__Impl rule__Attribute__Group_6__1 ;
    public final void rule__Attribute__Group_6__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3456:1: ( rule__Attribute__Group_6__0__Impl rule__Attribute__Group_6__1 )
            // InternalModel2Blockly.g:3457:2: rule__Attribute__Group_6__0__Impl rule__Attribute__Group_6__1
            {
            pushFollow(FOLLOW_10);
            rule__Attribute__Group_6__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Attribute__Group_6__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group_6__0"


    // $ANTLR start "rule__Attribute__Group_6__0__Impl"
    // InternalModel2Blockly.g:3464:1: rule__Attribute__Group_6__0__Impl : ( 'min' ) ;
    public final void rule__Attribute__Group_6__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3468:1: ( ( 'min' ) )
            // InternalModel2Blockly.g:3469:1: ( 'min' )
            {
            // InternalModel2Blockly.g:3469:1: ( 'min' )
            // InternalModel2Blockly.g:3470:2: 'min'
            {
             before(grammarAccess.getAttributeAccess().getMinKeyword_6_0()); 
            match(input,57,FOLLOW_2); 
             after(grammarAccess.getAttributeAccess().getMinKeyword_6_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group_6__0__Impl"


    // $ANTLR start "rule__Attribute__Group_6__1"
    // InternalModel2Blockly.g:3479:1: rule__Attribute__Group_6__1 : rule__Attribute__Group_6__1__Impl ;
    public final void rule__Attribute__Group_6__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3483:1: ( rule__Attribute__Group_6__1__Impl )
            // InternalModel2Blockly.g:3484:2: rule__Attribute__Group_6__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Attribute__Group_6__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group_6__1"


    // $ANTLR start "rule__Attribute__Group_6__1__Impl"
    // InternalModel2Blockly.g:3490:1: rule__Attribute__Group_6__1__Impl : ( ( rule__Attribute__MinAssignment_6_1 ) ) ;
    public final void rule__Attribute__Group_6__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3494:1: ( ( ( rule__Attribute__MinAssignment_6_1 ) ) )
            // InternalModel2Blockly.g:3495:1: ( ( rule__Attribute__MinAssignment_6_1 ) )
            {
            // InternalModel2Blockly.g:3495:1: ( ( rule__Attribute__MinAssignment_6_1 ) )
            // InternalModel2Blockly.g:3496:2: ( rule__Attribute__MinAssignment_6_1 )
            {
             before(grammarAccess.getAttributeAccess().getMinAssignment_6_1()); 
            // InternalModel2Blockly.g:3497:2: ( rule__Attribute__MinAssignment_6_1 )
            // InternalModel2Blockly.g:3497:3: rule__Attribute__MinAssignment_6_1
            {
            pushFollow(FOLLOW_2);
            rule__Attribute__MinAssignment_6_1();

            state._fsp--;


            }

             after(grammarAccess.getAttributeAccess().getMinAssignment_6_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group_6__1__Impl"


    // $ANTLR start "rule__Attribute__Group_7__0"
    // InternalModel2Blockly.g:3506:1: rule__Attribute__Group_7__0 : rule__Attribute__Group_7__0__Impl rule__Attribute__Group_7__1 ;
    public final void rule__Attribute__Group_7__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3510:1: ( rule__Attribute__Group_7__0__Impl rule__Attribute__Group_7__1 )
            // InternalModel2Blockly.g:3511:2: rule__Attribute__Group_7__0__Impl rule__Attribute__Group_7__1
            {
            pushFollow(FOLLOW_10);
            rule__Attribute__Group_7__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Attribute__Group_7__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group_7__0"


    // $ANTLR start "rule__Attribute__Group_7__0__Impl"
    // InternalModel2Blockly.g:3518:1: rule__Attribute__Group_7__0__Impl : ( 'max' ) ;
    public final void rule__Attribute__Group_7__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3522:1: ( ( 'max' ) )
            // InternalModel2Blockly.g:3523:1: ( 'max' )
            {
            // InternalModel2Blockly.g:3523:1: ( 'max' )
            // InternalModel2Blockly.g:3524:2: 'max'
            {
             before(grammarAccess.getAttributeAccess().getMaxKeyword_7_0()); 
            match(input,58,FOLLOW_2); 
             after(grammarAccess.getAttributeAccess().getMaxKeyword_7_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group_7__0__Impl"


    // $ANTLR start "rule__Attribute__Group_7__1"
    // InternalModel2Blockly.g:3533:1: rule__Attribute__Group_7__1 : rule__Attribute__Group_7__1__Impl ;
    public final void rule__Attribute__Group_7__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3537:1: ( rule__Attribute__Group_7__1__Impl )
            // InternalModel2Blockly.g:3538:2: rule__Attribute__Group_7__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Attribute__Group_7__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group_7__1"


    // $ANTLR start "rule__Attribute__Group_7__1__Impl"
    // InternalModel2Blockly.g:3544:1: rule__Attribute__Group_7__1__Impl : ( ( rule__Attribute__MaxAssignment_7_1 ) ) ;
    public final void rule__Attribute__Group_7__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3548:1: ( ( ( rule__Attribute__MaxAssignment_7_1 ) ) )
            // InternalModel2Blockly.g:3549:1: ( ( rule__Attribute__MaxAssignment_7_1 ) )
            {
            // InternalModel2Blockly.g:3549:1: ( ( rule__Attribute__MaxAssignment_7_1 ) )
            // InternalModel2Blockly.g:3550:2: ( rule__Attribute__MaxAssignment_7_1 )
            {
             before(grammarAccess.getAttributeAccess().getMaxAssignment_7_1()); 
            // InternalModel2Blockly.g:3551:2: ( rule__Attribute__MaxAssignment_7_1 )
            // InternalModel2Blockly.g:3551:3: rule__Attribute__MaxAssignment_7_1
            {
            pushFollow(FOLLOW_2);
            rule__Attribute__MaxAssignment_7_1();

            state._fsp--;


            }

             after(grammarAccess.getAttributeAccess().getMaxAssignment_7_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group_7__1__Impl"


    // $ANTLR start "rule__Attribute__Group_8__0"
    // InternalModel2Blockly.g:3560:1: rule__Attribute__Group_8__0 : rule__Attribute__Group_8__0__Impl rule__Attribute__Group_8__1 ;
    public final void rule__Attribute__Group_8__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3564:1: ( rule__Attribute__Group_8__0__Impl rule__Attribute__Group_8__1 )
            // InternalModel2Blockly.g:3565:2: rule__Attribute__Group_8__0__Impl rule__Attribute__Group_8__1
            {
            pushFollow(FOLLOW_10);
            rule__Attribute__Group_8__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Attribute__Group_8__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group_8__0"


    // $ANTLR start "rule__Attribute__Group_8__0__Impl"
    // InternalModel2Blockly.g:3572:1: rule__Attribute__Group_8__0__Impl : ( 'src' ) ;
    public final void rule__Attribute__Group_8__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3576:1: ( ( 'src' ) )
            // InternalModel2Blockly.g:3577:1: ( 'src' )
            {
            // InternalModel2Blockly.g:3577:1: ( 'src' )
            // InternalModel2Blockly.g:3578:2: 'src'
            {
             before(grammarAccess.getAttributeAccess().getSrcKeyword_8_0()); 
            match(input,59,FOLLOW_2); 
             after(grammarAccess.getAttributeAccess().getSrcKeyword_8_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group_8__0__Impl"


    // $ANTLR start "rule__Attribute__Group_8__1"
    // InternalModel2Blockly.g:3587:1: rule__Attribute__Group_8__1 : rule__Attribute__Group_8__1__Impl ;
    public final void rule__Attribute__Group_8__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3591:1: ( rule__Attribute__Group_8__1__Impl )
            // InternalModel2Blockly.g:3592:2: rule__Attribute__Group_8__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Attribute__Group_8__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group_8__1"


    // $ANTLR start "rule__Attribute__Group_8__1__Impl"
    // InternalModel2Blockly.g:3598:1: rule__Attribute__Group_8__1__Impl : ( ( rule__Attribute__ImageUrlAssignment_8_1 ) ) ;
    public final void rule__Attribute__Group_8__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3602:1: ( ( ( rule__Attribute__ImageUrlAssignment_8_1 ) ) )
            // InternalModel2Blockly.g:3603:1: ( ( rule__Attribute__ImageUrlAssignment_8_1 ) )
            {
            // InternalModel2Blockly.g:3603:1: ( ( rule__Attribute__ImageUrlAssignment_8_1 ) )
            // InternalModel2Blockly.g:3604:2: ( rule__Attribute__ImageUrlAssignment_8_1 )
            {
             before(grammarAccess.getAttributeAccess().getImageUrlAssignment_8_1()); 
            // InternalModel2Blockly.g:3605:2: ( rule__Attribute__ImageUrlAssignment_8_1 )
            // InternalModel2Blockly.g:3605:3: rule__Attribute__ImageUrlAssignment_8_1
            {
            pushFollow(FOLLOW_2);
            rule__Attribute__ImageUrlAssignment_8_1();

            state._fsp--;


            }

             after(grammarAccess.getAttributeAccess().getImageUrlAssignment_8_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group_8__1__Impl"


    // $ANTLR start "rule__Attribute__Group_9__0"
    // InternalModel2Blockly.g:3614:1: rule__Attribute__Group_9__0 : rule__Attribute__Group_9__0__Impl rule__Attribute__Group_9__1 ;
    public final void rule__Attribute__Group_9__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3618:1: ( rule__Attribute__Group_9__0__Impl rule__Attribute__Group_9__1 )
            // InternalModel2Blockly.g:3619:2: rule__Attribute__Group_9__0__Impl rule__Attribute__Group_9__1
            {
            pushFollow(FOLLOW_12);
            rule__Attribute__Group_9__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Attribute__Group_9__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group_9__0"


    // $ANTLR start "rule__Attribute__Group_9__0__Impl"
    // InternalModel2Blockly.g:3626:1: rule__Attribute__Group_9__0__Impl : ( 'width' ) ;
    public final void rule__Attribute__Group_9__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3630:1: ( ( 'width' ) )
            // InternalModel2Blockly.g:3631:1: ( 'width' )
            {
            // InternalModel2Blockly.g:3631:1: ( 'width' )
            // InternalModel2Blockly.g:3632:2: 'width'
            {
             before(grammarAccess.getAttributeAccess().getWidthKeyword_9_0()); 
            match(input,60,FOLLOW_2); 
             after(grammarAccess.getAttributeAccess().getWidthKeyword_9_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group_9__0__Impl"


    // $ANTLR start "rule__Attribute__Group_9__1"
    // InternalModel2Blockly.g:3641:1: rule__Attribute__Group_9__1 : rule__Attribute__Group_9__1__Impl ;
    public final void rule__Attribute__Group_9__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3645:1: ( rule__Attribute__Group_9__1__Impl )
            // InternalModel2Blockly.g:3646:2: rule__Attribute__Group_9__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Attribute__Group_9__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group_9__1"


    // $ANTLR start "rule__Attribute__Group_9__1__Impl"
    // InternalModel2Blockly.g:3652:1: rule__Attribute__Group_9__1__Impl : ( ( rule__Attribute__ImageWidthAssignment_9_1 ) ) ;
    public final void rule__Attribute__Group_9__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3656:1: ( ( ( rule__Attribute__ImageWidthAssignment_9_1 ) ) )
            // InternalModel2Blockly.g:3657:1: ( ( rule__Attribute__ImageWidthAssignment_9_1 ) )
            {
            // InternalModel2Blockly.g:3657:1: ( ( rule__Attribute__ImageWidthAssignment_9_1 ) )
            // InternalModel2Blockly.g:3658:2: ( rule__Attribute__ImageWidthAssignment_9_1 )
            {
             before(grammarAccess.getAttributeAccess().getImageWidthAssignment_9_1()); 
            // InternalModel2Blockly.g:3659:2: ( rule__Attribute__ImageWidthAssignment_9_1 )
            // InternalModel2Blockly.g:3659:3: rule__Attribute__ImageWidthAssignment_9_1
            {
            pushFollow(FOLLOW_2);
            rule__Attribute__ImageWidthAssignment_9_1();

            state._fsp--;


            }

             after(grammarAccess.getAttributeAccess().getImageWidthAssignment_9_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group_9__1__Impl"


    // $ANTLR start "rule__Attribute__Group_10__0"
    // InternalModel2Blockly.g:3668:1: rule__Attribute__Group_10__0 : rule__Attribute__Group_10__0__Impl rule__Attribute__Group_10__1 ;
    public final void rule__Attribute__Group_10__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3672:1: ( rule__Attribute__Group_10__0__Impl rule__Attribute__Group_10__1 )
            // InternalModel2Blockly.g:3673:2: rule__Attribute__Group_10__0__Impl rule__Attribute__Group_10__1
            {
            pushFollow(FOLLOW_12);
            rule__Attribute__Group_10__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Attribute__Group_10__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group_10__0"


    // $ANTLR start "rule__Attribute__Group_10__0__Impl"
    // InternalModel2Blockly.g:3680:1: rule__Attribute__Group_10__0__Impl : ( 'height' ) ;
    public final void rule__Attribute__Group_10__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3684:1: ( ( 'height' ) )
            // InternalModel2Blockly.g:3685:1: ( 'height' )
            {
            // InternalModel2Blockly.g:3685:1: ( 'height' )
            // InternalModel2Blockly.g:3686:2: 'height'
            {
             before(grammarAccess.getAttributeAccess().getHeightKeyword_10_0()); 
            match(input,61,FOLLOW_2); 
             after(grammarAccess.getAttributeAccess().getHeightKeyword_10_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group_10__0__Impl"


    // $ANTLR start "rule__Attribute__Group_10__1"
    // InternalModel2Blockly.g:3695:1: rule__Attribute__Group_10__1 : rule__Attribute__Group_10__1__Impl ;
    public final void rule__Attribute__Group_10__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3699:1: ( rule__Attribute__Group_10__1__Impl )
            // InternalModel2Blockly.g:3700:2: rule__Attribute__Group_10__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Attribute__Group_10__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group_10__1"


    // $ANTLR start "rule__Attribute__Group_10__1__Impl"
    // InternalModel2Blockly.g:3706:1: rule__Attribute__Group_10__1__Impl : ( ( rule__Attribute__ImageHeightAssignment_10_1 ) ) ;
    public final void rule__Attribute__Group_10__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3710:1: ( ( ( rule__Attribute__ImageHeightAssignment_10_1 ) ) )
            // InternalModel2Blockly.g:3711:1: ( ( rule__Attribute__ImageHeightAssignment_10_1 ) )
            {
            // InternalModel2Blockly.g:3711:1: ( ( rule__Attribute__ImageHeightAssignment_10_1 ) )
            // InternalModel2Blockly.g:3712:2: ( rule__Attribute__ImageHeightAssignment_10_1 )
            {
             before(grammarAccess.getAttributeAccess().getImageHeightAssignment_10_1()); 
            // InternalModel2Blockly.g:3713:2: ( rule__Attribute__ImageHeightAssignment_10_1 )
            // InternalModel2Blockly.g:3713:3: rule__Attribute__ImageHeightAssignment_10_1
            {
            pushFollow(FOLLOW_2);
            rule__Attribute__ImageHeightAssignment_10_1();

            state._fsp--;


            }

             after(grammarAccess.getAttributeAccess().getImageHeightAssignment_10_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group_10__1__Impl"


    // $ANTLR start "rule__Attribute__Group_11__0"
    // InternalModel2Blockly.g:3722:1: rule__Attribute__Group_11__0 : rule__Attribute__Group_11__0__Impl rule__Attribute__Group_11__1 ;
    public final void rule__Attribute__Group_11__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3726:1: ( rule__Attribute__Group_11__0__Impl rule__Attribute__Group_11__1 )
            // InternalModel2Blockly.g:3727:2: rule__Attribute__Group_11__0__Impl rule__Attribute__Group_11__1
            {
            pushFollow(FOLLOW_10);
            rule__Attribute__Group_11__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Attribute__Group_11__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group_11__0"


    // $ANTLR start "rule__Attribute__Group_11__0__Impl"
    // InternalModel2Blockly.g:3734:1: rule__Attribute__Group_11__0__Impl : ( 'alt' ) ;
    public final void rule__Attribute__Group_11__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3738:1: ( ( 'alt' ) )
            // InternalModel2Blockly.g:3739:1: ( 'alt' )
            {
            // InternalModel2Blockly.g:3739:1: ( 'alt' )
            // InternalModel2Blockly.g:3740:2: 'alt'
            {
             before(grammarAccess.getAttributeAccess().getAltKeyword_11_0()); 
            match(input,62,FOLLOW_2); 
             after(grammarAccess.getAttributeAccess().getAltKeyword_11_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group_11__0__Impl"


    // $ANTLR start "rule__Attribute__Group_11__1"
    // InternalModel2Blockly.g:3749:1: rule__Attribute__Group_11__1 : rule__Attribute__Group_11__1__Impl ;
    public final void rule__Attribute__Group_11__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3753:1: ( rule__Attribute__Group_11__1__Impl )
            // InternalModel2Blockly.g:3754:2: rule__Attribute__Group_11__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Attribute__Group_11__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group_11__1"


    // $ANTLR start "rule__Attribute__Group_11__1__Impl"
    // InternalModel2Blockly.g:3760:1: rule__Attribute__Group_11__1__Impl : ( ( rule__Attribute__ImageAltAssignment_11_1 ) ) ;
    public final void rule__Attribute__Group_11__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3764:1: ( ( ( rule__Attribute__ImageAltAssignment_11_1 ) ) )
            // InternalModel2Blockly.g:3765:1: ( ( rule__Attribute__ImageAltAssignment_11_1 ) )
            {
            // InternalModel2Blockly.g:3765:1: ( ( rule__Attribute__ImageAltAssignment_11_1 ) )
            // InternalModel2Blockly.g:3766:2: ( rule__Attribute__ImageAltAssignment_11_1 )
            {
             before(grammarAccess.getAttributeAccess().getImageAltAssignment_11_1()); 
            // InternalModel2Blockly.g:3767:2: ( rule__Attribute__ImageAltAssignment_11_1 )
            // InternalModel2Blockly.g:3767:3: rule__Attribute__ImageAltAssignment_11_1
            {
            pushFollow(FOLLOW_2);
            rule__Attribute__ImageAltAssignment_11_1();

            state._fsp--;


            }

             after(grammarAccess.getAttributeAccess().getImageAltAssignment_11_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group_11__1__Impl"


    // $ANTLR start "rule__Containment__Group__0"
    // InternalModel2Blockly.g:3776:1: rule__Containment__Group__0 : rule__Containment__Group__0__Impl rule__Containment__Group__1 ;
    public final void rule__Containment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3780:1: ( rule__Containment__Group__0__Impl rule__Containment__Group__1 )
            // InternalModel2Blockly.g:3781:2: rule__Containment__Group__0__Impl rule__Containment__Group__1
            {
            pushFollow(FOLLOW_4);
            rule__Containment__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Containment__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Containment__Group__0"


    // $ANTLR start "rule__Containment__Group__0__Impl"
    // InternalModel2Blockly.g:3788:1: rule__Containment__Group__0__Impl : ( 'contains' ) ;
    public final void rule__Containment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3792:1: ( ( 'contains' ) )
            // InternalModel2Blockly.g:3793:1: ( 'contains' )
            {
            // InternalModel2Blockly.g:3793:1: ( 'contains' )
            // InternalModel2Blockly.g:3794:2: 'contains'
            {
             before(grammarAccess.getContainmentAccess().getContainsKeyword_0()); 
            match(input,63,FOLLOW_2); 
             after(grammarAccess.getContainmentAccess().getContainsKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Containment__Group__0__Impl"


    // $ANTLR start "rule__Containment__Group__1"
    // InternalModel2Blockly.g:3803:1: rule__Containment__Group__1 : rule__Containment__Group__1__Impl rule__Containment__Group__2 ;
    public final void rule__Containment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3807:1: ( rule__Containment__Group__1__Impl rule__Containment__Group__2 )
            // InternalModel2Blockly.g:3808:2: rule__Containment__Group__1__Impl rule__Containment__Group__2
            {
            pushFollow(FOLLOW_4);
            rule__Containment__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Containment__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Containment__Group__1"


    // $ANTLR start "rule__Containment__Group__1__Impl"
    // InternalModel2Blockly.g:3815:1: rule__Containment__Group__1__Impl : ( ( rule__Containment__TypeAssignment_1 ) ) ;
    public final void rule__Containment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3819:1: ( ( ( rule__Containment__TypeAssignment_1 ) ) )
            // InternalModel2Blockly.g:3820:1: ( ( rule__Containment__TypeAssignment_1 ) )
            {
            // InternalModel2Blockly.g:3820:1: ( ( rule__Containment__TypeAssignment_1 ) )
            // InternalModel2Blockly.g:3821:2: ( rule__Containment__TypeAssignment_1 )
            {
             before(grammarAccess.getContainmentAccess().getTypeAssignment_1()); 
            // InternalModel2Blockly.g:3822:2: ( rule__Containment__TypeAssignment_1 )
            // InternalModel2Blockly.g:3822:3: rule__Containment__TypeAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__Containment__TypeAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getContainmentAccess().getTypeAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Containment__Group__1__Impl"


    // $ANTLR start "rule__Containment__Group__2"
    // InternalModel2Blockly.g:3830:1: rule__Containment__Group__2 : rule__Containment__Group__2__Impl rule__Containment__Group__3 ;
    public final void rule__Containment__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3834:1: ( rule__Containment__Group__2__Impl rule__Containment__Group__3 )
            // InternalModel2Blockly.g:3835:2: rule__Containment__Group__2__Impl rule__Containment__Group__3
            {
            pushFollow(FOLLOW_23);
            rule__Containment__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Containment__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Containment__Group__2"


    // $ANTLR start "rule__Containment__Group__2__Impl"
    // InternalModel2Blockly.g:3842:1: rule__Containment__Group__2__Impl : ( ( rule__Containment__NameAssignment_2 ) ) ;
    public final void rule__Containment__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3846:1: ( ( ( rule__Containment__NameAssignment_2 ) ) )
            // InternalModel2Blockly.g:3847:1: ( ( rule__Containment__NameAssignment_2 ) )
            {
            // InternalModel2Blockly.g:3847:1: ( ( rule__Containment__NameAssignment_2 ) )
            // InternalModel2Blockly.g:3848:2: ( rule__Containment__NameAssignment_2 )
            {
             before(grammarAccess.getContainmentAccess().getNameAssignment_2()); 
            // InternalModel2Blockly.g:3849:2: ( rule__Containment__NameAssignment_2 )
            // InternalModel2Blockly.g:3849:3: rule__Containment__NameAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__Containment__NameAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getContainmentAccess().getNameAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Containment__Group__2__Impl"


    // $ANTLR start "rule__Containment__Group__3"
    // InternalModel2Blockly.g:3857:1: rule__Containment__Group__3 : rule__Containment__Group__3__Impl rule__Containment__Group__4 ;
    public final void rule__Containment__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3861:1: ( rule__Containment__Group__3__Impl rule__Containment__Group__4 )
            // InternalModel2Blockly.g:3862:2: rule__Containment__Group__3__Impl rule__Containment__Group__4
            {
            pushFollow(FOLLOW_23);
            rule__Containment__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Containment__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Containment__Group__3"


    // $ANTLR start "rule__Containment__Group__3__Impl"
    // InternalModel2Blockly.g:3869:1: rule__Containment__Group__3__Impl : ( ( rule__Containment__Group_3__0 )? ) ;
    public final void rule__Containment__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3873:1: ( ( ( rule__Containment__Group_3__0 )? ) )
            // InternalModel2Blockly.g:3874:1: ( ( rule__Containment__Group_3__0 )? )
            {
            // InternalModel2Blockly.g:3874:1: ( ( rule__Containment__Group_3__0 )? )
            // InternalModel2Blockly.g:3875:2: ( rule__Containment__Group_3__0 )?
            {
             before(grammarAccess.getContainmentAccess().getGroup_3()); 
            // InternalModel2Blockly.g:3876:2: ( rule__Containment__Group_3__0 )?
            int alt52=2;
            int LA52_0 = input.LA(1);

            if ( (LA52_0==64) ) {
                alt52=1;
            }
            switch (alt52) {
                case 1 :
                    // InternalModel2Blockly.g:3876:3: rule__Containment__Group_3__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Containment__Group_3__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getContainmentAccess().getGroup_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Containment__Group__3__Impl"


    // $ANTLR start "rule__Containment__Group__4"
    // InternalModel2Blockly.g:3884:1: rule__Containment__Group__4 : rule__Containment__Group__4__Impl ;
    public final void rule__Containment__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3888:1: ( rule__Containment__Group__4__Impl )
            // InternalModel2Blockly.g:3889:2: rule__Containment__Group__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Containment__Group__4__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Containment__Group__4"


    // $ANTLR start "rule__Containment__Group__4__Impl"
    // InternalModel2Blockly.g:3895:1: rule__Containment__Group__4__Impl : ( ( rule__Containment__UiAssignment_4 )? ) ;
    public final void rule__Containment__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3899:1: ( ( ( rule__Containment__UiAssignment_4 )? ) )
            // InternalModel2Blockly.g:3900:1: ( ( rule__Containment__UiAssignment_4 )? )
            {
            // InternalModel2Blockly.g:3900:1: ( ( rule__Containment__UiAssignment_4 )? )
            // InternalModel2Blockly.g:3901:2: ( rule__Containment__UiAssignment_4 )?
            {
             before(grammarAccess.getContainmentAccess().getUiAssignment_4()); 
            // InternalModel2Blockly.g:3902:2: ( rule__Containment__UiAssignment_4 )?
            int alt53=2;
            int LA53_0 = input.LA(1);

            if ( ((LA53_0>=71 && LA53_0<=78)||(LA53_0>=98 && LA53_0<=99)) ) {
                alt53=1;
            }
            switch (alt53) {
                case 1 :
                    // InternalModel2Blockly.g:3902:3: rule__Containment__UiAssignment_4
                    {
                    pushFollow(FOLLOW_2);
                    rule__Containment__UiAssignment_4();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getContainmentAccess().getUiAssignment_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Containment__Group__4__Impl"


    // $ANTLR start "rule__Containment__Group_3__0"
    // InternalModel2Blockly.g:3911:1: rule__Containment__Group_3__0 : rule__Containment__Group_3__0__Impl rule__Containment__Group_3__1 ;
    public final void rule__Containment__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3915:1: ( rule__Containment__Group_3__0__Impl rule__Containment__Group_3__1 )
            // InternalModel2Blockly.g:3916:2: rule__Containment__Group_3__0__Impl rule__Containment__Group_3__1
            {
            pushFollow(FOLLOW_12);
            rule__Containment__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Containment__Group_3__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Containment__Group_3__0"


    // $ANTLR start "rule__Containment__Group_3__0__Impl"
    // InternalModel2Blockly.g:3923:1: rule__Containment__Group_3__0__Impl : ( '[' ) ;
    public final void rule__Containment__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3927:1: ( ( '[' ) )
            // InternalModel2Blockly.g:3928:1: ( '[' )
            {
            // InternalModel2Blockly.g:3928:1: ( '[' )
            // InternalModel2Blockly.g:3929:2: '['
            {
             before(grammarAccess.getContainmentAccess().getLeftSquareBracketKeyword_3_0()); 
            match(input,64,FOLLOW_2); 
             after(grammarAccess.getContainmentAccess().getLeftSquareBracketKeyword_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Containment__Group_3__0__Impl"


    // $ANTLR start "rule__Containment__Group_3__1"
    // InternalModel2Blockly.g:3938:1: rule__Containment__Group_3__1 : rule__Containment__Group_3__1__Impl rule__Containment__Group_3__2 ;
    public final void rule__Containment__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3942:1: ( rule__Containment__Group_3__1__Impl rule__Containment__Group_3__2 )
            // InternalModel2Blockly.g:3943:2: rule__Containment__Group_3__1__Impl rule__Containment__Group_3__2
            {
            pushFollow(FOLLOW_24);
            rule__Containment__Group_3__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Containment__Group_3__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Containment__Group_3__1"


    // $ANTLR start "rule__Containment__Group_3__1__Impl"
    // InternalModel2Blockly.g:3950:1: rule__Containment__Group_3__1__Impl : ( ( rule__Containment__LowerAssignment_3_1 ) ) ;
    public final void rule__Containment__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3954:1: ( ( ( rule__Containment__LowerAssignment_3_1 ) ) )
            // InternalModel2Blockly.g:3955:1: ( ( rule__Containment__LowerAssignment_3_1 ) )
            {
            // InternalModel2Blockly.g:3955:1: ( ( rule__Containment__LowerAssignment_3_1 ) )
            // InternalModel2Blockly.g:3956:2: ( rule__Containment__LowerAssignment_3_1 )
            {
             before(grammarAccess.getContainmentAccess().getLowerAssignment_3_1()); 
            // InternalModel2Blockly.g:3957:2: ( rule__Containment__LowerAssignment_3_1 )
            // InternalModel2Blockly.g:3957:3: rule__Containment__LowerAssignment_3_1
            {
            pushFollow(FOLLOW_2);
            rule__Containment__LowerAssignment_3_1();

            state._fsp--;


            }

             after(grammarAccess.getContainmentAccess().getLowerAssignment_3_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Containment__Group_3__1__Impl"


    // $ANTLR start "rule__Containment__Group_3__2"
    // InternalModel2Blockly.g:3965:1: rule__Containment__Group_3__2 : rule__Containment__Group_3__2__Impl rule__Containment__Group_3__3 ;
    public final void rule__Containment__Group_3__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3969:1: ( rule__Containment__Group_3__2__Impl rule__Containment__Group_3__3 )
            // InternalModel2Blockly.g:3970:2: rule__Containment__Group_3__2__Impl rule__Containment__Group_3__3
            {
            pushFollow(FOLLOW_12);
            rule__Containment__Group_3__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Containment__Group_3__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Containment__Group_3__2"


    // $ANTLR start "rule__Containment__Group_3__2__Impl"
    // InternalModel2Blockly.g:3977:1: rule__Containment__Group_3__2__Impl : ( '..' ) ;
    public final void rule__Containment__Group_3__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3981:1: ( ( '..' ) )
            // InternalModel2Blockly.g:3982:1: ( '..' )
            {
            // InternalModel2Blockly.g:3982:1: ( '..' )
            // InternalModel2Blockly.g:3983:2: '..'
            {
             before(grammarAccess.getContainmentAccess().getFullStopFullStopKeyword_3_2()); 
            match(input,65,FOLLOW_2); 
             after(grammarAccess.getContainmentAccess().getFullStopFullStopKeyword_3_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Containment__Group_3__2__Impl"


    // $ANTLR start "rule__Containment__Group_3__3"
    // InternalModel2Blockly.g:3992:1: rule__Containment__Group_3__3 : rule__Containment__Group_3__3__Impl rule__Containment__Group_3__4 ;
    public final void rule__Containment__Group_3__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:3996:1: ( rule__Containment__Group_3__3__Impl rule__Containment__Group_3__4 )
            // InternalModel2Blockly.g:3997:2: rule__Containment__Group_3__3__Impl rule__Containment__Group_3__4
            {
            pushFollow(FOLLOW_25);
            rule__Containment__Group_3__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Containment__Group_3__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Containment__Group_3__3"


    // $ANTLR start "rule__Containment__Group_3__3__Impl"
    // InternalModel2Blockly.g:4004:1: rule__Containment__Group_3__3__Impl : ( ( rule__Containment__UpperAssignment_3_3 ) ) ;
    public final void rule__Containment__Group_3__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4008:1: ( ( ( rule__Containment__UpperAssignment_3_3 ) ) )
            // InternalModel2Blockly.g:4009:1: ( ( rule__Containment__UpperAssignment_3_3 ) )
            {
            // InternalModel2Blockly.g:4009:1: ( ( rule__Containment__UpperAssignment_3_3 ) )
            // InternalModel2Blockly.g:4010:2: ( rule__Containment__UpperAssignment_3_3 )
            {
             before(grammarAccess.getContainmentAccess().getUpperAssignment_3_3()); 
            // InternalModel2Blockly.g:4011:2: ( rule__Containment__UpperAssignment_3_3 )
            // InternalModel2Blockly.g:4011:3: rule__Containment__UpperAssignment_3_3
            {
            pushFollow(FOLLOW_2);
            rule__Containment__UpperAssignment_3_3();

            state._fsp--;


            }

             after(grammarAccess.getContainmentAccess().getUpperAssignment_3_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Containment__Group_3__3__Impl"


    // $ANTLR start "rule__Containment__Group_3__4"
    // InternalModel2Blockly.g:4019:1: rule__Containment__Group_3__4 : rule__Containment__Group_3__4__Impl ;
    public final void rule__Containment__Group_3__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4023:1: ( rule__Containment__Group_3__4__Impl )
            // InternalModel2Blockly.g:4024:2: rule__Containment__Group_3__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Containment__Group_3__4__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Containment__Group_3__4"


    // $ANTLR start "rule__Containment__Group_3__4__Impl"
    // InternalModel2Blockly.g:4030:1: rule__Containment__Group_3__4__Impl : ( ']' ) ;
    public final void rule__Containment__Group_3__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4034:1: ( ( ']' ) )
            // InternalModel2Blockly.g:4035:1: ( ']' )
            {
            // InternalModel2Blockly.g:4035:1: ( ']' )
            // InternalModel2Blockly.g:4036:2: ']'
            {
             before(grammarAccess.getContainmentAccess().getRightSquareBracketKeyword_3_4()); 
            match(input,66,FOLLOW_2); 
             after(grammarAccess.getContainmentAccess().getRightSquareBracketKeyword_3_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Containment__Group_3__4__Impl"


    // $ANTLR start "rule__Reference__Group__0"
    // InternalModel2Blockly.g:4046:1: rule__Reference__Group__0 : rule__Reference__Group__0__Impl rule__Reference__Group__1 ;
    public final void rule__Reference__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4050:1: ( rule__Reference__Group__0__Impl rule__Reference__Group__1 )
            // InternalModel2Blockly.g:4051:2: rule__Reference__Group__0__Impl rule__Reference__Group__1
            {
            pushFollow(FOLLOW_4);
            rule__Reference__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Reference__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Reference__Group__0"


    // $ANTLR start "rule__Reference__Group__0__Impl"
    // InternalModel2Blockly.g:4058:1: rule__Reference__Group__0__Impl : ( 'reference' ) ;
    public final void rule__Reference__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4062:1: ( ( 'reference' ) )
            // InternalModel2Blockly.g:4063:1: ( 'reference' )
            {
            // InternalModel2Blockly.g:4063:1: ( 'reference' )
            // InternalModel2Blockly.g:4064:2: 'reference'
            {
             before(grammarAccess.getReferenceAccess().getReferenceKeyword_0()); 
            match(input,67,FOLLOW_2); 
             after(grammarAccess.getReferenceAccess().getReferenceKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Reference__Group__0__Impl"


    // $ANTLR start "rule__Reference__Group__1"
    // InternalModel2Blockly.g:4073:1: rule__Reference__Group__1 : rule__Reference__Group__1__Impl rule__Reference__Group__2 ;
    public final void rule__Reference__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4077:1: ( rule__Reference__Group__1__Impl rule__Reference__Group__2 )
            // InternalModel2Blockly.g:4078:2: rule__Reference__Group__1__Impl rule__Reference__Group__2
            {
            pushFollow(FOLLOW_4);
            rule__Reference__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Reference__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Reference__Group__1"


    // $ANTLR start "rule__Reference__Group__1__Impl"
    // InternalModel2Blockly.g:4085:1: rule__Reference__Group__1__Impl : ( ( rule__Reference__TypeAssignment_1 ) ) ;
    public final void rule__Reference__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4089:1: ( ( ( rule__Reference__TypeAssignment_1 ) ) )
            // InternalModel2Blockly.g:4090:1: ( ( rule__Reference__TypeAssignment_1 ) )
            {
            // InternalModel2Blockly.g:4090:1: ( ( rule__Reference__TypeAssignment_1 ) )
            // InternalModel2Blockly.g:4091:2: ( rule__Reference__TypeAssignment_1 )
            {
             before(grammarAccess.getReferenceAccess().getTypeAssignment_1()); 
            // InternalModel2Blockly.g:4092:2: ( rule__Reference__TypeAssignment_1 )
            // InternalModel2Blockly.g:4092:3: rule__Reference__TypeAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__Reference__TypeAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getReferenceAccess().getTypeAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Reference__Group__1__Impl"


    // $ANTLR start "rule__Reference__Group__2"
    // InternalModel2Blockly.g:4100:1: rule__Reference__Group__2 : rule__Reference__Group__2__Impl rule__Reference__Group__3 ;
    public final void rule__Reference__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4104:1: ( rule__Reference__Group__2__Impl rule__Reference__Group__3 )
            // InternalModel2Blockly.g:4105:2: rule__Reference__Group__2__Impl rule__Reference__Group__3
            {
            pushFollow(FOLLOW_26);
            rule__Reference__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Reference__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Reference__Group__2"


    // $ANTLR start "rule__Reference__Group__2__Impl"
    // InternalModel2Blockly.g:4112:1: rule__Reference__Group__2__Impl : ( ( rule__Reference__NameAssignment_2 ) ) ;
    public final void rule__Reference__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4116:1: ( ( ( rule__Reference__NameAssignment_2 ) ) )
            // InternalModel2Blockly.g:4117:1: ( ( rule__Reference__NameAssignment_2 ) )
            {
            // InternalModel2Blockly.g:4117:1: ( ( rule__Reference__NameAssignment_2 ) )
            // InternalModel2Blockly.g:4118:2: ( rule__Reference__NameAssignment_2 )
            {
             before(grammarAccess.getReferenceAccess().getNameAssignment_2()); 
            // InternalModel2Blockly.g:4119:2: ( rule__Reference__NameAssignment_2 )
            // InternalModel2Blockly.g:4119:3: rule__Reference__NameAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__Reference__NameAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getReferenceAccess().getNameAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Reference__Group__2__Impl"


    // $ANTLR start "rule__Reference__Group__3"
    // InternalModel2Blockly.g:4127:1: rule__Reference__Group__3 : rule__Reference__Group__3__Impl rule__Reference__Group__4 ;
    public final void rule__Reference__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4131:1: ( rule__Reference__Group__3__Impl rule__Reference__Group__4 )
            // InternalModel2Blockly.g:4132:2: rule__Reference__Group__3__Impl rule__Reference__Group__4
            {
            pushFollow(FOLLOW_26);
            rule__Reference__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Reference__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Reference__Group__3"


    // $ANTLR start "rule__Reference__Group__3__Impl"
    // InternalModel2Blockly.g:4139:1: rule__Reference__Group__3__Impl : ( ( rule__Reference__CardinalityAssignment_3 )? ) ;
    public final void rule__Reference__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4143:1: ( ( ( rule__Reference__CardinalityAssignment_3 )? ) )
            // InternalModel2Blockly.g:4144:1: ( ( rule__Reference__CardinalityAssignment_3 )? )
            {
            // InternalModel2Blockly.g:4144:1: ( ( rule__Reference__CardinalityAssignment_3 )? )
            // InternalModel2Blockly.g:4145:2: ( rule__Reference__CardinalityAssignment_3 )?
            {
             before(grammarAccess.getReferenceAccess().getCardinalityAssignment_3()); 
            // InternalModel2Blockly.g:4146:2: ( rule__Reference__CardinalityAssignment_3 )?
            int alt54=2;
            int LA54_0 = input.LA(1);

            if ( (LA54_0==64) ) {
                alt54=1;
            }
            switch (alt54) {
                case 1 :
                    // InternalModel2Blockly.g:4146:3: rule__Reference__CardinalityAssignment_3
                    {
                    pushFollow(FOLLOW_2);
                    rule__Reference__CardinalityAssignment_3();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getReferenceAccess().getCardinalityAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Reference__Group__3__Impl"


    // $ANTLR start "rule__Reference__Group__4"
    // InternalModel2Blockly.g:4154:1: rule__Reference__Group__4 : rule__Reference__Group__4__Impl rule__Reference__Group__5 ;
    public final void rule__Reference__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4158:1: ( rule__Reference__Group__4__Impl rule__Reference__Group__5 )
            // InternalModel2Blockly.g:4159:2: rule__Reference__Group__4__Impl rule__Reference__Group__5
            {
            pushFollow(FOLLOW_26);
            rule__Reference__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Reference__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Reference__Group__4"


    // $ANTLR start "rule__Reference__Group__4__Impl"
    // InternalModel2Blockly.g:4166:1: rule__Reference__Group__4__Impl : ( ( rule__Reference__Group_4__0 )? ) ;
    public final void rule__Reference__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4170:1: ( ( ( rule__Reference__Group_4__0 )? ) )
            // InternalModel2Blockly.g:4171:1: ( ( rule__Reference__Group_4__0 )? )
            {
            // InternalModel2Blockly.g:4171:1: ( ( rule__Reference__Group_4__0 )? )
            // InternalModel2Blockly.g:4172:2: ( rule__Reference__Group_4__0 )?
            {
             before(grammarAccess.getReferenceAccess().getGroup_4()); 
            // InternalModel2Blockly.g:4173:2: ( rule__Reference__Group_4__0 )?
            int alt55=2;
            int LA55_0 = input.LA(1);

            if ( (LA55_0==68) ) {
                alt55=1;
            }
            switch (alt55) {
                case 1 :
                    // InternalModel2Blockly.g:4173:3: rule__Reference__Group_4__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Reference__Group_4__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getReferenceAccess().getGroup_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Reference__Group__4__Impl"


    // $ANTLR start "rule__Reference__Group__5"
    // InternalModel2Blockly.g:4181:1: rule__Reference__Group__5 : rule__Reference__Group__5__Impl rule__Reference__Group__6 ;
    public final void rule__Reference__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4185:1: ( rule__Reference__Group__5__Impl rule__Reference__Group__6 )
            // InternalModel2Blockly.g:4186:2: rule__Reference__Group__5__Impl rule__Reference__Group__6
            {
            pushFollow(FOLLOW_26);
            rule__Reference__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Reference__Group__6();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Reference__Group__5"


    // $ANTLR start "rule__Reference__Group__5__Impl"
    // InternalModel2Blockly.g:4193:1: rule__Reference__Group__5__Impl : ( ( rule__Reference__RequiredAssignment_5 )? ) ;
    public final void rule__Reference__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4197:1: ( ( ( rule__Reference__RequiredAssignment_5 )? ) )
            // InternalModel2Blockly.g:4198:1: ( ( rule__Reference__RequiredAssignment_5 )? )
            {
            // InternalModel2Blockly.g:4198:1: ( ( rule__Reference__RequiredAssignment_5 )? )
            // InternalModel2Blockly.g:4199:2: ( rule__Reference__RequiredAssignment_5 )?
            {
             before(grammarAccess.getReferenceAccess().getRequiredAssignment_5()); 
            // InternalModel2Blockly.g:4200:2: ( rule__Reference__RequiredAssignment_5 )?
            int alt56=2;
            int LA56_0 = input.LA(1);

            if ( (LA56_0==92) ) {
                alt56=1;
            }
            switch (alt56) {
                case 1 :
                    // InternalModel2Blockly.g:4200:3: rule__Reference__RequiredAssignment_5
                    {
                    pushFollow(FOLLOW_2);
                    rule__Reference__RequiredAssignment_5();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getReferenceAccess().getRequiredAssignment_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Reference__Group__5__Impl"


    // $ANTLR start "rule__Reference__Group__6"
    // InternalModel2Blockly.g:4208:1: rule__Reference__Group__6 : rule__Reference__Group__6__Impl rule__Reference__Group__7 ;
    public final void rule__Reference__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4212:1: ( rule__Reference__Group__6__Impl rule__Reference__Group__7 )
            // InternalModel2Blockly.g:4213:2: rule__Reference__Group__6__Impl rule__Reference__Group__7
            {
            pushFollow(FOLLOW_26);
            rule__Reference__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Reference__Group__7();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Reference__Group__6"


    // $ANTLR start "rule__Reference__Group__6__Impl"
    // InternalModel2Blockly.g:4220:1: rule__Reference__Group__6__Impl : ( ( rule__Reference__UniqueAssignment_6 )? ) ;
    public final void rule__Reference__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4224:1: ( ( ( rule__Reference__UniqueAssignment_6 )? ) )
            // InternalModel2Blockly.g:4225:1: ( ( rule__Reference__UniqueAssignment_6 )? )
            {
            // InternalModel2Blockly.g:4225:1: ( ( rule__Reference__UniqueAssignment_6 )? )
            // InternalModel2Blockly.g:4226:2: ( rule__Reference__UniqueAssignment_6 )?
            {
             before(grammarAccess.getReferenceAccess().getUniqueAssignment_6()); 
            // InternalModel2Blockly.g:4227:2: ( rule__Reference__UniqueAssignment_6 )?
            int alt57=2;
            int LA57_0 = input.LA(1);

            if ( (LA57_0==94) ) {
                alt57=1;
            }
            switch (alt57) {
                case 1 :
                    // InternalModel2Blockly.g:4227:3: rule__Reference__UniqueAssignment_6
                    {
                    pushFollow(FOLLOW_2);
                    rule__Reference__UniqueAssignment_6();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getReferenceAccess().getUniqueAssignment_6()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Reference__Group__6__Impl"


    // $ANTLR start "rule__Reference__Group__7"
    // InternalModel2Blockly.g:4235:1: rule__Reference__Group__7 : rule__Reference__Group__7__Impl rule__Reference__Group__8 ;
    public final void rule__Reference__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4239:1: ( rule__Reference__Group__7__Impl rule__Reference__Group__8 )
            // InternalModel2Blockly.g:4240:2: rule__Reference__Group__7__Impl rule__Reference__Group__8
            {
            pushFollow(FOLLOW_26);
            rule__Reference__Group__7__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Reference__Group__8();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Reference__Group__7"


    // $ANTLR start "rule__Reference__Group__7__Impl"
    // InternalModel2Blockly.g:4247:1: rule__Reference__Group__7__Impl : ( ( rule__Reference__NonUniqueAssignment_7 )? ) ;
    public final void rule__Reference__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4251:1: ( ( ( rule__Reference__NonUniqueAssignment_7 )? ) )
            // InternalModel2Blockly.g:4252:1: ( ( rule__Reference__NonUniqueAssignment_7 )? )
            {
            // InternalModel2Blockly.g:4252:1: ( ( rule__Reference__NonUniqueAssignment_7 )? )
            // InternalModel2Blockly.g:4253:2: ( rule__Reference__NonUniqueAssignment_7 )?
            {
             before(grammarAccess.getReferenceAccess().getNonUniqueAssignment_7()); 
            // InternalModel2Blockly.g:4254:2: ( rule__Reference__NonUniqueAssignment_7 )?
            int alt58=2;
            int LA58_0 = input.LA(1);

            if ( (LA58_0==95) ) {
                alt58=1;
            }
            switch (alt58) {
                case 1 :
                    // InternalModel2Blockly.g:4254:3: rule__Reference__NonUniqueAssignment_7
                    {
                    pushFollow(FOLLOW_2);
                    rule__Reference__NonUniqueAssignment_7();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getReferenceAccess().getNonUniqueAssignment_7()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Reference__Group__7__Impl"


    // $ANTLR start "rule__Reference__Group__8"
    // InternalModel2Blockly.g:4262:1: rule__Reference__Group__8 : rule__Reference__Group__8__Impl rule__Reference__Group__9 ;
    public final void rule__Reference__Group__8() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4266:1: ( rule__Reference__Group__8__Impl rule__Reference__Group__9 )
            // InternalModel2Blockly.g:4267:2: rule__Reference__Group__8__Impl rule__Reference__Group__9
            {
            pushFollow(FOLLOW_26);
            rule__Reference__Group__8__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Reference__Group__9();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Reference__Group__8"


    // $ANTLR start "rule__Reference__Group__8__Impl"
    // InternalModel2Blockly.g:4274:1: rule__Reference__Group__8__Impl : ( ( rule__Reference__OrderedAssignment_8 )? ) ;
    public final void rule__Reference__Group__8__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4278:1: ( ( ( rule__Reference__OrderedAssignment_8 )? ) )
            // InternalModel2Blockly.g:4279:1: ( ( rule__Reference__OrderedAssignment_8 )? )
            {
            // InternalModel2Blockly.g:4279:1: ( ( rule__Reference__OrderedAssignment_8 )? )
            // InternalModel2Blockly.g:4280:2: ( rule__Reference__OrderedAssignment_8 )?
            {
             before(grammarAccess.getReferenceAccess().getOrderedAssignment_8()); 
            // InternalModel2Blockly.g:4281:2: ( rule__Reference__OrderedAssignment_8 )?
            int alt59=2;
            int LA59_0 = input.LA(1);

            if ( (LA59_0==96) ) {
                alt59=1;
            }
            switch (alt59) {
                case 1 :
                    // InternalModel2Blockly.g:4281:3: rule__Reference__OrderedAssignment_8
                    {
                    pushFollow(FOLLOW_2);
                    rule__Reference__OrderedAssignment_8();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getReferenceAccess().getOrderedAssignment_8()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Reference__Group__8__Impl"


    // $ANTLR start "rule__Reference__Group__9"
    // InternalModel2Blockly.g:4289:1: rule__Reference__Group__9 : rule__Reference__Group__9__Impl rule__Reference__Group__10 ;
    public final void rule__Reference__Group__9() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4293:1: ( rule__Reference__Group__9__Impl rule__Reference__Group__10 )
            // InternalModel2Blockly.g:4294:2: rule__Reference__Group__9__Impl rule__Reference__Group__10
            {
            pushFollow(FOLLOW_26);
            rule__Reference__Group__9__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Reference__Group__10();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Reference__Group__9"


    // $ANTLR start "rule__Reference__Group__9__Impl"
    // InternalModel2Blockly.g:4301:1: rule__Reference__Group__9__Impl : ( ( rule__Reference__UnorderedAssignment_9 )? ) ;
    public final void rule__Reference__Group__9__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4305:1: ( ( ( rule__Reference__UnorderedAssignment_9 )? ) )
            // InternalModel2Blockly.g:4306:1: ( ( rule__Reference__UnorderedAssignment_9 )? )
            {
            // InternalModel2Blockly.g:4306:1: ( ( rule__Reference__UnorderedAssignment_9 )? )
            // InternalModel2Blockly.g:4307:2: ( rule__Reference__UnorderedAssignment_9 )?
            {
             before(grammarAccess.getReferenceAccess().getUnorderedAssignment_9()); 
            // InternalModel2Blockly.g:4308:2: ( rule__Reference__UnorderedAssignment_9 )?
            int alt60=2;
            int LA60_0 = input.LA(1);

            if ( (LA60_0==97) ) {
                alt60=1;
            }
            switch (alt60) {
                case 1 :
                    // InternalModel2Blockly.g:4308:3: rule__Reference__UnorderedAssignment_9
                    {
                    pushFollow(FOLLOW_2);
                    rule__Reference__UnorderedAssignment_9();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getReferenceAccess().getUnorderedAssignment_9()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Reference__Group__9__Impl"


    // $ANTLR start "rule__Reference__Group__10"
    // InternalModel2Blockly.g:4316:1: rule__Reference__Group__10 : rule__Reference__Group__10__Impl ;
    public final void rule__Reference__Group__10() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4320:1: ( rule__Reference__Group__10__Impl )
            // InternalModel2Blockly.g:4321:2: rule__Reference__Group__10__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Reference__Group__10__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Reference__Group__10"


    // $ANTLR start "rule__Reference__Group__10__Impl"
    // InternalModel2Blockly.g:4327:1: rule__Reference__Group__10__Impl : ( ( rule__Reference__UiAssignment_10 )? ) ;
    public final void rule__Reference__Group__10__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4331:1: ( ( ( rule__Reference__UiAssignment_10 )? ) )
            // InternalModel2Blockly.g:4332:1: ( ( rule__Reference__UiAssignment_10 )? )
            {
            // InternalModel2Blockly.g:4332:1: ( ( rule__Reference__UiAssignment_10 )? )
            // InternalModel2Blockly.g:4333:2: ( rule__Reference__UiAssignment_10 )?
            {
             before(grammarAccess.getReferenceAccess().getUiAssignment_10()); 
            // InternalModel2Blockly.g:4334:2: ( rule__Reference__UiAssignment_10 )?
            int alt61=2;
            int LA61_0 = input.LA(1);

            if ( ((LA61_0>=71 && LA61_0<=78)||(LA61_0>=98 && LA61_0<=99)) ) {
                alt61=1;
            }
            switch (alt61) {
                case 1 :
                    // InternalModel2Blockly.g:4334:3: rule__Reference__UiAssignment_10
                    {
                    pushFollow(FOLLOW_2);
                    rule__Reference__UiAssignment_10();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getReferenceAccess().getUiAssignment_10()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Reference__Group__10__Impl"


    // $ANTLR start "rule__Reference__Group_4__0"
    // InternalModel2Blockly.g:4343:1: rule__Reference__Group_4__0 : rule__Reference__Group_4__0__Impl rule__Reference__Group_4__1 ;
    public final void rule__Reference__Group_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4347:1: ( rule__Reference__Group_4__0__Impl rule__Reference__Group_4__1 )
            // InternalModel2Blockly.g:4348:2: rule__Reference__Group_4__0__Impl rule__Reference__Group_4__1
            {
            pushFollow(FOLLOW_4);
            rule__Reference__Group_4__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Reference__Group_4__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Reference__Group_4__0"


    // $ANTLR start "rule__Reference__Group_4__0__Impl"
    // InternalModel2Blockly.g:4355:1: rule__Reference__Group_4__0__Impl : ( 'opposite' ) ;
    public final void rule__Reference__Group_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4359:1: ( ( 'opposite' ) )
            // InternalModel2Blockly.g:4360:1: ( 'opposite' )
            {
            // InternalModel2Blockly.g:4360:1: ( 'opposite' )
            // InternalModel2Blockly.g:4361:2: 'opposite'
            {
             before(grammarAccess.getReferenceAccess().getOppositeKeyword_4_0()); 
            match(input,68,FOLLOW_2); 
             after(grammarAccess.getReferenceAccess().getOppositeKeyword_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Reference__Group_4__0__Impl"


    // $ANTLR start "rule__Reference__Group_4__1"
    // InternalModel2Blockly.g:4370:1: rule__Reference__Group_4__1 : rule__Reference__Group_4__1__Impl ;
    public final void rule__Reference__Group_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4374:1: ( rule__Reference__Group_4__1__Impl )
            // InternalModel2Blockly.g:4375:2: rule__Reference__Group_4__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Reference__Group_4__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Reference__Group_4__1"


    // $ANTLR start "rule__Reference__Group_4__1__Impl"
    // InternalModel2Blockly.g:4381:1: rule__Reference__Group_4__1__Impl : ( ( rule__Reference__OppositeNameAssignment_4_1 ) ) ;
    public final void rule__Reference__Group_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4385:1: ( ( ( rule__Reference__OppositeNameAssignment_4_1 ) ) )
            // InternalModel2Blockly.g:4386:1: ( ( rule__Reference__OppositeNameAssignment_4_1 ) )
            {
            // InternalModel2Blockly.g:4386:1: ( ( rule__Reference__OppositeNameAssignment_4_1 ) )
            // InternalModel2Blockly.g:4387:2: ( rule__Reference__OppositeNameAssignment_4_1 )
            {
             before(grammarAccess.getReferenceAccess().getOppositeNameAssignment_4_1()); 
            // InternalModel2Blockly.g:4388:2: ( rule__Reference__OppositeNameAssignment_4_1 )
            // InternalModel2Blockly.g:4388:3: rule__Reference__OppositeNameAssignment_4_1
            {
            pushFollow(FOLLOW_2);
            rule__Reference__OppositeNameAssignment_4_1();

            state._fsp--;


            }

             after(grammarAccess.getReferenceAccess().getOppositeNameAssignment_4_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Reference__Group_4__1__Impl"


    // $ANTLR start "rule__ValueInput__Group__0"
    // InternalModel2Blockly.g:4397:1: rule__ValueInput__Group__0 : rule__ValueInput__Group__0__Impl rule__ValueInput__Group__1 ;
    public final void rule__ValueInput__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4401:1: ( rule__ValueInput__Group__0__Impl rule__ValueInput__Group__1 )
            // InternalModel2Blockly.g:4402:2: rule__ValueInput__Group__0__Impl rule__ValueInput__Group__1
            {
            pushFollow(FOLLOW_4);
            rule__ValueInput__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ValueInput__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ValueInput__Group__0"


    // $ANTLR start "rule__ValueInput__Group__0__Impl"
    // InternalModel2Blockly.g:4409:1: rule__ValueInput__Group__0__Impl : ( 'value' ) ;
    public final void rule__ValueInput__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4413:1: ( ( 'value' ) )
            // InternalModel2Blockly.g:4414:1: ( 'value' )
            {
            // InternalModel2Blockly.g:4414:1: ( 'value' )
            // InternalModel2Blockly.g:4415:2: 'value'
            {
             before(grammarAccess.getValueInputAccess().getValueKeyword_0()); 
            match(input,69,FOLLOW_2); 
             after(grammarAccess.getValueInputAccess().getValueKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ValueInput__Group__0__Impl"


    // $ANTLR start "rule__ValueInput__Group__1"
    // InternalModel2Blockly.g:4424:1: rule__ValueInput__Group__1 : rule__ValueInput__Group__1__Impl rule__ValueInput__Group__2 ;
    public final void rule__ValueInput__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4428:1: ( rule__ValueInput__Group__1__Impl rule__ValueInput__Group__2 )
            // InternalModel2Blockly.g:4429:2: rule__ValueInput__Group__1__Impl rule__ValueInput__Group__2
            {
            pushFollow(FOLLOW_4);
            rule__ValueInput__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ValueInput__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ValueInput__Group__1"


    // $ANTLR start "rule__ValueInput__Group__1__Impl"
    // InternalModel2Blockly.g:4436:1: rule__ValueInput__Group__1__Impl : ( ( rule__ValueInput__TypeAssignment_1 ) ) ;
    public final void rule__ValueInput__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4440:1: ( ( ( rule__ValueInput__TypeAssignment_1 ) ) )
            // InternalModel2Blockly.g:4441:1: ( ( rule__ValueInput__TypeAssignment_1 ) )
            {
            // InternalModel2Blockly.g:4441:1: ( ( rule__ValueInput__TypeAssignment_1 ) )
            // InternalModel2Blockly.g:4442:2: ( rule__ValueInput__TypeAssignment_1 )
            {
             before(grammarAccess.getValueInputAccess().getTypeAssignment_1()); 
            // InternalModel2Blockly.g:4443:2: ( rule__ValueInput__TypeAssignment_1 )
            // InternalModel2Blockly.g:4443:3: rule__ValueInput__TypeAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__ValueInput__TypeAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getValueInputAccess().getTypeAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ValueInput__Group__1__Impl"


    // $ANTLR start "rule__ValueInput__Group__2"
    // InternalModel2Blockly.g:4451:1: rule__ValueInput__Group__2 : rule__ValueInput__Group__2__Impl rule__ValueInput__Group__3 ;
    public final void rule__ValueInput__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4455:1: ( rule__ValueInput__Group__2__Impl rule__ValueInput__Group__3 )
            // InternalModel2Blockly.g:4456:2: rule__ValueInput__Group__2__Impl rule__ValueInput__Group__3
            {
            pushFollow(FOLLOW_27);
            rule__ValueInput__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ValueInput__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ValueInput__Group__2"


    // $ANTLR start "rule__ValueInput__Group__2__Impl"
    // InternalModel2Blockly.g:4463:1: rule__ValueInput__Group__2__Impl : ( ( rule__ValueInput__NameAssignment_2 ) ) ;
    public final void rule__ValueInput__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4467:1: ( ( ( rule__ValueInput__NameAssignment_2 ) ) )
            // InternalModel2Blockly.g:4468:1: ( ( rule__ValueInput__NameAssignment_2 ) )
            {
            // InternalModel2Blockly.g:4468:1: ( ( rule__ValueInput__NameAssignment_2 ) )
            // InternalModel2Blockly.g:4469:2: ( rule__ValueInput__NameAssignment_2 )
            {
             before(grammarAccess.getValueInputAccess().getNameAssignment_2()); 
            // InternalModel2Blockly.g:4470:2: ( rule__ValueInput__NameAssignment_2 )
            // InternalModel2Blockly.g:4470:3: rule__ValueInput__NameAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__ValueInput__NameAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getValueInputAccess().getNameAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ValueInput__Group__2__Impl"


    // $ANTLR start "rule__ValueInput__Group__3"
    // InternalModel2Blockly.g:4478:1: rule__ValueInput__Group__3 : rule__ValueInput__Group__3__Impl rule__ValueInput__Group__4 ;
    public final void rule__ValueInput__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4482:1: ( rule__ValueInput__Group__3__Impl rule__ValueInput__Group__4 )
            // InternalModel2Blockly.g:4483:2: rule__ValueInput__Group__3__Impl rule__ValueInput__Group__4
            {
            pushFollow(FOLLOW_27);
            rule__ValueInput__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ValueInput__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ValueInput__Group__3"


    // $ANTLR start "rule__ValueInput__Group__3__Impl"
    // InternalModel2Blockly.g:4490:1: rule__ValueInput__Group__3__Impl : ( ( rule__ValueInput__Group_3__0 )? ) ;
    public final void rule__ValueInput__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4494:1: ( ( ( rule__ValueInput__Group_3__0 )? ) )
            // InternalModel2Blockly.g:4495:1: ( ( rule__ValueInput__Group_3__0 )? )
            {
            // InternalModel2Blockly.g:4495:1: ( ( rule__ValueInput__Group_3__0 )? )
            // InternalModel2Blockly.g:4496:2: ( rule__ValueInput__Group_3__0 )?
            {
             before(grammarAccess.getValueInputAccess().getGroup_3()); 
            // InternalModel2Blockly.g:4497:2: ( rule__ValueInput__Group_3__0 )?
            int alt62=2;
            int LA62_0 = input.LA(1);

            if ( (LA62_0==70) ) {
                alt62=1;
            }
            switch (alt62) {
                case 1 :
                    // InternalModel2Blockly.g:4497:3: rule__ValueInput__Group_3__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ValueInput__Group_3__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getValueInputAccess().getGroup_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ValueInput__Group__3__Impl"


    // $ANTLR start "rule__ValueInput__Group__4"
    // InternalModel2Blockly.g:4505:1: rule__ValueInput__Group__4 : rule__ValueInput__Group__4__Impl ;
    public final void rule__ValueInput__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4509:1: ( rule__ValueInput__Group__4__Impl )
            // InternalModel2Blockly.g:4510:2: rule__ValueInput__Group__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ValueInput__Group__4__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ValueInput__Group__4"


    // $ANTLR start "rule__ValueInput__Group__4__Impl"
    // InternalModel2Blockly.g:4516:1: rule__ValueInput__Group__4__Impl : ( ( rule__ValueInput__UiAssignment_4 )? ) ;
    public final void rule__ValueInput__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4520:1: ( ( ( rule__ValueInput__UiAssignment_4 )? ) )
            // InternalModel2Blockly.g:4521:1: ( ( rule__ValueInput__UiAssignment_4 )? )
            {
            // InternalModel2Blockly.g:4521:1: ( ( rule__ValueInput__UiAssignment_4 )? )
            // InternalModel2Blockly.g:4522:2: ( rule__ValueInput__UiAssignment_4 )?
            {
             before(grammarAccess.getValueInputAccess().getUiAssignment_4()); 
            // InternalModel2Blockly.g:4523:2: ( rule__ValueInput__UiAssignment_4 )?
            int alt63=2;
            int LA63_0 = input.LA(1);

            if ( ((LA63_0>=71 && LA63_0<=78)||(LA63_0>=98 && LA63_0<=99)) ) {
                alt63=1;
            }
            switch (alt63) {
                case 1 :
                    // InternalModel2Blockly.g:4523:3: rule__ValueInput__UiAssignment_4
                    {
                    pushFollow(FOLLOW_2);
                    rule__ValueInput__UiAssignment_4();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getValueInputAccess().getUiAssignment_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ValueInput__Group__4__Impl"


    // $ANTLR start "rule__ValueInput__Group_3__0"
    // InternalModel2Blockly.g:4532:1: rule__ValueInput__Group_3__0 : rule__ValueInput__Group_3__0__Impl rule__ValueInput__Group_3__1 ;
    public final void rule__ValueInput__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4536:1: ( rule__ValueInput__Group_3__0__Impl rule__ValueInput__Group_3__1 )
            // InternalModel2Blockly.g:4537:2: rule__ValueInput__Group_3__0__Impl rule__ValueInput__Group_3__1
            {
            pushFollow(FOLLOW_4);
            rule__ValueInput__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ValueInput__Group_3__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ValueInput__Group_3__0"


    // $ANTLR start "rule__ValueInput__Group_3__0__Impl"
    // InternalModel2Blockly.g:4544:1: rule__ValueInput__Group_3__0__Impl : ( 'shadow' ) ;
    public final void rule__ValueInput__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4548:1: ( ( 'shadow' ) )
            // InternalModel2Blockly.g:4549:1: ( 'shadow' )
            {
            // InternalModel2Blockly.g:4549:1: ( 'shadow' )
            // InternalModel2Blockly.g:4550:2: 'shadow'
            {
             before(grammarAccess.getValueInputAccess().getShadowKeyword_3_0()); 
            match(input,70,FOLLOW_2); 
             after(grammarAccess.getValueInputAccess().getShadowKeyword_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ValueInput__Group_3__0__Impl"


    // $ANTLR start "rule__ValueInput__Group_3__1"
    // InternalModel2Blockly.g:4559:1: rule__ValueInput__Group_3__1 : rule__ValueInput__Group_3__1__Impl ;
    public final void rule__ValueInput__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4563:1: ( rule__ValueInput__Group_3__1__Impl )
            // InternalModel2Blockly.g:4564:2: rule__ValueInput__Group_3__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ValueInput__Group_3__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ValueInput__Group_3__1"


    // $ANTLR start "rule__ValueInput__Group_3__1__Impl"
    // InternalModel2Blockly.g:4570:1: rule__ValueInput__Group_3__1__Impl : ( ( rule__ValueInput__ShadowTypeAssignment_3_1 ) ) ;
    public final void rule__ValueInput__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4574:1: ( ( ( rule__ValueInput__ShadowTypeAssignment_3_1 ) ) )
            // InternalModel2Blockly.g:4575:1: ( ( rule__ValueInput__ShadowTypeAssignment_3_1 ) )
            {
            // InternalModel2Blockly.g:4575:1: ( ( rule__ValueInput__ShadowTypeAssignment_3_1 ) )
            // InternalModel2Blockly.g:4576:2: ( rule__ValueInput__ShadowTypeAssignment_3_1 )
            {
             before(grammarAccess.getValueInputAccess().getShadowTypeAssignment_3_1()); 
            // InternalModel2Blockly.g:4577:2: ( rule__ValueInput__ShadowTypeAssignment_3_1 )
            // InternalModel2Blockly.g:4577:3: rule__ValueInput__ShadowTypeAssignment_3_1
            {
            pushFollow(FOLLOW_2);
            rule__ValueInput__ShadowTypeAssignment_3_1();

            state._fsp--;


            }

             after(grammarAccess.getValueInputAccess().getShadowTypeAssignment_3_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ValueInput__Group_3__1__Impl"


    // $ANTLR start "rule__Cardinality__Group__0"
    // InternalModel2Blockly.g:4586:1: rule__Cardinality__Group__0 : rule__Cardinality__Group__0__Impl rule__Cardinality__Group__1 ;
    public final void rule__Cardinality__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4590:1: ( rule__Cardinality__Group__0__Impl rule__Cardinality__Group__1 )
            // InternalModel2Blockly.g:4591:2: rule__Cardinality__Group__0__Impl rule__Cardinality__Group__1
            {
            pushFollow(FOLLOW_12);
            rule__Cardinality__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Cardinality__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Cardinality__Group__0"


    // $ANTLR start "rule__Cardinality__Group__0__Impl"
    // InternalModel2Blockly.g:4598:1: rule__Cardinality__Group__0__Impl : ( '[' ) ;
    public final void rule__Cardinality__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4602:1: ( ( '[' ) )
            // InternalModel2Blockly.g:4603:1: ( '[' )
            {
            // InternalModel2Blockly.g:4603:1: ( '[' )
            // InternalModel2Blockly.g:4604:2: '['
            {
             before(grammarAccess.getCardinalityAccess().getLeftSquareBracketKeyword_0()); 
            match(input,64,FOLLOW_2); 
             after(grammarAccess.getCardinalityAccess().getLeftSquareBracketKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Cardinality__Group__0__Impl"


    // $ANTLR start "rule__Cardinality__Group__1"
    // InternalModel2Blockly.g:4613:1: rule__Cardinality__Group__1 : rule__Cardinality__Group__1__Impl rule__Cardinality__Group__2 ;
    public final void rule__Cardinality__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4617:1: ( rule__Cardinality__Group__1__Impl rule__Cardinality__Group__2 )
            // InternalModel2Blockly.g:4618:2: rule__Cardinality__Group__1__Impl rule__Cardinality__Group__2
            {
            pushFollow(FOLLOW_24);
            rule__Cardinality__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Cardinality__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Cardinality__Group__1"


    // $ANTLR start "rule__Cardinality__Group__1__Impl"
    // InternalModel2Blockly.g:4625:1: rule__Cardinality__Group__1__Impl : ( ( rule__Cardinality__LowerAssignment_1 ) ) ;
    public final void rule__Cardinality__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4629:1: ( ( ( rule__Cardinality__LowerAssignment_1 ) ) )
            // InternalModel2Blockly.g:4630:1: ( ( rule__Cardinality__LowerAssignment_1 ) )
            {
            // InternalModel2Blockly.g:4630:1: ( ( rule__Cardinality__LowerAssignment_1 ) )
            // InternalModel2Blockly.g:4631:2: ( rule__Cardinality__LowerAssignment_1 )
            {
             before(grammarAccess.getCardinalityAccess().getLowerAssignment_1()); 
            // InternalModel2Blockly.g:4632:2: ( rule__Cardinality__LowerAssignment_1 )
            // InternalModel2Blockly.g:4632:3: rule__Cardinality__LowerAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__Cardinality__LowerAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getCardinalityAccess().getLowerAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Cardinality__Group__1__Impl"


    // $ANTLR start "rule__Cardinality__Group__2"
    // InternalModel2Blockly.g:4640:1: rule__Cardinality__Group__2 : rule__Cardinality__Group__2__Impl rule__Cardinality__Group__3 ;
    public final void rule__Cardinality__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4644:1: ( rule__Cardinality__Group__2__Impl rule__Cardinality__Group__3 )
            // InternalModel2Blockly.g:4645:2: rule__Cardinality__Group__2__Impl rule__Cardinality__Group__3
            {
            pushFollow(FOLLOW_12);
            rule__Cardinality__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Cardinality__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Cardinality__Group__2"


    // $ANTLR start "rule__Cardinality__Group__2__Impl"
    // InternalModel2Blockly.g:4652:1: rule__Cardinality__Group__2__Impl : ( '..' ) ;
    public final void rule__Cardinality__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4656:1: ( ( '..' ) )
            // InternalModel2Blockly.g:4657:1: ( '..' )
            {
            // InternalModel2Blockly.g:4657:1: ( '..' )
            // InternalModel2Blockly.g:4658:2: '..'
            {
             before(grammarAccess.getCardinalityAccess().getFullStopFullStopKeyword_2()); 
            match(input,65,FOLLOW_2); 
             after(grammarAccess.getCardinalityAccess().getFullStopFullStopKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Cardinality__Group__2__Impl"


    // $ANTLR start "rule__Cardinality__Group__3"
    // InternalModel2Blockly.g:4667:1: rule__Cardinality__Group__3 : rule__Cardinality__Group__3__Impl rule__Cardinality__Group__4 ;
    public final void rule__Cardinality__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4671:1: ( rule__Cardinality__Group__3__Impl rule__Cardinality__Group__4 )
            // InternalModel2Blockly.g:4672:2: rule__Cardinality__Group__3__Impl rule__Cardinality__Group__4
            {
            pushFollow(FOLLOW_25);
            rule__Cardinality__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Cardinality__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Cardinality__Group__3"


    // $ANTLR start "rule__Cardinality__Group__3__Impl"
    // InternalModel2Blockly.g:4679:1: rule__Cardinality__Group__3__Impl : ( ( rule__Cardinality__UpperAssignment_3 ) ) ;
    public final void rule__Cardinality__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4683:1: ( ( ( rule__Cardinality__UpperAssignment_3 ) ) )
            // InternalModel2Blockly.g:4684:1: ( ( rule__Cardinality__UpperAssignment_3 ) )
            {
            // InternalModel2Blockly.g:4684:1: ( ( rule__Cardinality__UpperAssignment_3 ) )
            // InternalModel2Blockly.g:4685:2: ( rule__Cardinality__UpperAssignment_3 )
            {
             before(grammarAccess.getCardinalityAccess().getUpperAssignment_3()); 
            // InternalModel2Blockly.g:4686:2: ( rule__Cardinality__UpperAssignment_3 )
            // InternalModel2Blockly.g:4686:3: rule__Cardinality__UpperAssignment_3
            {
            pushFollow(FOLLOW_2);
            rule__Cardinality__UpperAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getCardinalityAccess().getUpperAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Cardinality__Group__3__Impl"


    // $ANTLR start "rule__Cardinality__Group__4"
    // InternalModel2Blockly.g:4694:1: rule__Cardinality__Group__4 : rule__Cardinality__Group__4__Impl ;
    public final void rule__Cardinality__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4698:1: ( rule__Cardinality__Group__4__Impl )
            // InternalModel2Blockly.g:4699:2: rule__Cardinality__Group__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Cardinality__Group__4__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Cardinality__Group__4"


    // $ANTLR start "rule__Cardinality__Group__4__Impl"
    // InternalModel2Blockly.g:4705:1: rule__Cardinality__Group__4__Impl : ( ']' ) ;
    public final void rule__Cardinality__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4709:1: ( ( ']' ) )
            // InternalModel2Blockly.g:4710:1: ( ']' )
            {
            // InternalModel2Blockly.g:4710:1: ( ']' )
            // InternalModel2Blockly.g:4711:2: ']'
            {
             before(grammarAccess.getCardinalityAccess().getRightSquareBracketKeyword_4()); 
            match(input,66,FOLLOW_2); 
             after(grammarAccess.getCardinalityAccess().getRightSquareBracketKeyword_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Cardinality__Group__4__Impl"


    // $ANTLR start "rule__UiOptions__Group_0__0"
    // InternalModel2Blockly.g:4721:1: rule__UiOptions__Group_0__0 : rule__UiOptions__Group_0__0__Impl rule__UiOptions__Group_0__1 ;
    public final void rule__UiOptions__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4725:1: ( rule__UiOptions__Group_0__0__Impl rule__UiOptions__Group_0__1 )
            // InternalModel2Blockly.g:4726:2: rule__UiOptions__Group_0__0__Impl rule__UiOptions__Group_0__1
            {
            pushFollow(FOLLOW_28);
            rule__UiOptions__Group_0__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__UiOptions__Group_0__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UiOptions__Group_0__0"


    // $ANTLR start "rule__UiOptions__Group_0__0__Impl"
    // InternalModel2Blockly.g:4733:1: rule__UiOptions__Group_0__0__Impl : ( 'widget' ) ;
    public final void rule__UiOptions__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4737:1: ( ( 'widget' ) )
            // InternalModel2Blockly.g:4738:1: ( 'widget' )
            {
            // InternalModel2Blockly.g:4738:1: ( 'widget' )
            // InternalModel2Blockly.g:4739:2: 'widget'
            {
             before(grammarAccess.getUiOptionsAccess().getWidgetKeyword_0_0()); 
            match(input,71,FOLLOW_2); 
             after(grammarAccess.getUiOptionsAccess().getWidgetKeyword_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UiOptions__Group_0__0__Impl"


    // $ANTLR start "rule__UiOptions__Group_0__1"
    // InternalModel2Blockly.g:4748:1: rule__UiOptions__Group_0__1 : rule__UiOptions__Group_0__1__Impl ;
    public final void rule__UiOptions__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4752:1: ( rule__UiOptions__Group_0__1__Impl )
            // InternalModel2Blockly.g:4753:2: rule__UiOptions__Group_0__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__UiOptions__Group_0__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UiOptions__Group_0__1"


    // $ANTLR start "rule__UiOptions__Group_0__1__Impl"
    // InternalModel2Blockly.g:4759:1: rule__UiOptions__Group_0__1__Impl : ( ( rule__UiOptions__WidgetAssignment_0_1 ) ) ;
    public final void rule__UiOptions__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4763:1: ( ( ( rule__UiOptions__WidgetAssignment_0_1 ) ) )
            // InternalModel2Blockly.g:4764:1: ( ( rule__UiOptions__WidgetAssignment_0_1 ) )
            {
            // InternalModel2Blockly.g:4764:1: ( ( rule__UiOptions__WidgetAssignment_0_1 ) )
            // InternalModel2Blockly.g:4765:2: ( rule__UiOptions__WidgetAssignment_0_1 )
            {
             before(grammarAccess.getUiOptionsAccess().getWidgetAssignment_0_1()); 
            // InternalModel2Blockly.g:4766:2: ( rule__UiOptions__WidgetAssignment_0_1 )
            // InternalModel2Blockly.g:4766:3: rule__UiOptions__WidgetAssignment_0_1
            {
            pushFollow(FOLLOW_2);
            rule__UiOptions__WidgetAssignment_0_1();

            state._fsp--;


            }

             after(grammarAccess.getUiOptionsAccess().getWidgetAssignment_0_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UiOptions__Group_0__1__Impl"


    // $ANTLR start "rule__UiOptions__Group_1__0"
    // InternalModel2Blockly.g:4775:1: rule__UiOptions__Group_1__0 : rule__UiOptions__Group_1__0__Impl rule__UiOptions__Group_1__1 ;
    public final void rule__UiOptions__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4779:1: ( rule__UiOptions__Group_1__0__Impl rule__UiOptions__Group_1__1 )
            // InternalModel2Blockly.g:4780:2: rule__UiOptions__Group_1__0__Impl rule__UiOptions__Group_1__1
            {
            pushFollow(FOLLOW_10);
            rule__UiOptions__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__UiOptions__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UiOptions__Group_1__0"


    // $ANTLR start "rule__UiOptions__Group_1__0__Impl"
    // InternalModel2Blockly.g:4787:1: rule__UiOptions__Group_1__0__Impl : ( 'uiLabel' ) ;
    public final void rule__UiOptions__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4791:1: ( ( 'uiLabel' ) )
            // InternalModel2Blockly.g:4792:1: ( 'uiLabel' )
            {
            // InternalModel2Blockly.g:4792:1: ( 'uiLabel' )
            // InternalModel2Blockly.g:4793:2: 'uiLabel'
            {
             before(grammarAccess.getUiOptionsAccess().getUiLabelKeyword_1_0()); 
            match(input,72,FOLLOW_2); 
             after(grammarAccess.getUiOptionsAccess().getUiLabelKeyword_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UiOptions__Group_1__0__Impl"


    // $ANTLR start "rule__UiOptions__Group_1__1"
    // InternalModel2Blockly.g:4802:1: rule__UiOptions__Group_1__1 : rule__UiOptions__Group_1__1__Impl ;
    public final void rule__UiOptions__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4806:1: ( rule__UiOptions__Group_1__1__Impl )
            // InternalModel2Blockly.g:4807:2: rule__UiOptions__Group_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__UiOptions__Group_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UiOptions__Group_1__1"


    // $ANTLR start "rule__UiOptions__Group_1__1__Impl"
    // InternalModel2Blockly.g:4813:1: rule__UiOptions__Group_1__1__Impl : ( ( rule__UiOptions__UiLabelAssignment_1_1 ) ) ;
    public final void rule__UiOptions__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4817:1: ( ( ( rule__UiOptions__UiLabelAssignment_1_1 ) ) )
            // InternalModel2Blockly.g:4818:1: ( ( rule__UiOptions__UiLabelAssignment_1_1 ) )
            {
            // InternalModel2Blockly.g:4818:1: ( ( rule__UiOptions__UiLabelAssignment_1_1 ) )
            // InternalModel2Blockly.g:4819:2: ( rule__UiOptions__UiLabelAssignment_1_1 )
            {
             before(grammarAccess.getUiOptionsAccess().getUiLabelAssignment_1_1()); 
            // InternalModel2Blockly.g:4820:2: ( rule__UiOptions__UiLabelAssignment_1_1 )
            // InternalModel2Blockly.g:4820:3: rule__UiOptions__UiLabelAssignment_1_1
            {
            pushFollow(FOLLOW_2);
            rule__UiOptions__UiLabelAssignment_1_1();

            state._fsp--;


            }

             after(grammarAccess.getUiOptionsAccess().getUiLabelAssignment_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UiOptions__Group_1__1__Impl"


    // $ANTLR start "rule__UiOptions__Group_2__0"
    // InternalModel2Blockly.g:4829:1: rule__UiOptions__Group_2__0 : rule__UiOptions__Group_2__0__Impl rule__UiOptions__Group_2__1 ;
    public final void rule__UiOptions__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4833:1: ( rule__UiOptions__Group_2__0__Impl rule__UiOptions__Group_2__1 )
            // InternalModel2Blockly.g:4834:2: rule__UiOptions__Group_2__0__Impl rule__UiOptions__Group_2__1
            {
            pushFollow(FOLLOW_10);
            rule__UiOptions__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__UiOptions__Group_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UiOptions__Group_2__0"


    // $ANTLR start "rule__UiOptions__Group_2__0__Impl"
    // InternalModel2Blockly.g:4841:1: rule__UiOptions__Group_2__0__Impl : ( 'help' ) ;
    public final void rule__UiOptions__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4845:1: ( ( 'help' ) )
            // InternalModel2Blockly.g:4846:1: ( 'help' )
            {
            // InternalModel2Blockly.g:4846:1: ( 'help' )
            // InternalModel2Blockly.g:4847:2: 'help'
            {
             before(grammarAccess.getUiOptionsAccess().getHelpKeyword_2_0()); 
            match(input,73,FOLLOW_2); 
             after(grammarAccess.getUiOptionsAccess().getHelpKeyword_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UiOptions__Group_2__0__Impl"


    // $ANTLR start "rule__UiOptions__Group_2__1"
    // InternalModel2Blockly.g:4856:1: rule__UiOptions__Group_2__1 : rule__UiOptions__Group_2__1__Impl ;
    public final void rule__UiOptions__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4860:1: ( rule__UiOptions__Group_2__1__Impl )
            // InternalModel2Blockly.g:4861:2: rule__UiOptions__Group_2__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__UiOptions__Group_2__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UiOptions__Group_2__1"


    // $ANTLR start "rule__UiOptions__Group_2__1__Impl"
    // InternalModel2Blockly.g:4867:1: rule__UiOptions__Group_2__1__Impl : ( ( rule__UiOptions__HelpAssignment_2_1 ) ) ;
    public final void rule__UiOptions__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4871:1: ( ( ( rule__UiOptions__HelpAssignment_2_1 ) ) )
            // InternalModel2Blockly.g:4872:1: ( ( rule__UiOptions__HelpAssignment_2_1 ) )
            {
            // InternalModel2Blockly.g:4872:1: ( ( rule__UiOptions__HelpAssignment_2_1 ) )
            // InternalModel2Blockly.g:4873:2: ( rule__UiOptions__HelpAssignment_2_1 )
            {
             before(grammarAccess.getUiOptionsAccess().getHelpAssignment_2_1()); 
            // InternalModel2Blockly.g:4874:2: ( rule__UiOptions__HelpAssignment_2_1 )
            // InternalModel2Blockly.g:4874:3: rule__UiOptions__HelpAssignment_2_1
            {
            pushFollow(FOLLOW_2);
            rule__UiOptions__HelpAssignment_2_1();

            state._fsp--;


            }

             after(grammarAccess.getUiOptionsAccess().getHelpAssignment_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UiOptions__Group_2__1__Impl"


    // $ANTLR start "rule__UiOptions__Group_3__0"
    // InternalModel2Blockly.g:4883:1: rule__UiOptions__Group_3__0 : rule__UiOptions__Group_3__0__Impl rule__UiOptions__Group_3__1 ;
    public final void rule__UiOptions__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4887:1: ( rule__UiOptions__Group_3__0__Impl rule__UiOptions__Group_3__1 )
            // InternalModel2Blockly.g:4888:2: rule__UiOptions__Group_3__0__Impl rule__UiOptions__Group_3__1
            {
            pushFollow(FOLLOW_10);
            rule__UiOptions__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__UiOptions__Group_3__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UiOptions__Group_3__0"


    // $ANTLR start "rule__UiOptions__Group_3__0__Impl"
    // InternalModel2Blockly.g:4895:1: rule__UiOptions__Group_3__0__Impl : ( 'placeholder' ) ;
    public final void rule__UiOptions__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4899:1: ( ( 'placeholder' ) )
            // InternalModel2Blockly.g:4900:1: ( 'placeholder' )
            {
            // InternalModel2Blockly.g:4900:1: ( 'placeholder' )
            // InternalModel2Blockly.g:4901:2: 'placeholder'
            {
             before(grammarAccess.getUiOptionsAccess().getPlaceholderKeyword_3_0()); 
            match(input,74,FOLLOW_2); 
             after(grammarAccess.getUiOptionsAccess().getPlaceholderKeyword_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UiOptions__Group_3__0__Impl"


    // $ANTLR start "rule__UiOptions__Group_3__1"
    // InternalModel2Blockly.g:4910:1: rule__UiOptions__Group_3__1 : rule__UiOptions__Group_3__1__Impl ;
    public final void rule__UiOptions__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4914:1: ( rule__UiOptions__Group_3__1__Impl )
            // InternalModel2Blockly.g:4915:2: rule__UiOptions__Group_3__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__UiOptions__Group_3__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UiOptions__Group_3__1"


    // $ANTLR start "rule__UiOptions__Group_3__1__Impl"
    // InternalModel2Blockly.g:4921:1: rule__UiOptions__Group_3__1__Impl : ( ( rule__UiOptions__PlaceholderAssignment_3_1 ) ) ;
    public final void rule__UiOptions__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4925:1: ( ( ( rule__UiOptions__PlaceholderAssignment_3_1 ) ) )
            // InternalModel2Blockly.g:4926:1: ( ( rule__UiOptions__PlaceholderAssignment_3_1 ) )
            {
            // InternalModel2Blockly.g:4926:1: ( ( rule__UiOptions__PlaceholderAssignment_3_1 ) )
            // InternalModel2Blockly.g:4927:2: ( rule__UiOptions__PlaceholderAssignment_3_1 )
            {
             before(grammarAccess.getUiOptionsAccess().getPlaceholderAssignment_3_1()); 
            // InternalModel2Blockly.g:4928:2: ( rule__UiOptions__PlaceholderAssignment_3_1 )
            // InternalModel2Blockly.g:4928:3: rule__UiOptions__PlaceholderAssignment_3_1
            {
            pushFollow(FOLLOW_2);
            rule__UiOptions__PlaceholderAssignment_3_1();

            state._fsp--;


            }

             after(grammarAccess.getUiOptionsAccess().getPlaceholderAssignment_3_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UiOptions__Group_3__1__Impl"


    // $ANTLR start "rule__UiOptions__Group_4__0"
    // InternalModel2Blockly.g:4937:1: rule__UiOptions__Group_4__0 : rule__UiOptions__Group_4__0__Impl rule__UiOptions__Group_4__1 ;
    public final void rule__UiOptions__Group_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4941:1: ( rule__UiOptions__Group_4__0__Impl rule__UiOptions__Group_4__1 )
            // InternalModel2Blockly.g:4942:2: rule__UiOptions__Group_4__0__Impl rule__UiOptions__Group_4__1
            {
            pushFollow(FOLLOW_10);
            rule__UiOptions__Group_4__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__UiOptions__Group_4__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UiOptions__Group_4__0"


    // $ANTLR start "rule__UiOptions__Group_4__0__Impl"
    // InternalModel2Blockly.g:4949:1: rule__UiOptions__Group_4__0__Impl : ( 'group' ) ;
    public final void rule__UiOptions__Group_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4953:1: ( ( 'group' ) )
            // InternalModel2Blockly.g:4954:1: ( 'group' )
            {
            // InternalModel2Blockly.g:4954:1: ( 'group' )
            // InternalModel2Blockly.g:4955:2: 'group'
            {
             before(grammarAccess.getUiOptionsAccess().getGroupKeyword_4_0()); 
            match(input,75,FOLLOW_2); 
             after(grammarAccess.getUiOptionsAccess().getGroupKeyword_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UiOptions__Group_4__0__Impl"


    // $ANTLR start "rule__UiOptions__Group_4__1"
    // InternalModel2Blockly.g:4964:1: rule__UiOptions__Group_4__1 : rule__UiOptions__Group_4__1__Impl ;
    public final void rule__UiOptions__Group_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4968:1: ( rule__UiOptions__Group_4__1__Impl )
            // InternalModel2Blockly.g:4969:2: rule__UiOptions__Group_4__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__UiOptions__Group_4__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UiOptions__Group_4__1"


    // $ANTLR start "rule__UiOptions__Group_4__1__Impl"
    // InternalModel2Blockly.g:4975:1: rule__UiOptions__Group_4__1__Impl : ( ( rule__UiOptions__GroupAssignment_4_1 ) ) ;
    public final void rule__UiOptions__Group_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4979:1: ( ( ( rule__UiOptions__GroupAssignment_4_1 ) ) )
            // InternalModel2Blockly.g:4980:1: ( ( rule__UiOptions__GroupAssignment_4_1 ) )
            {
            // InternalModel2Blockly.g:4980:1: ( ( rule__UiOptions__GroupAssignment_4_1 ) )
            // InternalModel2Blockly.g:4981:2: ( rule__UiOptions__GroupAssignment_4_1 )
            {
             before(grammarAccess.getUiOptionsAccess().getGroupAssignment_4_1()); 
            // InternalModel2Blockly.g:4982:2: ( rule__UiOptions__GroupAssignment_4_1 )
            // InternalModel2Blockly.g:4982:3: rule__UiOptions__GroupAssignment_4_1
            {
            pushFollow(FOLLOW_2);
            rule__UiOptions__GroupAssignment_4_1();

            state._fsp--;


            }

             after(grammarAccess.getUiOptionsAccess().getGroupAssignment_4_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UiOptions__Group_4__1__Impl"


    // $ANTLR start "rule__UiOptions__Group_5__0"
    // InternalModel2Blockly.g:4991:1: rule__UiOptions__Group_5__0 : rule__UiOptions__Group_5__0__Impl rule__UiOptions__Group_5__1 ;
    public final void rule__UiOptions__Group_5__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:4995:1: ( rule__UiOptions__Group_5__0__Impl rule__UiOptions__Group_5__1 )
            // InternalModel2Blockly.g:4996:2: rule__UiOptions__Group_5__0__Impl rule__UiOptions__Group_5__1
            {
            pushFollow(FOLLOW_12);
            rule__UiOptions__Group_5__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__UiOptions__Group_5__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UiOptions__Group_5__0"


    // $ANTLR start "rule__UiOptions__Group_5__0__Impl"
    // InternalModel2Blockly.g:5003:1: rule__UiOptions__Group_5__0__Impl : ( 'order' ) ;
    public final void rule__UiOptions__Group_5__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5007:1: ( ( 'order' ) )
            // InternalModel2Blockly.g:5008:1: ( 'order' )
            {
            // InternalModel2Blockly.g:5008:1: ( 'order' )
            // InternalModel2Blockly.g:5009:2: 'order'
            {
             before(grammarAccess.getUiOptionsAccess().getOrderKeyword_5_0()); 
            match(input,76,FOLLOW_2); 
             after(grammarAccess.getUiOptionsAccess().getOrderKeyword_5_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UiOptions__Group_5__0__Impl"


    // $ANTLR start "rule__UiOptions__Group_5__1"
    // InternalModel2Blockly.g:5018:1: rule__UiOptions__Group_5__1 : rule__UiOptions__Group_5__1__Impl ;
    public final void rule__UiOptions__Group_5__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5022:1: ( rule__UiOptions__Group_5__1__Impl )
            // InternalModel2Blockly.g:5023:2: rule__UiOptions__Group_5__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__UiOptions__Group_5__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UiOptions__Group_5__1"


    // $ANTLR start "rule__UiOptions__Group_5__1__Impl"
    // InternalModel2Blockly.g:5029:1: rule__UiOptions__Group_5__1__Impl : ( ( rule__UiOptions__OrderAssignment_5_1 ) ) ;
    public final void rule__UiOptions__Group_5__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5033:1: ( ( ( rule__UiOptions__OrderAssignment_5_1 ) ) )
            // InternalModel2Blockly.g:5034:1: ( ( rule__UiOptions__OrderAssignment_5_1 ) )
            {
            // InternalModel2Blockly.g:5034:1: ( ( rule__UiOptions__OrderAssignment_5_1 ) )
            // InternalModel2Blockly.g:5035:2: ( rule__UiOptions__OrderAssignment_5_1 )
            {
             before(grammarAccess.getUiOptionsAccess().getOrderAssignment_5_1()); 
            // InternalModel2Blockly.g:5036:2: ( rule__UiOptions__OrderAssignment_5_1 )
            // InternalModel2Blockly.g:5036:3: rule__UiOptions__OrderAssignment_5_1
            {
            pushFollow(FOLLOW_2);
            rule__UiOptions__OrderAssignment_5_1();

            state._fsp--;


            }

             after(grammarAccess.getUiOptionsAccess().getOrderAssignment_5_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UiOptions__Group_5__1__Impl"


    // $ANTLR start "rule__UiOptions__Group_8__0"
    // InternalModel2Blockly.g:5045:1: rule__UiOptions__Group_8__0 : rule__UiOptions__Group_8__0__Impl rule__UiOptions__Group_8__1 ;
    public final void rule__UiOptions__Group_8__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5049:1: ( rule__UiOptions__Group_8__0__Impl rule__UiOptions__Group_8__1 )
            // InternalModel2Blockly.g:5050:2: rule__UiOptions__Group_8__0__Impl rule__UiOptions__Group_8__1
            {
            pushFollow(FOLLOW_29);
            rule__UiOptions__Group_8__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__UiOptions__Group_8__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UiOptions__Group_8__0"


    // $ANTLR start "rule__UiOptions__Group_8__0__Impl"
    // InternalModel2Blockly.g:5057:1: rule__UiOptions__Group_8__0__Impl : ( 'variant' ) ;
    public final void rule__UiOptions__Group_8__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5061:1: ( ( 'variant' ) )
            // InternalModel2Blockly.g:5062:1: ( 'variant' )
            {
            // InternalModel2Blockly.g:5062:1: ( 'variant' )
            // InternalModel2Blockly.g:5063:2: 'variant'
            {
             before(grammarAccess.getUiOptionsAccess().getVariantKeyword_8_0()); 
            match(input,77,FOLLOW_2); 
             after(grammarAccess.getUiOptionsAccess().getVariantKeyword_8_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UiOptions__Group_8__0__Impl"


    // $ANTLR start "rule__UiOptions__Group_8__1"
    // InternalModel2Blockly.g:5072:1: rule__UiOptions__Group_8__1 : rule__UiOptions__Group_8__1__Impl ;
    public final void rule__UiOptions__Group_8__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5076:1: ( rule__UiOptions__Group_8__1__Impl )
            // InternalModel2Blockly.g:5077:2: rule__UiOptions__Group_8__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__UiOptions__Group_8__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UiOptions__Group_8__1"


    // $ANTLR start "rule__UiOptions__Group_8__1__Impl"
    // InternalModel2Blockly.g:5083:1: rule__UiOptions__Group_8__1__Impl : ( ( rule__UiOptions__VariantAssignment_8_1 ) ) ;
    public final void rule__UiOptions__Group_8__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5087:1: ( ( ( rule__UiOptions__VariantAssignment_8_1 ) ) )
            // InternalModel2Blockly.g:5088:1: ( ( rule__UiOptions__VariantAssignment_8_1 ) )
            {
            // InternalModel2Blockly.g:5088:1: ( ( rule__UiOptions__VariantAssignment_8_1 ) )
            // InternalModel2Blockly.g:5089:2: ( rule__UiOptions__VariantAssignment_8_1 )
            {
             before(grammarAccess.getUiOptionsAccess().getVariantAssignment_8_1()); 
            // InternalModel2Blockly.g:5090:2: ( rule__UiOptions__VariantAssignment_8_1 )
            // InternalModel2Blockly.g:5090:3: rule__UiOptions__VariantAssignment_8_1
            {
            pushFollow(FOLLOW_2);
            rule__UiOptions__VariantAssignment_8_1();

            state._fsp--;


            }

             after(grammarAccess.getUiOptionsAccess().getVariantAssignment_8_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UiOptions__Group_8__1__Impl"


    // $ANTLR start "rule__UiOptions__Group_9__0"
    // InternalModel2Blockly.g:5099:1: rule__UiOptions__Group_9__0 : rule__UiOptions__Group_9__0__Impl rule__UiOptions__Group_9__1 ;
    public final void rule__UiOptions__Group_9__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5103:1: ( rule__UiOptions__Group_9__0__Impl rule__UiOptions__Group_9__1 )
            // InternalModel2Blockly.g:5104:2: rule__UiOptions__Group_9__0__Impl rule__UiOptions__Group_9__1
            {
            pushFollow(FOLLOW_4);
            rule__UiOptions__Group_9__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__UiOptions__Group_9__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UiOptions__Group_9__0"


    // $ANTLR start "rule__UiOptions__Group_9__0__Impl"
    // InternalModel2Blockly.g:5111:1: rule__UiOptions__Group_9__0__Impl : ( 'referenceLabelField' ) ;
    public final void rule__UiOptions__Group_9__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5115:1: ( ( 'referenceLabelField' ) )
            // InternalModel2Blockly.g:5116:1: ( 'referenceLabelField' )
            {
            // InternalModel2Blockly.g:5116:1: ( 'referenceLabelField' )
            // InternalModel2Blockly.g:5117:2: 'referenceLabelField'
            {
             before(grammarAccess.getUiOptionsAccess().getReferenceLabelFieldKeyword_9_0()); 
            match(input,78,FOLLOW_2); 
             after(grammarAccess.getUiOptionsAccess().getReferenceLabelFieldKeyword_9_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UiOptions__Group_9__0__Impl"


    // $ANTLR start "rule__UiOptions__Group_9__1"
    // InternalModel2Blockly.g:5126:1: rule__UiOptions__Group_9__1 : rule__UiOptions__Group_9__1__Impl ;
    public final void rule__UiOptions__Group_9__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5130:1: ( rule__UiOptions__Group_9__1__Impl )
            // InternalModel2Blockly.g:5131:2: rule__UiOptions__Group_9__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__UiOptions__Group_9__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UiOptions__Group_9__1"


    // $ANTLR start "rule__UiOptions__Group_9__1__Impl"
    // InternalModel2Blockly.g:5137:1: rule__UiOptions__Group_9__1__Impl : ( ( rule__UiOptions__ReferenceLabelFieldAssignment_9_1 ) ) ;
    public final void rule__UiOptions__Group_9__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5141:1: ( ( ( rule__UiOptions__ReferenceLabelFieldAssignment_9_1 ) ) )
            // InternalModel2Blockly.g:5142:1: ( ( rule__UiOptions__ReferenceLabelFieldAssignment_9_1 ) )
            {
            // InternalModel2Blockly.g:5142:1: ( ( rule__UiOptions__ReferenceLabelFieldAssignment_9_1 ) )
            // InternalModel2Blockly.g:5143:2: ( rule__UiOptions__ReferenceLabelFieldAssignment_9_1 )
            {
             before(grammarAccess.getUiOptionsAccess().getReferenceLabelFieldAssignment_9_1()); 
            // InternalModel2Blockly.g:5144:2: ( rule__UiOptions__ReferenceLabelFieldAssignment_9_1 )
            // InternalModel2Blockly.g:5144:3: rule__UiOptions__ReferenceLabelFieldAssignment_9_1
            {
            pushFollow(FOLLOW_2);
            rule__UiOptions__ReferenceLabelFieldAssignment_9_1();

            state._fsp--;


            }

             after(grammarAccess.getUiOptionsAccess().getReferenceLabelFieldAssignment_9_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UiOptions__Group_9__1__Impl"


    // $ANTLR start "rule__EnumType__Group__0"
    // InternalModel2Blockly.g:5153:1: rule__EnumType__Group__0 : rule__EnumType__Group__0__Impl rule__EnumType__Group__1 ;
    public final void rule__EnumType__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5157:1: ( rule__EnumType__Group__0__Impl rule__EnumType__Group__1 )
            // InternalModel2Blockly.g:5158:2: rule__EnumType__Group__0__Impl rule__EnumType__Group__1
            {
            pushFollow(FOLLOW_30);
            rule__EnumType__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__EnumType__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EnumType__Group__0"


    // $ANTLR start "rule__EnumType__Group__0__Impl"
    // InternalModel2Blockly.g:5165:1: rule__EnumType__Group__0__Impl : ( 'enum' ) ;
    public final void rule__EnumType__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5169:1: ( ( 'enum' ) )
            // InternalModel2Blockly.g:5170:1: ( 'enum' )
            {
            // InternalModel2Blockly.g:5170:1: ( 'enum' )
            // InternalModel2Blockly.g:5171:2: 'enum'
            {
             before(grammarAccess.getEnumTypeAccess().getEnumKeyword_0()); 
            match(input,79,FOLLOW_2); 
             after(grammarAccess.getEnumTypeAccess().getEnumKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EnumType__Group__0__Impl"


    // $ANTLR start "rule__EnumType__Group__1"
    // InternalModel2Blockly.g:5180:1: rule__EnumType__Group__1 : rule__EnumType__Group__1__Impl rule__EnumType__Group__2 ;
    public final void rule__EnumType__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5184:1: ( rule__EnumType__Group__1__Impl rule__EnumType__Group__2 )
            // InternalModel2Blockly.g:5185:2: rule__EnumType__Group__1__Impl rule__EnumType__Group__2
            {
            pushFollow(FOLLOW_4);
            rule__EnumType__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__EnumType__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EnumType__Group__1"


    // $ANTLR start "rule__EnumType__Group__1__Impl"
    // InternalModel2Blockly.g:5192:1: rule__EnumType__Group__1__Impl : ( '{' ) ;
    public final void rule__EnumType__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5196:1: ( ( '{' ) )
            // InternalModel2Blockly.g:5197:1: ( '{' )
            {
            // InternalModel2Blockly.g:5197:1: ( '{' )
            // InternalModel2Blockly.g:5198:2: '{'
            {
             before(grammarAccess.getEnumTypeAccess().getLeftCurlyBracketKeyword_1()); 
            match(input,45,FOLLOW_2); 
             after(grammarAccess.getEnumTypeAccess().getLeftCurlyBracketKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EnumType__Group__1__Impl"


    // $ANTLR start "rule__EnumType__Group__2"
    // InternalModel2Blockly.g:5207:1: rule__EnumType__Group__2 : rule__EnumType__Group__2__Impl rule__EnumType__Group__3 ;
    public final void rule__EnumType__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5211:1: ( rule__EnumType__Group__2__Impl rule__EnumType__Group__3 )
            // InternalModel2Blockly.g:5212:2: rule__EnumType__Group__2__Impl rule__EnumType__Group__3
            {
            pushFollow(FOLLOW_31);
            rule__EnumType__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__EnumType__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EnumType__Group__2"


    // $ANTLR start "rule__EnumType__Group__2__Impl"
    // InternalModel2Blockly.g:5219:1: rule__EnumType__Group__2__Impl : ( ( rule__EnumType__LiteralsAssignment_2 ) ) ;
    public final void rule__EnumType__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5223:1: ( ( ( rule__EnumType__LiteralsAssignment_2 ) ) )
            // InternalModel2Blockly.g:5224:1: ( ( rule__EnumType__LiteralsAssignment_2 ) )
            {
            // InternalModel2Blockly.g:5224:1: ( ( rule__EnumType__LiteralsAssignment_2 ) )
            // InternalModel2Blockly.g:5225:2: ( rule__EnumType__LiteralsAssignment_2 )
            {
             before(grammarAccess.getEnumTypeAccess().getLiteralsAssignment_2()); 
            // InternalModel2Blockly.g:5226:2: ( rule__EnumType__LiteralsAssignment_2 )
            // InternalModel2Blockly.g:5226:3: rule__EnumType__LiteralsAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__EnumType__LiteralsAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getEnumTypeAccess().getLiteralsAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EnumType__Group__2__Impl"


    // $ANTLR start "rule__EnumType__Group__3"
    // InternalModel2Blockly.g:5234:1: rule__EnumType__Group__3 : rule__EnumType__Group__3__Impl rule__EnumType__Group__4 ;
    public final void rule__EnumType__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5238:1: ( rule__EnumType__Group__3__Impl rule__EnumType__Group__4 )
            // InternalModel2Blockly.g:5239:2: rule__EnumType__Group__3__Impl rule__EnumType__Group__4
            {
            pushFollow(FOLLOW_31);
            rule__EnumType__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__EnumType__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EnumType__Group__3"


    // $ANTLR start "rule__EnumType__Group__3__Impl"
    // InternalModel2Blockly.g:5246:1: rule__EnumType__Group__3__Impl : ( ( rule__EnumType__Group_3__0 )* ) ;
    public final void rule__EnumType__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5250:1: ( ( ( rule__EnumType__Group_3__0 )* ) )
            // InternalModel2Blockly.g:5251:1: ( ( rule__EnumType__Group_3__0 )* )
            {
            // InternalModel2Blockly.g:5251:1: ( ( rule__EnumType__Group_3__0 )* )
            // InternalModel2Blockly.g:5252:2: ( rule__EnumType__Group_3__0 )*
            {
             before(grammarAccess.getEnumTypeAccess().getGroup_3()); 
            // InternalModel2Blockly.g:5253:2: ( rule__EnumType__Group_3__0 )*
            loop64:
            do {
                int alt64=2;
                int LA64_0 = input.LA(1);

                if ( (LA64_0==80) ) {
                    alt64=1;
                }


                switch (alt64) {
            	case 1 :
            	    // InternalModel2Blockly.g:5253:3: rule__EnumType__Group_3__0
            	    {
            	    pushFollow(FOLLOW_32);
            	    rule__EnumType__Group_3__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop64;
                }
            } while (true);

             after(grammarAccess.getEnumTypeAccess().getGroup_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EnumType__Group__3__Impl"


    // $ANTLR start "rule__EnumType__Group__4"
    // InternalModel2Blockly.g:5261:1: rule__EnumType__Group__4 : rule__EnumType__Group__4__Impl ;
    public final void rule__EnumType__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5265:1: ( rule__EnumType__Group__4__Impl )
            // InternalModel2Blockly.g:5266:2: rule__EnumType__Group__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__EnumType__Group__4__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EnumType__Group__4"


    // $ANTLR start "rule__EnumType__Group__4__Impl"
    // InternalModel2Blockly.g:5272:1: rule__EnumType__Group__4__Impl : ( '}' ) ;
    public final void rule__EnumType__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5276:1: ( ( '}' ) )
            // InternalModel2Blockly.g:5277:1: ( '}' )
            {
            // InternalModel2Blockly.g:5277:1: ( '}' )
            // InternalModel2Blockly.g:5278:2: '}'
            {
             before(grammarAccess.getEnumTypeAccess().getRightCurlyBracketKeyword_4()); 
            match(input,46,FOLLOW_2); 
             after(grammarAccess.getEnumTypeAccess().getRightCurlyBracketKeyword_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EnumType__Group__4__Impl"


    // $ANTLR start "rule__EnumType__Group_3__0"
    // InternalModel2Blockly.g:5288:1: rule__EnumType__Group_3__0 : rule__EnumType__Group_3__0__Impl rule__EnumType__Group_3__1 ;
    public final void rule__EnumType__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5292:1: ( rule__EnumType__Group_3__0__Impl rule__EnumType__Group_3__1 )
            // InternalModel2Blockly.g:5293:2: rule__EnumType__Group_3__0__Impl rule__EnumType__Group_3__1
            {
            pushFollow(FOLLOW_4);
            rule__EnumType__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__EnumType__Group_3__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EnumType__Group_3__0"


    // $ANTLR start "rule__EnumType__Group_3__0__Impl"
    // InternalModel2Blockly.g:5300:1: rule__EnumType__Group_3__0__Impl : ( ',' ) ;
    public final void rule__EnumType__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5304:1: ( ( ',' ) )
            // InternalModel2Blockly.g:5305:1: ( ',' )
            {
            // InternalModel2Blockly.g:5305:1: ( ',' )
            // InternalModel2Blockly.g:5306:2: ','
            {
             before(grammarAccess.getEnumTypeAccess().getCommaKeyword_3_0()); 
            match(input,80,FOLLOW_2); 
             after(grammarAccess.getEnumTypeAccess().getCommaKeyword_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EnumType__Group_3__0__Impl"


    // $ANTLR start "rule__EnumType__Group_3__1"
    // InternalModel2Blockly.g:5315:1: rule__EnumType__Group_3__1 : rule__EnumType__Group_3__1__Impl ;
    public final void rule__EnumType__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5319:1: ( rule__EnumType__Group_3__1__Impl )
            // InternalModel2Blockly.g:5320:2: rule__EnumType__Group_3__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__EnumType__Group_3__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EnumType__Group_3__1"


    // $ANTLR start "rule__EnumType__Group_3__1__Impl"
    // InternalModel2Blockly.g:5326:1: rule__EnumType__Group_3__1__Impl : ( ( rule__EnumType__LiteralsAssignment_3_1 ) ) ;
    public final void rule__EnumType__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5330:1: ( ( ( rule__EnumType__LiteralsAssignment_3_1 ) ) )
            // InternalModel2Blockly.g:5331:1: ( ( rule__EnumType__LiteralsAssignment_3_1 ) )
            {
            // InternalModel2Blockly.g:5331:1: ( ( rule__EnumType__LiteralsAssignment_3_1 ) )
            // InternalModel2Blockly.g:5332:2: ( rule__EnumType__LiteralsAssignment_3_1 )
            {
             before(grammarAccess.getEnumTypeAccess().getLiteralsAssignment_3_1()); 
            // InternalModel2Blockly.g:5333:2: ( rule__EnumType__LiteralsAssignment_3_1 )
            // InternalModel2Blockly.g:5333:3: rule__EnumType__LiteralsAssignment_3_1
            {
            pushFollow(FOLLOW_2);
            rule__EnumType__LiteralsAssignment_3_1();

            state._fsp--;


            }

             after(grammarAccess.getEnumTypeAccess().getLiteralsAssignment_3_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EnumType__Group_3__1__Impl"


    // $ANTLR start "rule__EnumLiteral__Group__0"
    // InternalModel2Blockly.g:5342:1: rule__EnumLiteral__Group__0 : rule__EnumLiteral__Group__0__Impl rule__EnumLiteral__Group__1 ;
    public final void rule__EnumLiteral__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5346:1: ( rule__EnumLiteral__Group__0__Impl rule__EnumLiteral__Group__1 )
            // InternalModel2Blockly.g:5347:2: rule__EnumLiteral__Group__0__Impl rule__EnumLiteral__Group__1
            {
            pushFollow(FOLLOW_33);
            rule__EnumLiteral__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__EnumLiteral__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EnumLiteral__Group__0"


    // $ANTLR start "rule__EnumLiteral__Group__0__Impl"
    // InternalModel2Blockly.g:5354:1: rule__EnumLiteral__Group__0__Impl : ( ( rule__EnumLiteral__NameAssignment_0 ) ) ;
    public final void rule__EnumLiteral__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5358:1: ( ( ( rule__EnumLiteral__NameAssignment_0 ) ) )
            // InternalModel2Blockly.g:5359:1: ( ( rule__EnumLiteral__NameAssignment_0 ) )
            {
            // InternalModel2Blockly.g:5359:1: ( ( rule__EnumLiteral__NameAssignment_0 ) )
            // InternalModel2Blockly.g:5360:2: ( rule__EnumLiteral__NameAssignment_0 )
            {
             before(grammarAccess.getEnumLiteralAccess().getNameAssignment_0()); 
            // InternalModel2Blockly.g:5361:2: ( rule__EnumLiteral__NameAssignment_0 )
            // InternalModel2Blockly.g:5361:3: rule__EnumLiteral__NameAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__EnumLiteral__NameAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getEnumLiteralAccess().getNameAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EnumLiteral__Group__0__Impl"


    // $ANTLR start "rule__EnumLiteral__Group__1"
    // InternalModel2Blockly.g:5369:1: rule__EnumLiteral__Group__1 : rule__EnumLiteral__Group__1__Impl ;
    public final void rule__EnumLiteral__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5373:1: ( rule__EnumLiteral__Group__1__Impl )
            // InternalModel2Blockly.g:5374:2: rule__EnumLiteral__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__EnumLiteral__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EnumLiteral__Group__1"


    // $ANTLR start "rule__EnumLiteral__Group__1__Impl"
    // InternalModel2Blockly.g:5380:1: rule__EnumLiteral__Group__1__Impl : ( ( rule__EnumLiteral__Group_1__0 )? ) ;
    public final void rule__EnumLiteral__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5384:1: ( ( ( rule__EnumLiteral__Group_1__0 )? ) )
            // InternalModel2Blockly.g:5385:1: ( ( rule__EnumLiteral__Group_1__0 )? )
            {
            // InternalModel2Blockly.g:5385:1: ( ( rule__EnumLiteral__Group_1__0 )? )
            // InternalModel2Blockly.g:5386:2: ( rule__EnumLiteral__Group_1__0 )?
            {
             before(grammarAccess.getEnumLiteralAccess().getGroup_1()); 
            // InternalModel2Blockly.g:5387:2: ( rule__EnumLiteral__Group_1__0 )?
            int alt65=2;
            int LA65_0 = input.LA(1);

            if ( (LA65_0==81) ) {
                alt65=1;
            }
            switch (alt65) {
                case 1 :
                    // InternalModel2Blockly.g:5387:3: rule__EnumLiteral__Group_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__EnumLiteral__Group_1__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getEnumLiteralAccess().getGroup_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EnumLiteral__Group__1__Impl"


    // $ANTLR start "rule__EnumLiteral__Group_1__0"
    // InternalModel2Blockly.g:5396:1: rule__EnumLiteral__Group_1__0 : rule__EnumLiteral__Group_1__0__Impl rule__EnumLiteral__Group_1__1 ;
    public final void rule__EnumLiteral__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5400:1: ( rule__EnumLiteral__Group_1__0__Impl rule__EnumLiteral__Group_1__1 )
            // InternalModel2Blockly.g:5401:2: rule__EnumLiteral__Group_1__0__Impl rule__EnumLiteral__Group_1__1
            {
            pushFollow(FOLLOW_10);
            rule__EnumLiteral__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__EnumLiteral__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EnumLiteral__Group_1__0"


    // $ANTLR start "rule__EnumLiteral__Group_1__0__Impl"
    // InternalModel2Blockly.g:5408:1: rule__EnumLiteral__Group_1__0__Impl : ( '=' ) ;
    public final void rule__EnumLiteral__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5412:1: ( ( '=' ) )
            // InternalModel2Blockly.g:5413:1: ( '=' )
            {
            // InternalModel2Blockly.g:5413:1: ( '=' )
            // InternalModel2Blockly.g:5414:2: '='
            {
             before(grammarAccess.getEnumLiteralAccess().getEqualsSignKeyword_1_0()); 
            match(input,81,FOLLOW_2); 
             after(grammarAccess.getEnumLiteralAccess().getEqualsSignKeyword_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EnumLiteral__Group_1__0__Impl"


    // $ANTLR start "rule__EnumLiteral__Group_1__1"
    // InternalModel2Blockly.g:5423:1: rule__EnumLiteral__Group_1__1 : rule__EnumLiteral__Group_1__1__Impl ;
    public final void rule__EnumLiteral__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5427:1: ( rule__EnumLiteral__Group_1__1__Impl )
            // InternalModel2Blockly.g:5428:2: rule__EnumLiteral__Group_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__EnumLiteral__Group_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EnumLiteral__Group_1__1"


    // $ANTLR start "rule__EnumLiteral__Group_1__1__Impl"
    // InternalModel2Blockly.g:5434:1: rule__EnumLiteral__Group_1__1__Impl : ( ( rule__EnumLiteral__LabelAssignment_1_1 ) ) ;
    public final void rule__EnumLiteral__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5438:1: ( ( ( rule__EnumLiteral__LabelAssignment_1_1 ) ) )
            // InternalModel2Blockly.g:5439:1: ( ( rule__EnumLiteral__LabelAssignment_1_1 ) )
            {
            // InternalModel2Blockly.g:5439:1: ( ( rule__EnumLiteral__LabelAssignment_1_1 ) )
            // InternalModel2Blockly.g:5440:2: ( rule__EnumLiteral__LabelAssignment_1_1 )
            {
             before(grammarAccess.getEnumLiteralAccess().getLabelAssignment_1_1()); 
            // InternalModel2Blockly.g:5441:2: ( rule__EnumLiteral__LabelAssignment_1_1 )
            // InternalModel2Blockly.g:5441:3: rule__EnumLiteral__LabelAssignment_1_1
            {
            pushFollow(FOLLOW_2);
            rule__EnumLiteral__LabelAssignment_1_1();

            state._fsp--;


            }

             after(grammarAccess.getEnumLiteralAccess().getLabelAssignment_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EnumLiteral__Group_1__1__Impl"


    // $ANTLR start "rule__ConstraintDef__Group__0"
    // InternalModel2Blockly.g:5450:1: rule__ConstraintDef__Group__0 : rule__ConstraintDef__Group__0__Impl rule__ConstraintDef__Group__1 ;
    public final void rule__ConstraintDef__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5454:1: ( rule__ConstraintDef__Group__0__Impl rule__ConstraintDef__Group__1 )
            // InternalModel2Blockly.g:5455:2: rule__ConstraintDef__Group__0__Impl rule__ConstraintDef__Group__1
            {
            pushFollow(FOLLOW_4);
            rule__ConstraintDef__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ConstraintDef__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConstraintDef__Group__0"


    // $ANTLR start "rule__ConstraintDef__Group__0__Impl"
    // InternalModel2Blockly.g:5462:1: rule__ConstraintDef__Group__0__Impl : ( 'constraint' ) ;
    public final void rule__ConstraintDef__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5466:1: ( ( 'constraint' ) )
            // InternalModel2Blockly.g:5467:1: ( 'constraint' )
            {
            // InternalModel2Blockly.g:5467:1: ( 'constraint' )
            // InternalModel2Blockly.g:5468:2: 'constraint'
            {
             before(grammarAccess.getConstraintDefAccess().getConstraintKeyword_0()); 
            match(input,82,FOLLOW_2); 
             after(grammarAccess.getConstraintDefAccess().getConstraintKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConstraintDef__Group__0__Impl"


    // $ANTLR start "rule__ConstraintDef__Group__1"
    // InternalModel2Blockly.g:5477:1: rule__ConstraintDef__Group__1 : rule__ConstraintDef__Group__1__Impl rule__ConstraintDef__Group__2 ;
    public final void rule__ConstraintDef__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5481:1: ( rule__ConstraintDef__Group__1__Impl rule__ConstraintDef__Group__2 )
            // InternalModel2Blockly.g:5482:2: rule__ConstraintDef__Group__1__Impl rule__ConstraintDef__Group__2
            {
            pushFollow(FOLLOW_34);
            rule__ConstraintDef__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ConstraintDef__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConstraintDef__Group__1"


    // $ANTLR start "rule__ConstraintDef__Group__1__Impl"
    // InternalModel2Blockly.g:5489:1: rule__ConstraintDef__Group__1__Impl : ( ( rule__ConstraintDef__NameAssignment_1 ) ) ;
    public final void rule__ConstraintDef__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5493:1: ( ( ( rule__ConstraintDef__NameAssignment_1 ) ) )
            // InternalModel2Blockly.g:5494:1: ( ( rule__ConstraintDef__NameAssignment_1 ) )
            {
            // InternalModel2Blockly.g:5494:1: ( ( rule__ConstraintDef__NameAssignment_1 ) )
            // InternalModel2Blockly.g:5495:2: ( rule__ConstraintDef__NameAssignment_1 )
            {
             before(grammarAccess.getConstraintDefAccess().getNameAssignment_1()); 
            // InternalModel2Blockly.g:5496:2: ( rule__ConstraintDef__NameAssignment_1 )
            // InternalModel2Blockly.g:5496:3: rule__ConstraintDef__NameAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__ConstraintDef__NameAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getConstraintDefAccess().getNameAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConstraintDef__Group__1__Impl"


    // $ANTLR start "rule__ConstraintDef__Group__2"
    // InternalModel2Blockly.g:5504:1: rule__ConstraintDef__Group__2 : rule__ConstraintDef__Group__2__Impl rule__ConstraintDef__Group__3 ;
    public final void rule__ConstraintDef__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5508:1: ( rule__ConstraintDef__Group__2__Impl rule__ConstraintDef__Group__3 )
            // InternalModel2Blockly.g:5509:2: rule__ConstraintDef__Group__2__Impl rule__ConstraintDef__Group__3
            {
            pushFollow(FOLLOW_4);
            rule__ConstraintDef__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ConstraintDef__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConstraintDef__Group__2"


    // $ANTLR start "rule__ConstraintDef__Group__2__Impl"
    // InternalModel2Blockly.g:5516:1: rule__ConstraintDef__Group__2__Impl : ( 'on' ) ;
    public final void rule__ConstraintDef__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5520:1: ( ( 'on' ) )
            // InternalModel2Blockly.g:5521:1: ( 'on' )
            {
            // InternalModel2Blockly.g:5521:1: ( 'on' )
            // InternalModel2Blockly.g:5522:2: 'on'
            {
             before(grammarAccess.getConstraintDefAccess().getOnKeyword_2()); 
            match(input,83,FOLLOW_2); 
             after(grammarAccess.getConstraintDefAccess().getOnKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConstraintDef__Group__2__Impl"


    // $ANTLR start "rule__ConstraintDef__Group__3"
    // InternalModel2Blockly.g:5531:1: rule__ConstraintDef__Group__3 : rule__ConstraintDef__Group__3__Impl rule__ConstraintDef__Group__4 ;
    public final void rule__ConstraintDef__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5535:1: ( rule__ConstraintDef__Group__3__Impl rule__ConstraintDef__Group__4 )
            // InternalModel2Blockly.g:5536:2: rule__ConstraintDef__Group__3__Impl rule__ConstraintDef__Group__4
            {
            pushFollow(FOLLOW_20);
            rule__ConstraintDef__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ConstraintDef__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConstraintDef__Group__3"


    // $ANTLR start "rule__ConstraintDef__Group__3__Impl"
    // InternalModel2Blockly.g:5543:1: rule__ConstraintDef__Group__3__Impl : ( ( rule__ConstraintDef__TargetAssignment_3 ) ) ;
    public final void rule__ConstraintDef__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5547:1: ( ( ( rule__ConstraintDef__TargetAssignment_3 ) ) )
            // InternalModel2Blockly.g:5548:1: ( ( rule__ConstraintDef__TargetAssignment_3 ) )
            {
            // InternalModel2Blockly.g:5548:1: ( ( rule__ConstraintDef__TargetAssignment_3 ) )
            // InternalModel2Blockly.g:5549:2: ( rule__ConstraintDef__TargetAssignment_3 )
            {
             before(grammarAccess.getConstraintDefAccess().getTargetAssignment_3()); 
            // InternalModel2Blockly.g:5550:2: ( rule__ConstraintDef__TargetAssignment_3 )
            // InternalModel2Blockly.g:5550:3: rule__ConstraintDef__TargetAssignment_3
            {
            pushFollow(FOLLOW_2);
            rule__ConstraintDef__TargetAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getConstraintDefAccess().getTargetAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConstraintDef__Group__3__Impl"


    // $ANTLR start "rule__ConstraintDef__Group__4"
    // InternalModel2Blockly.g:5558:1: rule__ConstraintDef__Group__4 : rule__ConstraintDef__Group__4__Impl rule__ConstraintDef__Group__5 ;
    public final void rule__ConstraintDef__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5562:1: ( rule__ConstraintDef__Group__4__Impl rule__ConstraintDef__Group__5 )
            // InternalModel2Blockly.g:5563:2: rule__ConstraintDef__Group__4__Impl rule__ConstraintDef__Group__5
            {
            pushFollow(FOLLOW_35);
            rule__ConstraintDef__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ConstraintDef__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConstraintDef__Group__4"


    // $ANTLR start "rule__ConstraintDef__Group__4__Impl"
    // InternalModel2Blockly.g:5570:1: rule__ConstraintDef__Group__4__Impl : ( ':' ) ;
    public final void rule__ConstraintDef__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5574:1: ( ( ':' ) )
            // InternalModel2Blockly.g:5575:1: ( ':' )
            {
            // InternalModel2Blockly.g:5575:1: ( ':' )
            // InternalModel2Blockly.g:5576:2: ':'
            {
             before(grammarAccess.getConstraintDefAccess().getColonKeyword_4()); 
            match(input,56,FOLLOW_2); 
             after(grammarAccess.getConstraintDefAccess().getColonKeyword_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConstraintDef__Group__4__Impl"


    // $ANTLR start "rule__ConstraintDef__Group__5"
    // InternalModel2Blockly.g:5585:1: rule__ConstraintDef__Group__5 : rule__ConstraintDef__Group__5__Impl rule__ConstraintDef__Group__6 ;
    public final void rule__ConstraintDef__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5589:1: ( rule__ConstraintDef__Group__5__Impl rule__ConstraintDef__Group__6 )
            // InternalModel2Blockly.g:5590:2: rule__ConstraintDef__Group__5__Impl rule__ConstraintDef__Group__6
            {
            pushFollow(FOLLOW_36);
            rule__ConstraintDef__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ConstraintDef__Group__6();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConstraintDef__Group__5"


    // $ANTLR start "rule__ConstraintDef__Group__5__Impl"
    // InternalModel2Blockly.g:5597:1: rule__ConstraintDef__Group__5__Impl : ( 'must' ) ;
    public final void rule__ConstraintDef__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5601:1: ( ( 'must' ) )
            // InternalModel2Blockly.g:5602:1: ( 'must' )
            {
            // InternalModel2Blockly.g:5602:1: ( 'must' )
            // InternalModel2Blockly.g:5603:2: 'must'
            {
             before(grammarAccess.getConstraintDefAccess().getMustKeyword_5()); 
            match(input,84,FOLLOW_2); 
             after(grammarAccess.getConstraintDefAccess().getMustKeyword_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConstraintDef__Group__5__Impl"


    // $ANTLR start "rule__ConstraintDef__Group__6"
    // InternalModel2Blockly.g:5612:1: rule__ConstraintDef__Group__6 : rule__ConstraintDef__Group__6__Impl rule__ConstraintDef__Group__7 ;
    public final void rule__ConstraintDef__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5616:1: ( rule__ConstraintDef__Group__6__Impl rule__ConstraintDef__Group__7 )
            // InternalModel2Blockly.g:5617:2: rule__ConstraintDef__Group__6__Impl rule__ConstraintDef__Group__7
            {
            pushFollow(FOLLOW_4);
            rule__ConstraintDef__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ConstraintDef__Group__7();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConstraintDef__Group__6"


    // $ANTLR start "rule__ConstraintDef__Group__6__Impl"
    // InternalModel2Blockly.g:5624:1: rule__ConstraintDef__Group__6__Impl : ( 'follow' ) ;
    public final void rule__ConstraintDef__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5628:1: ( ( 'follow' ) )
            // InternalModel2Blockly.g:5629:1: ( 'follow' )
            {
            // InternalModel2Blockly.g:5629:1: ( 'follow' )
            // InternalModel2Blockly.g:5630:2: 'follow'
            {
             before(grammarAccess.getConstraintDefAccess().getFollowKeyword_6()); 
            match(input,85,FOLLOW_2); 
             after(grammarAccess.getConstraintDefAccess().getFollowKeyword_6()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConstraintDef__Group__6__Impl"


    // $ANTLR start "rule__ConstraintDef__Group__7"
    // InternalModel2Blockly.g:5639:1: rule__ConstraintDef__Group__7 : rule__ConstraintDef__Group__7__Impl ;
    public final void rule__ConstraintDef__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5643:1: ( rule__ConstraintDef__Group__7__Impl )
            // InternalModel2Blockly.g:5644:2: rule__ConstraintDef__Group__7__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ConstraintDef__Group__7__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConstraintDef__Group__7"


    // $ANTLR start "rule__ConstraintDef__Group__7__Impl"
    // InternalModel2Blockly.g:5650:1: rule__ConstraintDef__Group__7__Impl : ( ( rule__ConstraintDef__PredecessorAssignment_7 ) ) ;
    public final void rule__ConstraintDef__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5654:1: ( ( ( rule__ConstraintDef__PredecessorAssignment_7 ) ) )
            // InternalModel2Blockly.g:5655:1: ( ( rule__ConstraintDef__PredecessorAssignment_7 ) )
            {
            // InternalModel2Blockly.g:5655:1: ( ( rule__ConstraintDef__PredecessorAssignment_7 ) )
            // InternalModel2Blockly.g:5656:2: ( rule__ConstraintDef__PredecessorAssignment_7 )
            {
             before(grammarAccess.getConstraintDefAccess().getPredecessorAssignment_7()); 
            // InternalModel2Blockly.g:5657:2: ( rule__ConstraintDef__PredecessorAssignment_7 )
            // InternalModel2Blockly.g:5657:3: rule__ConstraintDef__PredecessorAssignment_7
            {
            pushFollow(FOLLOW_2);
            rule__ConstraintDef__PredecessorAssignment_7();

            state._fsp--;


            }

             after(grammarAccess.getConstraintDefAccess().getPredecessorAssignment_7()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConstraintDef__Group__7__Impl"


    // $ANTLR start "rule__ValidationDef__Group__0"
    // InternalModel2Blockly.g:5666:1: rule__ValidationDef__Group__0 : rule__ValidationDef__Group__0__Impl rule__ValidationDef__Group__1 ;
    public final void rule__ValidationDef__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5670:1: ( rule__ValidationDef__Group__0__Impl rule__ValidationDef__Group__1 )
            // InternalModel2Blockly.g:5671:2: rule__ValidationDef__Group__0__Impl rule__ValidationDef__Group__1
            {
            pushFollow(FOLLOW_4);
            rule__ValidationDef__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ValidationDef__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ValidationDef__Group__0"


    // $ANTLR start "rule__ValidationDef__Group__0__Impl"
    // InternalModel2Blockly.g:5678:1: rule__ValidationDef__Group__0__Impl : ( 'validation' ) ;
    public final void rule__ValidationDef__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5682:1: ( ( 'validation' ) )
            // InternalModel2Blockly.g:5683:1: ( 'validation' )
            {
            // InternalModel2Blockly.g:5683:1: ( 'validation' )
            // InternalModel2Blockly.g:5684:2: 'validation'
            {
             before(grammarAccess.getValidationDefAccess().getValidationKeyword_0()); 
            match(input,86,FOLLOW_2); 
             after(grammarAccess.getValidationDefAccess().getValidationKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ValidationDef__Group__0__Impl"


    // $ANTLR start "rule__ValidationDef__Group__1"
    // InternalModel2Blockly.g:5693:1: rule__ValidationDef__Group__1 : rule__ValidationDef__Group__1__Impl rule__ValidationDef__Group__2 ;
    public final void rule__ValidationDef__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5697:1: ( rule__ValidationDef__Group__1__Impl rule__ValidationDef__Group__2 )
            // InternalModel2Blockly.g:5698:2: rule__ValidationDef__Group__1__Impl rule__ValidationDef__Group__2
            {
            pushFollow(FOLLOW_34);
            rule__ValidationDef__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ValidationDef__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ValidationDef__Group__1"


    // $ANTLR start "rule__ValidationDef__Group__1__Impl"
    // InternalModel2Blockly.g:5705:1: rule__ValidationDef__Group__1__Impl : ( ( rule__ValidationDef__NameAssignment_1 ) ) ;
    public final void rule__ValidationDef__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5709:1: ( ( ( rule__ValidationDef__NameAssignment_1 ) ) )
            // InternalModel2Blockly.g:5710:1: ( ( rule__ValidationDef__NameAssignment_1 ) )
            {
            // InternalModel2Blockly.g:5710:1: ( ( rule__ValidationDef__NameAssignment_1 ) )
            // InternalModel2Blockly.g:5711:2: ( rule__ValidationDef__NameAssignment_1 )
            {
             before(grammarAccess.getValidationDefAccess().getNameAssignment_1()); 
            // InternalModel2Blockly.g:5712:2: ( rule__ValidationDef__NameAssignment_1 )
            // InternalModel2Blockly.g:5712:3: rule__ValidationDef__NameAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__ValidationDef__NameAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getValidationDefAccess().getNameAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ValidationDef__Group__1__Impl"


    // $ANTLR start "rule__ValidationDef__Group__2"
    // InternalModel2Blockly.g:5720:1: rule__ValidationDef__Group__2 : rule__ValidationDef__Group__2__Impl rule__ValidationDef__Group__3 ;
    public final void rule__ValidationDef__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5724:1: ( rule__ValidationDef__Group__2__Impl rule__ValidationDef__Group__3 )
            // InternalModel2Blockly.g:5725:2: rule__ValidationDef__Group__2__Impl rule__ValidationDef__Group__3
            {
            pushFollow(FOLLOW_4);
            rule__ValidationDef__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ValidationDef__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ValidationDef__Group__2"


    // $ANTLR start "rule__ValidationDef__Group__2__Impl"
    // InternalModel2Blockly.g:5732:1: rule__ValidationDef__Group__2__Impl : ( 'on' ) ;
    public final void rule__ValidationDef__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5736:1: ( ( 'on' ) )
            // InternalModel2Blockly.g:5737:1: ( 'on' )
            {
            // InternalModel2Blockly.g:5737:1: ( 'on' )
            // InternalModel2Blockly.g:5738:2: 'on'
            {
             before(grammarAccess.getValidationDefAccess().getOnKeyword_2()); 
            match(input,83,FOLLOW_2); 
             after(grammarAccess.getValidationDefAccess().getOnKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ValidationDef__Group__2__Impl"


    // $ANTLR start "rule__ValidationDef__Group__3"
    // InternalModel2Blockly.g:5747:1: rule__ValidationDef__Group__3 : rule__ValidationDef__Group__3__Impl rule__ValidationDef__Group__4 ;
    public final void rule__ValidationDef__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5751:1: ( rule__ValidationDef__Group__3__Impl rule__ValidationDef__Group__4 )
            // InternalModel2Blockly.g:5752:2: rule__ValidationDef__Group__3__Impl rule__ValidationDef__Group__4
            {
            pushFollow(FOLLOW_20);
            rule__ValidationDef__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ValidationDef__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ValidationDef__Group__3"


    // $ANTLR start "rule__ValidationDef__Group__3__Impl"
    // InternalModel2Blockly.g:5759:1: rule__ValidationDef__Group__3__Impl : ( ( rule__ValidationDef__TargetAssignment_3 ) ) ;
    public final void rule__ValidationDef__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5763:1: ( ( ( rule__ValidationDef__TargetAssignment_3 ) ) )
            // InternalModel2Blockly.g:5764:1: ( ( rule__ValidationDef__TargetAssignment_3 ) )
            {
            // InternalModel2Blockly.g:5764:1: ( ( rule__ValidationDef__TargetAssignment_3 ) )
            // InternalModel2Blockly.g:5765:2: ( rule__ValidationDef__TargetAssignment_3 )
            {
             before(grammarAccess.getValidationDefAccess().getTargetAssignment_3()); 
            // InternalModel2Blockly.g:5766:2: ( rule__ValidationDef__TargetAssignment_3 )
            // InternalModel2Blockly.g:5766:3: rule__ValidationDef__TargetAssignment_3
            {
            pushFollow(FOLLOW_2);
            rule__ValidationDef__TargetAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getValidationDefAccess().getTargetAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ValidationDef__Group__3__Impl"


    // $ANTLR start "rule__ValidationDef__Group__4"
    // InternalModel2Blockly.g:5774:1: rule__ValidationDef__Group__4 : rule__ValidationDef__Group__4__Impl rule__ValidationDef__Group__5 ;
    public final void rule__ValidationDef__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5778:1: ( rule__ValidationDef__Group__4__Impl rule__ValidationDef__Group__5 )
            // InternalModel2Blockly.g:5779:2: rule__ValidationDef__Group__4__Impl rule__ValidationDef__Group__5
            {
            pushFollow(FOLLOW_37);
            rule__ValidationDef__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ValidationDef__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ValidationDef__Group__4"


    // $ANTLR start "rule__ValidationDef__Group__4__Impl"
    // InternalModel2Blockly.g:5786:1: rule__ValidationDef__Group__4__Impl : ( ':' ) ;
    public final void rule__ValidationDef__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5790:1: ( ( ':' ) )
            // InternalModel2Blockly.g:5791:1: ( ':' )
            {
            // InternalModel2Blockly.g:5791:1: ( ':' )
            // InternalModel2Blockly.g:5792:2: ':'
            {
             before(grammarAccess.getValidationDefAccess().getColonKeyword_4()); 
            match(input,56,FOLLOW_2); 
             after(grammarAccess.getValidationDefAccess().getColonKeyword_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ValidationDef__Group__4__Impl"


    // $ANTLR start "rule__ValidationDef__Group__5"
    // InternalModel2Blockly.g:5801:1: rule__ValidationDef__Group__5 : rule__ValidationDef__Group__5__Impl rule__ValidationDef__Group__6 ;
    public final void rule__ValidationDef__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5805:1: ( rule__ValidationDef__Group__5__Impl rule__ValidationDef__Group__6 )
            // InternalModel2Blockly.g:5806:2: rule__ValidationDef__Group__5__Impl rule__ValidationDef__Group__6
            {
            pushFollow(FOLLOW_10);
            rule__ValidationDef__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ValidationDef__Group__6();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ValidationDef__Group__5"


    // $ANTLR start "rule__ValidationDef__Group__5__Impl"
    // InternalModel2Blockly.g:5813:1: rule__ValidationDef__Group__5__Impl : ( ( rule__ValidationDef__KindAssignment_5 ) ) ;
    public final void rule__ValidationDef__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5817:1: ( ( ( rule__ValidationDef__KindAssignment_5 ) ) )
            // InternalModel2Blockly.g:5818:1: ( ( rule__ValidationDef__KindAssignment_5 ) )
            {
            // InternalModel2Blockly.g:5818:1: ( ( rule__ValidationDef__KindAssignment_5 ) )
            // InternalModel2Blockly.g:5819:2: ( rule__ValidationDef__KindAssignment_5 )
            {
             before(grammarAccess.getValidationDefAccess().getKindAssignment_5()); 
            // InternalModel2Blockly.g:5820:2: ( rule__ValidationDef__KindAssignment_5 )
            // InternalModel2Blockly.g:5820:3: rule__ValidationDef__KindAssignment_5
            {
            pushFollow(FOLLOW_2);
            rule__ValidationDef__KindAssignment_5();

            state._fsp--;


            }

             after(grammarAccess.getValidationDefAccess().getKindAssignment_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ValidationDef__Group__5__Impl"


    // $ANTLR start "rule__ValidationDef__Group__6"
    // InternalModel2Blockly.g:5828:1: rule__ValidationDef__Group__6 : rule__ValidationDef__Group__6__Impl rule__ValidationDef__Group__7 ;
    public final void rule__ValidationDef__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5832:1: ( rule__ValidationDef__Group__6__Impl rule__ValidationDef__Group__7 )
            // InternalModel2Blockly.g:5833:2: rule__ValidationDef__Group__6__Impl rule__ValidationDef__Group__7
            {
            pushFollow(FOLLOW_38);
            rule__ValidationDef__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ValidationDef__Group__7();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ValidationDef__Group__6"


    // $ANTLR start "rule__ValidationDef__Group__6__Impl"
    // InternalModel2Blockly.g:5840:1: rule__ValidationDef__Group__6__Impl : ( ( rule__ValidationDef__ExpressionAssignment_6 ) ) ;
    public final void rule__ValidationDef__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5844:1: ( ( ( rule__ValidationDef__ExpressionAssignment_6 ) ) )
            // InternalModel2Blockly.g:5845:1: ( ( rule__ValidationDef__ExpressionAssignment_6 ) )
            {
            // InternalModel2Blockly.g:5845:1: ( ( rule__ValidationDef__ExpressionAssignment_6 ) )
            // InternalModel2Blockly.g:5846:2: ( rule__ValidationDef__ExpressionAssignment_6 )
            {
             before(grammarAccess.getValidationDefAccess().getExpressionAssignment_6()); 
            // InternalModel2Blockly.g:5847:2: ( rule__ValidationDef__ExpressionAssignment_6 )
            // InternalModel2Blockly.g:5847:3: rule__ValidationDef__ExpressionAssignment_6
            {
            pushFollow(FOLLOW_2);
            rule__ValidationDef__ExpressionAssignment_6();

            state._fsp--;


            }

             after(grammarAccess.getValidationDefAccess().getExpressionAssignment_6()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ValidationDef__Group__6__Impl"


    // $ANTLR start "rule__ValidationDef__Group__7"
    // InternalModel2Blockly.g:5855:1: rule__ValidationDef__Group__7 : rule__ValidationDef__Group__7__Impl ;
    public final void rule__ValidationDef__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5859:1: ( rule__ValidationDef__Group__7__Impl )
            // InternalModel2Blockly.g:5860:2: rule__ValidationDef__Group__7__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ValidationDef__Group__7__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ValidationDef__Group__7"


    // $ANTLR start "rule__ValidationDef__Group__7__Impl"
    // InternalModel2Blockly.g:5866:1: rule__ValidationDef__Group__7__Impl : ( ( rule__ValidationDef__Group_7__0 )? ) ;
    public final void rule__ValidationDef__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5870:1: ( ( ( rule__ValidationDef__Group_7__0 )? ) )
            // InternalModel2Blockly.g:5871:1: ( ( rule__ValidationDef__Group_7__0 )? )
            {
            // InternalModel2Blockly.g:5871:1: ( ( rule__ValidationDef__Group_7__0 )? )
            // InternalModel2Blockly.g:5872:2: ( rule__ValidationDef__Group_7__0 )?
            {
             before(grammarAccess.getValidationDefAccess().getGroup_7()); 
            // InternalModel2Blockly.g:5873:2: ( rule__ValidationDef__Group_7__0 )?
            int alt66=2;
            int LA66_0 = input.LA(1);

            if ( (LA66_0==87) ) {
                alt66=1;
            }
            switch (alt66) {
                case 1 :
                    // InternalModel2Blockly.g:5873:3: rule__ValidationDef__Group_7__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ValidationDef__Group_7__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getValidationDefAccess().getGroup_7()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ValidationDef__Group__7__Impl"


    // $ANTLR start "rule__ValidationDef__Group_7__0"
    // InternalModel2Blockly.g:5882:1: rule__ValidationDef__Group_7__0 : rule__ValidationDef__Group_7__0__Impl rule__ValidationDef__Group_7__1 ;
    public final void rule__ValidationDef__Group_7__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5886:1: ( rule__ValidationDef__Group_7__0__Impl rule__ValidationDef__Group_7__1 )
            // InternalModel2Blockly.g:5887:2: rule__ValidationDef__Group_7__0__Impl rule__ValidationDef__Group_7__1
            {
            pushFollow(FOLLOW_10);
            rule__ValidationDef__Group_7__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ValidationDef__Group_7__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ValidationDef__Group_7__0"


    // $ANTLR start "rule__ValidationDef__Group_7__0__Impl"
    // InternalModel2Blockly.g:5894:1: rule__ValidationDef__Group_7__0__Impl : ( 'errorMessage' ) ;
    public final void rule__ValidationDef__Group_7__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5898:1: ( ( 'errorMessage' ) )
            // InternalModel2Blockly.g:5899:1: ( 'errorMessage' )
            {
            // InternalModel2Blockly.g:5899:1: ( 'errorMessage' )
            // InternalModel2Blockly.g:5900:2: 'errorMessage'
            {
             before(grammarAccess.getValidationDefAccess().getErrorMessageKeyword_7_0()); 
            match(input,87,FOLLOW_2); 
             after(grammarAccess.getValidationDefAccess().getErrorMessageKeyword_7_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ValidationDef__Group_7__0__Impl"


    // $ANTLR start "rule__ValidationDef__Group_7__1"
    // InternalModel2Blockly.g:5909:1: rule__ValidationDef__Group_7__1 : rule__ValidationDef__Group_7__1__Impl ;
    public final void rule__ValidationDef__Group_7__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5913:1: ( rule__ValidationDef__Group_7__1__Impl )
            // InternalModel2Blockly.g:5914:2: rule__ValidationDef__Group_7__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ValidationDef__Group_7__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ValidationDef__Group_7__1"


    // $ANTLR start "rule__ValidationDef__Group_7__1__Impl"
    // InternalModel2Blockly.g:5920:1: rule__ValidationDef__Group_7__1__Impl : ( ( rule__ValidationDef__MessageAssignment_7_1 ) ) ;
    public final void rule__ValidationDef__Group_7__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5924:1: ( ( ( rule__ValidationDef__MessageAssignment_7_1 ) ) )
            // InternalModel2Blockly.g:5925:1: ( ( rule__ValidationDef__MessageAssignment_7_1 ) )
            {
            // InternalModel2Blockly.g:5925:1: ( ( rule__ValidationDef__MessageAssignment_7_1 ) )
            // InternalModel2Blockly.g:5926:2: ( rule__ValidationDef__MessageAssignment_7_1 )
            {
             before(grammarAccess.getValidationDefAccess().getMessageAssignment_7_1()); 
            // InternalModel2Blockly.g:5927:2: ( rule__ValidationDef__MessageAssignment_7_1 )
            // InternalModel2Blockly.g:5927:3: rule__ValidationDef__MessageAssignment_7_1
            {
            pushFollow(FOLLOW_2);
            rule__ValidationDef__MessageAssignment_7_1();

            state._fsp--;


            }

             after(grammarAccess.getValidationDefAccess().getMessageAssignment_7_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ValidationDef__Group_7__1__Impl"


    // $ANTLR start "rule__WorkspaceConfig__Group__0"
    // InternalModel2Blockly.g:5936:1: rule__WorkspaceConfig__Group__0 : rule__WorkspaceConfig__Group__0__Impl rule__WorkspaceConfig__Group__1 ;
    public final void rule__WorkspaceConfig__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5940:1: ( rule__WorkspaceConfig__Group__0__Impl rule__WorkspaceConfig__Group__1 )
            // InternalModel2Blockly.g:5941:2: rule__WorkspaceConfig__Group__0__Impl rule__WorkspaceConfig__Group__1
            {
            pushFollow(FOLLOW_30);
            rule__WorkspaceConfig__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__WorkspaceConfig__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WorkspaceConfig__Group__0"


    // $ANTLR start "rule__WorkspaceConfig__Group__0__Impl"
    // InternalModel2Blockly.g:5948:1: rule__WorkspaceConfig__Group__0__Impl : ( 'workspace' ) ;
    public final void rule__WorkspaceConfig__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5952:1: ( ( 'workspace' ) )
            // InternalModel2Blockly.g:5953:1: ( 'workspace' )
            {
            // InternalModel2Blockly.g:5953:1: ( 'workspace' )
            // InternalModel2Blockly.g:5954:2: 'workspace'
            {
             before(grammarAccess.getWorkspaceConfigAccess().getWorkspaceKeyword_0()); 
            match(input,88,FOLLOW_2); 
             after(grammarAccess.getWorkspaceConfigAccess().getWorkspaceKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WorkspaceConfig__Group__0__Impl"


    // $ANTLR start "rule__WorkspaceConfig__Group__1"
    // InternalModel2Blockly.g:5963:1: rule__WorkspaceConfig__Group__1 : rule__WorkspaceConfig__Group__1__Impl rule__WorkspaceConfig__Group__2 ;
    public final void rule__WorkspaceConfig__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5967:1: ( rule__WorkspaceConfig__Group__1__Impl rule__WorkspaceConfig__Group__2 )
            // InternalModel2Blockly.g:5968:2: rule__WorkspaceConfig__Group__1__Impl rule__WorkspaceConfig__Group__2
            {
            pushFollow(FOLLOW_39);
            rule__WorkspaceConfig__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__WorkspaceConfig__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WorkspaceConfig__Group__1"


    // $ANTLR start "rule__WorkspaceConfig__Group__1__Impl"
    // InternalModel2Blockly.g:5975:1: rule__WorkspaceConfig__Group__1__Impl : ( '{' ) ;
    public final void rule__WorkspaceConfig__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5979:1: ( ( '{' ) )
            // InternalModel2Blockly.g:5980:1: ( '{' )
            {
            // InternalModel2Blockly.g:5980:1: ( '{' )
            // InternalModel2Blockly.g:5981:2: '{'
            {
             before(grammarAccess.getWorkspaceConfigAccess().getLeftCurlyBracketKeyword_1()); 
            match(input,45,FOLLOW_2); 
             after(grammarAccess.getWorkspaceConfigAccess().getLeftCurlyBracketKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WorkspaceConfig__Group__1__Impl"


    // $ANTLR start "rule__WorkspaceConfig__Group__2"
    // InternalModel2Blockly.g:5990:1: rule__WorkspaceConfig__Group__2 : rule__WorkspaceConfig__Group__2__Impl rule__WorkspaceConfig__Group__3 ;
    public final void rule__WorkspaceConfig__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:5994:1: ( rule__WorkspaceConfig__Group__2__Impl rule__WorkspaceConfig__Group__3 )
            // InternalModel2Blockly.g:5995:2: rule__WorkspaceConfig__Group__2__Impl rule__WorkspaceConfig__Group__3
            {
            pushFollow(FOLLOW_39);
            rule__WorkspaceConfig__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__WorkspaceConfig__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WorkspaceConfig__Group__2"


    // $ANTLR start "rule__WorkspaceConfig__Group__2__Impl"
    // InternalModel2Blockly.g:6002:1: rule__WorkspaceConfig__Group__2__Impl : ( ( rule__WorkspaceConfig__OptionsAssignment_2 )* ) ;
    public final void rule__WorkspaceConfig__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:6006:1: ( ( ( rule__WorkspaceConfig__OptionsAssignment_2 )* ) )
            // InternalModel2Blockly.g:6007:1: ( ( rule__WorkspaceConfig__OptionsAssignment_2 )* )
            {
            // InternalModel2Blockly.g:6007:1: ( ( rule__WorkspaceConfig__OptionsAssignment_2 )* )
            // InternalModel2Blockly.g:6008:2: ( rule__WorkspaceConfig__OptionsAssignment_2 )*
            {
             before(grammarAccess.getWorkspaceConfigAccess().getOptionsAssignment_2()); 
            // InternalModel2Blockly.g:6009:2: ( rule__WorkspaceConfig__OptionsAssignment_2 )*
            loop67:
            do {
                int alt67=2;
                int LA67_0 = input.LA(1);

                if ( (LA67_0==RULE_ID) ) {
                    alt67=1;
                }


                switch (alt67) {
            	case 1 :
            	    // InternalModel2Blockly.g:6009:3: rule__WorkspaceConfig__OptionsAssignment_2
            	    {
            	    pushFollow(FOLLOW_40);
            	    rule__WorkspaceConfig__OptionsAssignment_2();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop67;
                }
            } while (true);

             after(grammarAccess.getWorkspaceConfigAccess().getOptionsAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WorkspaceConfig__Group__2__Impl"


    // $ANTLR start "rule__WorkspaceConfig__Group__3"
    // InternalModel2Blockly.g:6017:1: rule__WorkspaceConfig__Group__3 : rule__WorkspaceConfig__Group__3__Impl ;
    public final void rule__WorkspaceConfig__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:6021:1: ( rule__WorkspaceConfig__Group__3__Impl )
            // InternalModel2Blockly.g:6022:2: rule__WorkspaceConfig__Group__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__WorkspaceConfig__Group__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WorkspaceConfig__Group__3"


    // $ANTLR start "rule__WorkspaceConfig__Group__3__Impl"
    // InternalModel2Blockly.g:6028:1: rule__WorkspaceConfig__Group__3__Impl : ( '}' ) ;
    public final void rule__WorkspaceConfig__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:6032:1: ( ( '}' ) )
            // InternalModel2Blockly.g:6033:1: ( '}' )
            {
            // InternalModel2Blockly.g:6033:1: ( '}' )
            // InternalModel2Blockly.g:6034:2: '}'
            {
             before(grammarAccess.getWorkspaceConfigAccess().getRightCurlyBracketKeyword_3()); 
            match(input,46,FOLLOW_2); 
             after(grammarAccess.getWorkspaceConfigAccess().getRightCurlyBracketKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WorkspaceConfig__Group__3__Impl"


    // $ANTLR start "rule__StringOption__Group__0"
    // InternalModel2Blockly.g:6044:1: rule__StringOption__Group__0 : rule__StringOption__Group__0__Impl rule__StringOption__Group__1 ;
    public final void rule__StringOption__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:6048:1: ( rule__StringOption__Group__0__Impl rule__StringOption__Group__1 )
            // InternalModel2Blockly.g:6049:2: rule__StringOption__Group__0__Impl rule__StringOption__Group__1
            {
            pushFollow(FOLLOW_20);
            rule__StringOption__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__StringOption__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__StringOption__Group__0"


    // $ANTLR start "rule__StringOption__Group__0__Impl"
    // InternalModel2Blockly.g:6056:1: rule__StringOption__Group__0__Impl : ( ( rule__StringOption__KeyAssignment_0 ) ) ;
    public final void rule__StringOption__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:6060:1: ( ( ( rule__StringOption__KeyAssignment_0 ) ) )
            // InternalModel2Blockly.g:6061:1: ( ( rule__StringOption__KeyAssignment_0 ) )
            {
            // InternalModel2Blockly.g:6061:1: ( ( rule__StringOption__KeyAssignment_0 ) )
            // InternalModel2Blockly.g:6062:2: ( rule__StringOption__KeyAssignment_0 )
            {
             before(grammarAccess.getStringOptionAccess().getKeyAssignment_0()); 
            // InternalModel2Blockly.g:6063:2: ( rule__StringOption__KeyAssignment_0 )
            // InternalModel2Blockly.g:6063:3: rule__StringOption__KeyAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__StringOption__KeyAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getStringOptionAccess().getKeyAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__StringOption__Group__0__Impl"


    // $ANTLR start "rule__StringOption__Group__1"
    // InternalModel2Blockly.g:6071:1: rule__StringOption__Group__1 : rule__StringOption__Group__1__Impl rule__StringOption__Group__2 ;
    public final void rule__StringOption__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:6075:1: ( rule__StringOption__Group__1__Impl rule__StringOption__Group__2 )
            // InternalModel2Blockly.g:6076:2: rule__StringOption__Group__1__Impl rule__StringOption__Group__2
            {
            pushFollow(FOLLOW_10);
            rule__StringOption__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__StringOption__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__StringOption__Group__1"


    // $ANTLR start "rule__StringOption__Group__1__Impl"
    // InternalModel2Blockly.g:6083:1: rule__StringOption__Group__1__Impl : ( ':' ) ;
    public final void rule__StringOption__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:6087:1: ( ( ':' ) )
            // InternalModel2Blockly.g:6088:1: ( ':' )
            {
            // InternalModel2Blockly.g:6088:1: ( ':' )
            // InternalModel2Blockly.g:6089:2: ':'
            {
             before(grammarAccess.getStringOptionAccess().getColonKeyword_1()); 
            match(input,56,FOLLOW_2); 
             after(grammarAccess.getStringOptionAccess().getColonKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__StringOption__Group__1__Impl"


    // $ANTLR start "rule__StringOption__Group__2"
    // InternalModel2Blockly.g:6098:1: rule__StringOption__Group__2 : rule__StringOption__Group__2__Impl ;
    public final void rule__StringOption__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:6102:1: ( rule__StringOption__Group__2__Impl )
            // InternalModel2Blockly.g:6103:2: rule__StringOption__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__StringOption__Group__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__StringOption__Group__2"


    // $ANTLR start "rule__StringOption__Group__2__Impl"
    // InternalModel2Blockly.g:6109:1: rule__StringOption__Group__2__Impl : ( ( rule__StringOption__ValueAssignment_2 ) ) ;
    public final void rule__StringOption__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:6113:1: ( ( ( rule__StringOption__ValueAssignment_2 ) ) )
            // InternalModel2Blockly.g:6114:1: ( ( rule__StringOption__ValueAssignment_2 ) )
            {
            // InternalModel2Blockly.g:6114:1: ( ( rule__StringOption__ValueAssignment_2 ) )
            // InternalModel2Blockly.g:6115:2: ( rule__StringOption__ValueAssignment_2 )
            {
             before(grammarAccess.getStringOptionAccess().getValueAssignment_2()); 
            // InternalModel2Blockly.g:6116:2: ( rule__StringOption__ValueAssignment_2 )
            // InternalModel2Blockly.g:6116:3: rule__StringOption__ValueAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__StringOption__ValueAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getStringOptionAccess().getValueAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__StringOption__Group__2__Impl"


    // $ANTLR start "rule__IntOption__Group__0"
    // InternalModel2Blockly.g:6125:1: rule__IntOption__Group__0 : rule__IntOption__Group__0__Impl rule__IntOption__Group__1 ;
    public final void rule__IntOption__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:6129:1: ( rule__IntOption__Group__0__Impl rule__IntOption__Group__1 )
            // InternalModel2Blockly.g:6130:2: rule__IntOption__Group__0__Impl rule__IntOption__Group__1
            {
            pushFollow(FOLLOW_20);
            rule__IntOption__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__IntOption__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IntOption__Group__0"


    // $ANTLR start "rule__IntOption__Group__0__Impl"
    // InternalModel2Blockly.g:6137:1: rule__IntOption__Group__0__Impl : ( ( rule__IntOption__KeyAssignment_0 ) ) ;
    public final void rule__IntOption__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:6141:1: ( ( ( rule__IntOption__KeyAssignment_0 ) ) )
            // InternalModel2Blockly.g:6142:1: ( ( rule__IntOption__KeyAssignment_0 ) )
            {
            // InternalModel2Blockly.g:6142:1: ( ( rule__IntOption__KeyAssignment_0 ) )
            // InternalModel2Blockly.g:6143:2: ( rule__IntOption__KeyAssignment_0 )
            {
             before(grammarAccess.getIntOptionAccess().getKeyAssignment_0()); 
            // InternalModel2Blockly.g:6144:2: ( rule__IntOption__KeyAssignment_0 )
            // InternalModel2Blockly.g:6144:3: rule__IntOption__KeyAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__IntOption__KeyAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getIntOptionAccess().getKeyAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IntOption__Group__0__Impl"


    // $ANTLR start "rule__IntOption__Group__1"
    // InternalModel2Blockly.g:6152:1: rule__IntOption__Group__1 : rule__IntOption__Group__1__Impl rule__IntOption__Group__2 ;
    public final void rule__IntOption__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:6156:1: ( rule__IntOption__Group__1__Impl rule__IntOption__Group__2 )
            // InternalModel2Blockly.g:6157:2: rule__IntOption__Group__1__Impl rule__IntOption__Group__2
            {
            pushFollow(FOLLOW_12);
            rule__IntOption__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__IntOption__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IntOption__Group__1"


    // $ANTLR start "rule__IntOption__Group__1__Impl"
    // InternalModel2Blockly.g:6164:1: rule__IntOption__Group__1__Impl : ( ':' ) ;
    public final void rule__IntOption__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:6168:1: ( ( ':' ) )
            // InternalModel2Blockly.g:6169:1: ( ':' )
            {
            // InternalModel2Blockly.g:6169:1: ( ':' )
            // InternalModel2Blockly.g:6170:2: ':'
            {
             before(grammarAccess.getIntOptionAccess().getColonKeyword_1()); 
            match(input,56,FOLLOW_2); 
             after(grammarAccess.getIntOptionAccess().getColonKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IntOption__Group__1__Impl"


    // $ANTLR start "rule__IntOption__Group__2"
    // InternalModel2Blockly.g:6179:1: rule__IntOption__Group__2 : rule__IntOption__Group__2__Impl ;
    public final void rule__IntOption__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:6183:1: ( rule__IntOption__Group__2__Impl )
            // InternalModel2Blockly.g:6184:2: rule__IntOption__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__IntOption__Group__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IntOption__Group__2"


    // $ANTLR start "rule__IntOption__Group__2__Impl"
    // InternalModel2Blockly.g:6190:1: rule__IntOption__Group__2__Impl : ( ( rule__IntOption__ValueAssignment_2 ) ) ;
    public final void rule__IntOption__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:6194:1: ( ( ( rule__IntOption__ValueAssignment_2 ) ) )
            // InternalModel2Blockly.g:6195:1: ( ( rule__IntOption__ValueAssignment_2 ) )
            {
            // InternalModel2Blockly.g:6195:1: ( ( rule__IntOption__ValueAssignment_2 ) )
            // InternalModel2Blockly.g:6196:2: ( rule__IntOption__ValueAssignment_2 )
            {
             before(grammarAccess.getIntOptionAccess().getValueAssignment_2()); 
            // InternalModel2Blockly.g:6197:2: ( rule__IntOption__ValueAssignment_2 )
            // InternalModel2Blockly.g:6197:3: rule__IntOption__ValueAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__IntOption__ValueAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getIntOptionAccess().getValueAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IntOption__Group__2__Impl"


    // $ANTLR start "rule__BoolOption__Group__0"
    // InternalModel2Blockly.g:6206:1: rule__BoolOption__Group__0 : rule__BoolOption__Group__0__Impl rule__BoolOption__Group__1 ;
    public final void rule__BoolOption__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:6210:1: ( rule__BoolOption__Group__0__Impl rule__BoolOption__Group__1 )
            // InternalModel2Blockly.g:6211:2: rule__BoolOption__Group__0__Impl rule__BoolOption__Group__1
            {
            pushFollow(FOLLOW_20);
            rule__BoolOption__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__BoolOption__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BoolOption__Group__0"


    // $ANTLR start "rule__BoolOption__Group__0__Impl"
    // InternalModel2Blockly.g:6218:1: rule__BoolOption__Group__0__Impl : ( ( rule__BoolOption__KeyAssignment_0 ) ) ;
    public final void rule__BoolOption__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:6222:1: ( ( ( rule__BoolOption__KeyAssignment_0 ) ) )
            // InternalModel2Blockly.g:6223:1: ( ( rule__BoolOption__KeyAssignment_0 ) )
            {
            // InternalModel2Blockly.g:6223:1: ( ( rule__BoolOption__KeyAssignment_0 ) )
            // InternalModel2Blockly.g:6224:2: ( rule__BoolOption__KeyAssignment_0 )
            {
             before(grammarAccess.getBoolOptionAccess().getKeyAssignment_0()); 
            // InternalModel2Blockly.g:6225:2: ( rule__BoolOption__KeyAssignment_0 )
            // InternalModel2Blockly.g:6225:3: rule__BoolOption__KeyAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__BoolOption__KeyAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getBoolOptionAccess().getKeyAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BoolOption__Group__0__Impl"


    // $ANTLR start "rule__BoolOption__Group__1"
    // InternalModel2Blockly.g:6233:1: rule__BoolOption__Group__1 : rule__BoolOption__Group__1__Impl rule__BoolOption__Group__2 ;
    public final void rule__BoolOption__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:6237:1: ( rule__BoolOption__Group__1__Impl rule__BoolOption__Group__2 )
            // InternalModel2Blockly.g:6238:2: rule__BoolOption__Group__1__Impl rule__BoolOption__Group__2
            {
            pushFollow(FOLLOW_19);
            rule__BoolOption__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__BoolOption__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BoolOption__Group__1"


    // $ANTLR start "rule__BoolOption__Group__1__Impl"
    // InternalModel2Blockly.g:6245:1: rule__BoolOption__Group__1__Impl : ( ':' ) ;
    public final void rule__BoolOption__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:6249:1: ( ( ':' ) )
            // InternalModel2Blockly.g:6250:1: ( ':' )
            {
            // InternalModel2Blockly.g:6250:1: ( ':' )
            // InternalModel2Blockly.g:6251:2: ':'
            {
             before(grammarAccess.getBoolOptionAccess().getColonKeyword_1()); 
            match(input,56,FOLLOW_2); 
             after(grammarAccess.getBoolOptionAccess().getColonKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BoolOption__Group__1__Impl"


    // $ANTLR start "rule__BoolOption__Group__2"
    // InternalModel2Blockly.g:6260:1: rule__BoolOption__Group__2 : rule__BoolOption__Group__2__Impl ;
    public final void rule__BoolOption__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:6264:1: ( rule__BoolOption__Group__2__Impl )
            // InternalModel2Blockly.g:6265:2: rule__BoolOption__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__BoolOption__Group__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BoolOption__Group__2"


    // $ANTLR start "rule__BoolOption__Group__2__Impl"
    // InternalModel2Blockly.g:6271:1: rule__BoolOption__Group__2__Impl : ( ( rule__BoolOption__ValueAssignment_2 ) ) ;
    public final void rule__BoolOption__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:6275:1: ( ( ( rule__BoolOption__ValueAssignment_2 ) ) )
            // InternalModel2Blockly.g:6276:1: ( ( rule__BoolOption__ValueAssignment_2 ) )
            {
            // InternalModel2Blockly.g:6276:1: ( ( rule__BoolOption__ValueAssignment_2 ) )
            // InternalModel2Blockly.g:6277:2: ( rule__BoolOption__ValueAssignment_2 )
            {
             before(grammarAccess.getBoolOptionAccess().getValueAssignment_2()); 
            // InternalModel2Blockly.g:6278:2: ( rule__BoolOption__ValueAssignment_2 )
            // InternalModel2Blockly.g:6278:3: rule__BoolOption__ValueAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__BoolOption__ValueAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getBoolOptionAccess().getValueAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BoolOption__Group__2__Impl"


    // $ANTLR start "rule__ObjectOption__Group__0"
    // InternalModel2Blockly.g:6287:1: rule__ObjectOption__Group__0 : rule__ObjectOption__Group__0__Impl rule__ObjectOption__Group__1 ;
    public final void rule__ObjectOption__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:6291:1: ( rule__ObjectOption__Group__0__Impl rule__ObjectOption__Group__1 )
            // InternalModel2Blockly.g:6292:2: rule__ObjectOption__Group__0__Impl rule__ObjectOption__Group__1
            {
            pushFollow(FOLLOW_20);
            rule__ObjectOption__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ObjectOption__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ObjectOption__Group__0"


    // $ANTLR start "rule__ObjectOption__Group__0__Impl"
    // InternalModel2Blockly.g:6299:1: rule__ObjectOption__Group__0__Impl : ( ( rule__ObjectOption__KeyAssignment_0 ) ) ;
    public final void rule__ObjectOption__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:6303:1: ( ( ( rule__ObjectOption__KeyAssignment_0 ) ) )
            // InternalModel2Blockly.g:6304:1: ( ( rule__ObjectOption__KeyAssignment_0 ) )
            {
            // InternalModel2Blockly.g:6304:1: ( ( rule__ObjectOption__KeyAssignment_0 ) )
            // InternalModel2Blockly.g:6305:2: ( rule__ObjectOption__KeyAssignment_0 )
            {
             before(grammarAccess.getObjectOptionAccess().getKeyAssignment_0()); 
            // InternalModel2Blockly.g:6306:2: ( rule__ObjectOption__KeyAssignment_0 )
            // InternalModel2Blockly.g:6306:3: rule__ObjectOption__KeyAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__ObjectOption__KeyAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getObjectOptionAccess().getKeyAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ObjectOption__Group__0__Impl"


    // $ANTLR start "rule__ObjectOption__Group__1"
    // InternalModel2Blockly.g:6314:1: rule__ObjectOption__Group__1 : rule__ObjectOption__Group__1__Impl rule__ObjectOption__Group__2 ;
    public final void rule__ObjectOption__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:6318:1: ( rule__ObjectOption__Group__1__Impl rule__ObjectOption__Group__2 )
            // InternalModel2Blockly.g:6319:2: rule__ObjectOption__Group__1__Impl rule__ObjectOption__Group__2
            {
            pushFollow(FOLLOW_30);
            rule__ObjectOption__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ObjectOption__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ObjectOption__Group__1"


    // $ANTLR start "rule__ObjectOption__Group__1__Impl"
    // InternalModel2Blockly.g:6326:1: rule__ObjectOption__Group__1__Impl : ( ':' ) ;
    public final void rule__ObjectOption__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:6330:1: ( ( ':' ) )
            // InternalModel2Blockly.g:6331:1: ( ':' )
            {
            // InternalModel2Blockly.g:6331:1: ( ':' )
            // InternalModel2Blockly.g:6332:2: ':'
            {
             before(grammarAccess.getObjectOptionAccess().getColonKeyword_1()); 
            match(input,56,FOLLOW_2); 
             after(grammarAccess.getObjectOptionAccess().getColonKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ObjectOption__Group__1__Impl"


    // $ANTLR start "rule__ObjectOption__Group__2"
    // InternalModel2Blockly.g:6341:1: rule__ObjectOption__Group__2 : rule__ObjectOption__Group__2__Impl rule__ObjectOption__Group__3 ;
    public final void rule__ObjectOption__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:6345:1: ( rule__ObjectOption__Group__2__Impl rule__ObjectOption__Group__3 )
            // InternalModel2Blockly.g:6346:2: rule__ObjectOption__Group__2__Impl rule__ObjectOption__Group__3
            {
            pushFollow(FOLLOW_39);
            rule__ObjectOption__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ObjectOption__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ObjectOption__Group__2"


    // $ANTLR start "rule__ObjectOption__Group__2__Impl"
    // InternalModel2Blockly.g:6353:1: rule__ObjectOption__Group__2__Impl : ( '{' ) ;
    public final void rule__ObjectOption__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:6357:1: ( ( '{' ) )
            // InternalModel2Blockly.g:6358:1: ( '{' )
            {
            // InternalModel2Blockly.g:6358:1: ( '{' )
            // InternalModel2Blockly.g:6359:2: '{'
            {
             before(grammarAccess.getObjectOptionAccess().getLeftCurlyBracketKeyword_2()); 
            match(input,45,FOLLOW_2); 
             after(grammarAccess.getObjectOptionAccess().getLeftCurlyBracketKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ObjectOption__Group__2__Impl"


    // $ANTLR start "rule__ObjectOption__Group__3"
    // InternalModel2Blockly.g:6368:1: rule__ObjectOption__Group__3 : rule__ObjectOption__Group__3__Impl rule__ObjectOption__Group__4 ;
    public final void rule__ObjectOption__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:6372:1: ( rule__ObjectOption__Group__3__Impl rule__ObjectOption__Group__4 )
            // InternalModel2Blockly.g:6373:2: rule__ObjectOption__Group__3__Impl rule__ObjectOption__Group__4
            {
            pushFollow(FOLLOW_39);
            rule__ObjectOption__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ObjectOption__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ObjectOption__Group__3"


    // $ANTLR start "rule__ObjectOption__Group__3__Impl"
    // InternalModel2Blockly.g:6380:1: rule__ObjectOption__Group__3__Impl : ( ( rule__ObjectOption__EntriesAssignment_3 )* ) ;
    public final void rule__ObjectOption__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:6384:1: ( ( ( rule__ObjectOption__EntriesAssignment_3 )* ) )
            // InternalModel2Blockly.g:6385:1: ( ( rule__ObjectOption__EntriesAssignment_3 )* )
            {
            // InternalModel2Blockly.g:6385:1: ( ( rule__ObjectOption__EntriesAssignment_3 )* )
            // InternalModel2Blockly.g:6386:2: ( rule__ObjectOption__EntriesAssignment_3 )*
            {
             before(grammarAccess.getObjectOptionAccess().getEntriesAssignment_3()); 
            // InternalModel2Blockly.g:6387:2: ( rule__ObjectOption__EntriesAssignment_3 )*
            loop68:
            do {
                int alt68=2;
                int LA68_0 = input.LA(1);

                if ( (LA68_0==RULE_ID) ) {
                    alt68=1;
                }


                switch (alt68) {
            	case 1 :
            	    // InternalModel2Blockly.g:6387:3: rule__ObjectOption__EntriesAssignment_3
            	    {
            	    pushFollow(FOLLOW_40);
            	    rule__ObjectOption__EntriesAssignment_3();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop68;
                }
            } while (true);

             after(grammarAccess.getObjectOptionAccess().getEntriesAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ObjectOption__Group__3__Impl"


    // $ANTLR start "rule__ObjectOption__Group__4"
    // InternalModel2Blockly.g:6395:1: rule__ObjectOption__Group__4 : rule__ObjectOption__Group__4__Impl ;
    public final void rule__ObjectOption__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:6399:1: ( rule__ObjectOption__Group__4__Impl )
            // InternalModel2Blockly.g:6400:2: rule__ObjectOption__Group__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ObjectOption__Group__4__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ObjectOption__Group__4"


    // $ANTLR start "rule__ObjectOption__Group__4__Impl"
    // InternalModel2Blockly.g:6406:1: rule__ObjectOption__Group__4__Impl : ( '}' ) ;
    public final void rule__ObjectOption__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:6410:1: ( ( '}' ) )
            // InternalModel2Blockly.g:6411:1: ( '}' )
            {
            // InternalModel2Blockly.g:6411:1: ( '}' )
            // InternalModel2Blockly.g:6412:2: '}'
            {
             before(grammarAccess.getObjectOptionAccess().getRightCurlyBracketKeyword_4()); 
            match(input,46,FOLLOW_2); 
             after(grammarAccess.getObjectOptionAccess().getRightCurlyBracketKeyword_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ObjectOption__Group__4__Impl"


    // $ANTLR start "rule__DomainModel__NameAssignment_1"
    // InternalModel2Blockly.g:6422:1: rule__DomainModel__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__DomainModel__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:6426:1: ( ( RULE_ID ) )
            // InternalModel2Blockly.g:6427:2: ( RULE_ID )
            {
            // InternalModel2Blockly.g:6427:2: ( RULE_ID )
            // InternalModel2Blockly.g:6428:3: RULE_ID
            {
             before(grammarAccess.getDomainModelAccess().getNameIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getDomainModelAccess().getNameIDTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DomainModel__NameAssignment_1"


    // $ANTLR start "rule__DomainModel__CodeLanguageAssignment_2_1"
    // InternalModel2Blockly.g:6437:1: rule__DomainModel__CodeLanguageAssignment_2_1 : ( RULE_STRING ) ;
    public final void rule__DomainModel__CodeLanguageAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:6441:1: ( ( RULE_STRING ) )
            // InternalModel2Blockly.g:6442:2: ( RULE_STRING )
            {
            // InternalModel2Blockly.g:6442:2: ( RULE_STRING )
            // InternalModel2Blockly.g:6443:3: RULE_STRING
            {
             before(grammarAccess.getDomainModelAccess().getCodeLanguageSTRINGTerminalRuleCall_2_1_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getDomainModelAccess().getCodeLanguageSTRINGTerminalRuleCall_2_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DomainModel__CodeLanguageAssignment_2_1"


    // $ANTLR start "rule__DomainModel__CodeFileExtensionAssignment_3_1"
    // InternalModel2Blockly.g:6452:1: rule__DomainModel__CodeFileExtensionAssignment_3_1 : ( RULE_STRING ) ;
    public final void rule__DomainModel__CodeFileExtensionAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:6456:1: ( ( RULE_STRING ) )
            // InternalModel2Blockly.g:6457:2: ( RULE_STRING )
            {
            // InternalModel2Blockly.g:6457:2: ( RULE_STRING )
            // InternalModel2Blockly.g:6458:3: RULE_STRING
            {
             before(grammarAccess.getDomainModelAccess().getCodeFileExtensionSTRINGTerminalRuleCall_3_1_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getDomainModelAccess().getCodeFileExtensionSTRINGTerminalRuleCall_3_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DomainModel__CodeFileExtensionAssignment_3_1"


    // $ANTLR start "rule__DomainModel__RuntimeKindAssignment_4_1"
    // InternalModel2Blockly.g:6467:1: rule__DomainModel__RuntimeKindAssignment_4_1 : ( RULE_STRING ) ;
    public final void rule__DomainModel__RuntimeKindAssignment_4_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:6471:1: ( ( RULE_STRING ) )
            // InternalModel2Blockly.g:6472:2: ( RULE_STRING )
            {
            // InternalModel2Blockly.g:6472:2: ( RULE_STRING )
            // InternalModel2Blockly.g:6473:3: RULE_STRING
            {
             before(grammarAccess.getDomainModelAccess().getRuntimeKindSTRINGTerminalRuleCall_4_1_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getDomainModelAccess().getRuntimeKindSTRINGTerminalRuleCall_4_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DomainModel__RuntimeKindAssignment_4_1"


    // $ANTLR start "rule__DomainModel__WorkspaceAssignment_5"
    // InternalModel2Blockly.g:6482:1: rule__DomainModel__WorkspaceAssignment_5 : ( ruleWorkspaceConfig ) ;
    public final void rule__DomainModel__WorkspaceAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:6486:1: ( ( ruleWorkspaceConfig ) )
            // InternalModel2Blockly.g:6487:2: ( ruleWorkspaceConfig )
            {
            // InternalModel2Blockly.g:6487:2: ( ruleWorkspaceConfig )
            // InternalModel2Blockly.g:6488:3: ruleWorkspaceConfig
            {
             before(grammarAccess.getDomainModelAccess().getWorkspaceWorkspaceConfigParserRuleCall_5_0()); 
            pushFollow(FOLLOW_2);
            ruleWorkspaceConfig();

            state._fsp--;

             after(grammarAccess.getDomainModelAccess().getWorkspaceWorkspaceConfigParserRuleCall_5_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DomainModel__WorkspaceAssignment_5"


    // $ANTLR start "rule__DomainModel__CategoriesAssignment_6"
    // InternalModel2Blockly.g:6497:1: rule__DomainModel__CategoriesAssignment_6 : ( ruleCategoryDef ) ;
    public final void rule__DomainModel__CategoriesAssignment_6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:6501:1: ( ( ruleCategoryDef ) )
            // InternalModel2Blockly.g:6502:2: ( ruleCategoryDef )
            {
            // InternalModel2Blockly.g:6502:2: ( ruleCategoryDef )
            // InternalModel2Blockly.g:6503:3: ruleCategoryDef
            {
             before(grammarAccess.getDomainModelAccess().getCategoriesCategoryDefParserRuleCall_6_0()); 
            pushFollow(FOLLOW_2);
            ruleCategoryDef();

            state._fsp--;

             after(grammarAccess.getDomainModelAccess().getCategoriesCategoryDefParserRuleCall_6_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DomainModel__CategoriesAssignment_6"


    // $ANTLR start "rule__DomainModel__ClassesAssignment_7"
    // InternalModel2Blockly.g:6512:1: rule__DomainModel__ClassesAssignment_7 : ( ruleClassDef ) ;
    public final void rule__DomainModel__ClassesAssignment_7() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:6516:1: ( ( ruleClassDef ) )
            // InternalModel2Blockly.g:6517:2: ( ruleClassDef )
            {
            // InternalModel2Blockly.g:6517:2: ( ruleClassDef )
            // InternalModel2Blockly.g:6518:3: ruleClassDef
            {
             before(grammarAccess.getDomainModelAccess().getClassesClassDefParserRuleCall_7_0()); 
            pushFollow(FOLLOW_2);
            ruleClassDef();

            state._fsp--;

             after(grammarAccess.getDomainModelAccess().getClassesClassDefParserRuleCall_7_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DomainModel__ClassesAssignment_7"


    // $ANTLR start "rule__DomainModel__ConstraintsAssignment_8"
    // InternalModel2Blockly.g:6527:1: rule__DomainModel__ConstraintsAssignment_8 : ( ruleConstraintDef ) ;
    public final void rule__DomainModel__ConstraintsAssignment_8() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:6531:1: ( ( ruleConstraintDef ) )
            // InternalModel2Blockly.g:6532:2: ( ruleConstraintDef )
            {
            // InternalModel2Blockly.g:6532:2: ( ruleConstraintDef )
            // InternalModel2Blockly.g:6533:3: ruleConstraintDef
            {
             before(grammarAccess.getDomainModelAccess().getConstraintsConstraintDefParserRuleCall_8_0()); 
            pushFollow(FOLLOW_2);
            ruleConstraintDef();

            state._fsp--;

             after(grammarAccess.getDomainModelAccess().getConstraintsConstraintDefParserRuleCall_8_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DomainModel__ConstraintsAssignment_8"


    // $ANTLR start "rule__DomainModel__ValidationsAssignment_9"
    // InternalModel2Blockly.g:6542:1: rule__DomainModel__ValidationsAssignment_9 : ( ruleValidationDef ) ;
    public final void rule__DomainModel__ValidationsAssignment_9() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:6546:1: ( ( ruleValidationDef ) )
            // InternalModel2Blockly.g:6547:2: ( ruleValidationDef )
            {
            // InternalModel2Blockly.g:6547:2: ( ruleValidationDef )
            // InternalModel2Blockly.g:6548:3: ruleValidationDef
            {
             before(grammarAccess.getDomainModelAccess().getValidationsValidationDefParserRuleCall_9_0()); 
            pushFollow(FOLLOW_2);
            ruleValidationDef();

            state._fsp--;

             after(grammarAccess.getDomainModelAccess().getValidationsValidationDefParserRuleCall_9_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DomainModel__ValidationsAssignment_9"


    // $ANTLR start "rule__CategoryDef__NameAssignment_1"
    // InternalModel2Blockly.g:6557:1: rule__CategoryDef__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__CategoryDef__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:6561:1: ( ( RULE_ID ) )
            // InternalModel2Blockly.g:6562:2: ( RULE_ID )
            {
            // InternalModel2Blockly.g:6562:2: ( RULE_ID )
            // InternalModel2Blockly.g:6563:3: RULE_ID
            {
             before(grammarAccess.getCategoryDefAccess().getNameIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getCategoryDefAccess().getNameIDTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CategoryDef__NameAssignment_1"


    // $ANTLR start "rule__CategoryDef__LabelAssignment_2_1"
    // InternalModel2Blockly.g:6572:1: rule__CategoryDef__LabelAssignment_2_1 : ( RULE_STRING ) ;
    public final void rule__CategoryDef__LabelAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:6576:1: ( ( RULE_STRING ) )
            // InternalModel2Blockly.g:6577:2: ( RULE_STRING )
            {
            // InternalModel2Blockly.g:6577:2: ( RULE_STRING )
            // InternalModel2Blockly.g:6578:3: RULE_STRING
            {
             before(grammarAccess.getCategoryDefAccess().getLabelSTRINGTerminalRuleCall_2_1_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getCategoryDefAccess().getLabelSTRINGTerminalRuleCall_2_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CategoryDef__LabelAssignment_2_1"


    // $ANTLR start "rule__CategoryDef__ColourAssignment_3_1"
    // InternalModel2Blockly.g:6587:1: rule__CategoryDef__ColourAssignment_3_1 : ( RULE_INT ) ;
    public final void rule__CategoryDef__ColourAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:6591:1: ( ( RULE_INT ) )
            // InternalModel2Blockly.g:6592:2: ( RULE_INT )
            {
            // InternalModel2Blockly.g:6592:2: ( RULE_INT )
            // InternalModel2Blockly.g:6593:3: RULE_INT
            {
             before(grammarAccess.getCategoryDefAccess().getColourINTTerminalRuleCall_3_1_0()); 
            match(input,RULE_INT,FOLLOW_2); 
             after(grammarAccess.getCategoryDefAccess().getColourINTTerminalRuleCall_3_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CategoryDef__ColourAssignment_3_1"


    // $ANTLR start "rule__CategoryDef__SubcategoriesAssignment_4_1"
    // InternalModel2Blockly.g:6602:1: rule__CategoryDef__SubcategoriesAssignment_4_1 : ( ruleCategoryDef ) ;
    public final void rule__CategoryDef__SubcategoriesAssignment_4_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:6606:1: ( ( ruleCategoryDef ) )
            // InternalModel2Blockly.g:6607:2: ( ruleCategoryDef )
            {
            // InternalModel2Blockly.g:6607:2: ( ruleCategoryDef )
            // InternalModel2Blockly.g:6608:3: ruleCategoryDef
            {
             before(grammarAccess.getCategoryDefAccess().getSubcategoriesCategoryDefParserRuleCall_4_1_0()); 
            pushFollow(FOLLOW_2);
            ruleCategoryDef();

            state._fsp--;

             after(grammarAccess.getCategoryDefAccess().getSubcategoriesCategoryDefParserRuleCall_4_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CategoryDef__SubcategoriesAssignment_4_1"


    // $ANTLR start "rule__ClassDef__AbstractAssignment_0"
    // InternalModel2Blockly.g:6617:1: rule__ClassDef__AbstractAssignment_0 : ( ( 'abstract' ) ) ;
    public final void rule__ClassDef__AbstractAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:6621:1: ( ( ( 'abstract' ) ) )
            // InternalModel2Blockly.g:6622:2: ( ( 'abstract' ) )
            {
            // InternalModel2Blockly.g:6622:2: ( ( 'abstract' ) )
            // InternalModel2Blockly.g:6623:3: ( 'abstract' )
            {
             before(grammarAccess.getClassDefAccess().getAbstractAbstractKeyword_0_0()); 
            // InternalModel2Blockly.g:6624:3: ( 'abstract' )
            // InternalModel2Blockly.g:6625:4: 'abstract'
            {
             before(grammarAccess.getClassDefAccess().getAbstractAbstractKeyword_0_0()); 
            match(input,89,FOLLOW_2); 
             after(grammarAccess.getClassDefAccess().getAbstractAbstractKeyword_0_0()); 

            }

             after(grammarAccess.getClassDefAccess().getAbstractAbstractKeyword_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__AbstractAssignment_0"


    // $ANTLR start "rule__ClassDef__OutputAssignment_1_0"
    // InternalModel2Blockly.g:6636:1: rule__ClassDef__OutputAssignment_1_0 : ( ( 'output' ) ) ;
    public final void rule__ClassDef__OutputAssignment_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:6640:1: ( ( ( 'output' ) ) )
            // InternalModel2Blockly.g:6641:2: ( ( 'output' ) )
            {
            // InternalModel2Blockly.g:6641:2: ( ( 'output' ) )
            // InternalModel2Blockly.g:6642:3: ( 'output' )
            {
             before(grammarAccess.getClassDefAccess().getOutputOutputKeyword_1_0_0()); 
            // InternalModel2Blockly.g:6643:3: ( 'output' )
            // InternalModel2Blockly.g:6644:4: 'output'
            {
             before(grammarAccess.getClassDefAccess().getOutputOutputKeyword_1_0_0()); 
            match(input,90,FOLLOW_2); 
             after(grammarAccess.getClassDefAccess().getOutputOutputKeyword_1_0_0()); 

            }

             after(grammarAccess.getClassDefAccess().getOutputOutputKeyword_1_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__OutputAssignment_1_0"


    // $ANTLR start "rule__ClassDef__OutputTypeAssignment_1_1_1"
    // InternalModel2Blockly.g:6655:1: rule__ClassDef__OutputTypeAssignment_1_1_1 : ( ( RULE_ID ) ) ;
    public final void rule__ClassDef__OutputTypeAssignment_1_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:6659:1: ( ( ( RULE_ID ) ) )
            // InternalModel2Blockly.g:6660:2: ( ( RULE_ID ) )
            {
            // InternalModel2Blockly.g:6660:2: ( ( RULE_ID ) )
            // InternalModel2Blockly.g:6661:3: ( RULE_ID )
            {
             before(grammarAccess.getClassDefAccess().getOutputTypeClassDefCrossReference_1_1_1_0()); 
            // InternalModel2Blockly.g:6662:3: ( RULE_ID )
            // InternalModel2Blockly.g:6663:4: RULE_ID
            {
             before(grammarAccess.getClassDefAccess().getOutputTypeClassDefIDTerminalRuleCall_1_1_1_0_1()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getClassDefAccess().getOutputTypeClassDefIDTerminalRuleCall_1_1_1_0_1()); 

            }

             after(grammarAccess.getClassDefAccess().getOutputTypeClassDefCrossReference_1_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__OutputTypeAssignment_1_1_1"


    // $ANTLR start "rule__ClassDef__NameAssignment_3"
    // InternalModel2Blockly.g:6674:1: rule__ClassDef__NameAssignment_3 : ( RULE_ID ) ;
    public final void rule__ClassDef__NameAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:6678:1: ( ( RULE_ID ) )
            // InternalModel2Blockly.g:6679:2: ( RULE_ID )
            {
            // InternalModel2Blockly.g:6679:2: ( RULE_ID )
            // InternalModel2Blockly.g:6680:3: RULE_ID
            {
             before(grammarAccess.getClassDefAccess().getNameIDTerminalRuleCall_3_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getClassDefAccess().getNameIDTerminalRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__NameAssignment_3"


    // $ANTLR start "rule__ClassDef__SuperClassAssignment_4_1"
    // InternalModel2Blockly.g:6689:1: rule__ClassDef__SuperClassAssignment_4_1 : ( ( RULE_ID ) ) ;
    public final void rule__ClassDef__SuperClassAssignment_4_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:6693:1: ( ( ( RULE_ID ) ) )
            // InternalModel2Blockly.g:6694:2: ( ( RULE_ID ) )
            {
            // InternalModel2Blockly.g:6694:2: ( ( RULE_ID ) )
            // InternalModel2Blockly.g:6695:3: ( RULE_ID )
            {
             before(grammarAccess.getClassDefAccess().getSuperClassClassDefCrossReference_4_1_0()); 
            // InternalModel2Blockly.g:6696:3: ( RULE_ID )
            // InternalModel2Blockly.g:6697:4: RULE_ID
            {
             before(grammarAccess.getClassDefAccess().getSuperClassClassDefIDTerminalRuleCall_4_1_0_1()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getClassDefAccess().getSuperClassClassDefIDTerminalRuleCall_4_1_0_1()); 

            }

             after(grammarAccess.getClassDefAccess().getSuperClassClassDefCrossReference_4_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__SuperClassAssignment_4_1"


    // $ANTLR start "rule__ClassDef__CategoryAssignment_5_1"
    // InternalModel2Blockly.g:6708:1: rule__ClassDef__CategoryAssignment_5_1 : ( ( RULE_ID ) ) ;
    public final void rule__ClassDef__CategoryAssignment_5_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:6712:1: ( ( ( RULE_ID ) ) )
            // InternalModel2Blockly.g:6713:2: ( ( RULE_ID ) )
            {
            // InternalModel2Blockly.g:6713:2: ( ( RULE_ID ) )
            // InternalModel2Blockly.g:6714:3: ( RULE_ID )
            {
             before(grammarAccess.getClassDefAccess().getCategoryCategoryDefCrossReference_5_1_0()); 
            // InternalModel2Blockly.g:6715:3: ( RULE_ID )
            // InternalModel2Blockly.g:6716:4: RULE_ID
            {
             before(grammarAccess.getClassDefAccess().getCategoryCategoryDefIDTerminalRuleCall_5_1_0_1()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getClassDefAccess().getCategoryCategoryDefIDTerminalRuleCall_5_1_0_1()); 

            }

             after(grammarAccess.getClassDefAccess().getCategoryCategoryDefCrossReference_5_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__CategoryAssignment_5_1"


    // $ANTLR start "rule__ClassDef__ColourAssignment_6_1"
    // InternalModel2Blockly.g:6727:1: rule__ClassDef__ColourAssignment_6_1 : ( RULE_INT ) ;
    public final void rule__ClassDef__ColourAssignment_6_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:6731:1: ( ( RULE_INT ) )
            // InternalModel2Blockly.g:6732:2: ( RULE_INT )
            {
            // InternalModel2Blockly.g:6732:2: ( RULE_INT )
            // InternalModel2Blockly.g:6733:3: RULE_INT
            {
             before(grammarAccess.getClassDefAccess().getColourINTTerminalRuleCall_6_1_0()); 
            match(input,RULE_INT,FOLLOW_2); 
             after(grammarAccess.getClassDefAccess().getColourINTTerminalRuleCall_6_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__ColourAssignment_6_1"


    // $ANTLR start "rule__ClassDef__LabelAssignment_7_1"
    // InternalModel2Blockly.g:6742:1: rule__ClassDef__LabelAssignment_7_1 : ( RULE_STRING ) ;
    public final void rule__ClassDef__LabelAssignment_7_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:6746:1: ( ( RULE_STRING ) )
            // InternalModel2Blockly.g:6747:2: ( RULE_STRING )
            {
            // InternalModel2Blockly.g:6747:2: ( RULE_STRING )
            // InternalModel2Blockly.g:6748:3: RULE_STRING
            {
             before(grammarAccess.getClassDefAccess().getLabelSTRINGTerminalRuleCall_7_1_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getClassDefAccess().getLabelSTRINGTerminalRuleCall_7_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__LabelAssignment_7_1"


    // $ANTLR start "rule__ClassDef__Message0Assignment_8_1"
    // InternalModel2Blockly.g:6757:1: rule__ClassDef__Message0Assignment_8_1 : ( RULE_STRING ) ;
    public final void rule__ClassDef__Message0Assignment_8_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:6761:1: ( ( RULE_STRING ) )
            // InternalModel2Blockly.g:6762:2: ( RULE_STRING )
            {
            // InternalModel2Blockly.g:6762:2: ( RULE_STRING )
            // InternalModel2Blockly.g:6763:3: RULE_STRING
            {
             before(grammarAccess.getClassDefAccess().getMessage0STRINGTerminalRuleCall_8_1_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getClassDefAccess().getMessage0STRINGTerminalRuleCall_8_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__Message0Assignment_8_1"


    // $ANTLR start "rule__ClassDef__TooltipAssignment_9_1"
    // InternalModel2Blockly.g:6772:1: rule__ClassDef__TooltipAssignment_9_1 : ( RULE_STRING ) ;
    public final void rule__ClassDef__TooltipAssignment_9_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:6776:1: ( ( RULE_STRING ) )
            // InternalModel2Blockly.g:6777:2: ( RULE_STRING )
            {
            // InternalModel2Blockly.g:6777:2: ( RULE_STRING )
            // InternalModel2Blockly.g:6778:3: RULE_STRING
            {
             before(grammarAccess.getClassDefAccess().getTooltipSTRINGTerminalRuleCall_9_1_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getClassDefAccess().getTooltipSTRINGTerminalRuleCall_9_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__TooltipAssignment_9_1"


    // $ANTLR start "rule__ClassDef__HelpUrlAssignment_10_1"
    // InternalModel2Blockly.g:6787:1: rule__ClassDef__HelpUrlAssignment_10_1 : ( RULE_STRING ) ;
    public final void rule__ClassDef__HelpUrlAssignment_10_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:6791:1: ( ( RULE_STRING ) )
            // InternalModel2Blockly.g:6792:2: ( RULE_STRING )
            {
            // InternalModel2Blockly.g:6792:2: ( RULE_STRING )
            // InternalModel2Blockly.g:6793:3: RULE_STRING
            {
             before(grammarAccess.getClassDefAccess().getHelpUrlSTRINGTerminalRuleCall_10_1_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getClassDefAccess().getHelpUrlSTRINGTerminalRuleCall_10_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__HelpUrlAssignment_10_1"


    // $ANTLR start "rule__ClassDef__InputsInlineAssignment_11_1"
    // InternalModel2Blockly.g:6802:1: rule__ClassDef__InputsInlineAssignment_11_1 : ( ruleBoolVal ) ;
    public final void rule__ClassDef__InputsInlineAssignment_11_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:6806:1: ( ( ruleBoolVal ) )
            // InternalModel2Blockly.g:6807:2: ( ruleBoolVal )
            {
            // InternalModel2Blockly.g:6807:2: ( ruleBoolVal )
            // InternalModel2Blockly.g:6808:3: ruleBoolVal
            {
             before(grammarAccess.getClassDefAccess().getInputsInlineBoolValEnumRuleCall_11_1_0()); 
            pushFollow(FOLLOW_2);
            ruleBoolVal();

            state._fsp--;

             after(grammarAccess.getClassDefAccess().getInputsInlineBoolValEnumRuleCall_11_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__InputsInlineAssignment_11_1"


    // $ANTLR start "rule__ClassDef__InlineAssignment_12"
    // InternalModel2Blockly.g:6817:1: rule__ClassDef__InlineAssignment_12 : ( ( 'inline' ) ) ;
    public final void rule__ClassDef__InlineAssignment_12() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:6821:1: ( ( ( 'inline' ) ) )
            // InternalModel2Blockly.g:6822:2: ( ( 'inline' ) )
            {
            // InternalModel2Blockly.g:6822:2: ( ( 'inline' ) )
            // InternalModel2Blockly.g:6823:3: ( 'inline' )
            {
             before(grammarAccess.getClassDefAccess().getInlineInlineKeyword_12_0()); 
            // InternalModel2Blockly.g:6824:3: ( 'inline' )
            // InternalModel2Blockly.g:6825:4: 'inline'
            {
             before(grammarAccess.getClassDefAccess().getInlineInlineKeyword_12_0()); 
            match(input,91,FOLLOW_2); 
             after(grammarAccess.getClassDefAccess().getInlineInlineKeyword_12_0()); 

            }

             after(grammarAccess.getClassDefAccess().getInlineInlineKeyword_12_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__InlineAssignment_12"


    // $ANTLR start "rule__ClassDef__CodeTemplateAssignment_13_1"
    // InternalModel2Blockly.g:6836:1: rule__ClassDef__CodeTemplateAssignment_13_1 : ( RULE_STRING ) ;
    public final void rule__ClassDef__CodeTemplateAssignment_13_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:6840:1: ( ( RULE_STRING ) )
            // InternalModel2Blockly.g:6841:2: ( RULE_STRING )
            {
            // InternalModel2Blockly.g:6841:2: ( RULE_STRING )
            // InternalModel2Blockly.g:6842:3: RULE_STRING
            {
             before(grammarAccess.getClassDefAccess().getCodeTemplateSTRINGTerminalRuleCall_13_1_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getClassDefAccess().getCodeTemplateSTRINGTerminalRuleCall_13_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__CodeTemplateAssignment_13_1"


    // $ANTLR start "rule__ClassDef__FeaturesAssignment_15"
    // InternalModel2Blockly.g:6851:1: rule__ClassDef__FeaturesAssignment_15 : ( ruleFeature ) ;
    public final void rule__ClassDef__FeaturesAssignment_15() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:6855:1: ( ( ruleFeature ) )
            // InternalModel2Blockly.g:6856:2: ( ruleFeature )
            {
            // InternalModel2Blockly.g:6856:2: ( ruleFeature )
            // InternalModel2Blockly.g:6857:3: ruleFeature
            {
             before(grammarAccess.getClassDefAccess().getFeaturesFeatureParserRuleCall_15_0()); 
            pushFollow(FOLLOW_2);
            ruleFeature();

            state._fsp--;

             after(grammarAccess.getClassDefAccess().getFeaturesFeatureParserRuleCall_15_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ClassDef__FeaturesAssignment_15"


    // $ANTLR start "rule__Attribute__NameAssignment_1"
    // InternalModel2Blockly.g:6866:1: rule__Attribute__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__Attribute__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:6870:1: ( ( RULE_ID ) )
            // InternalModel2Blockly.g:6871:2: ( RULE_ID )
            {
            // InternalModel2Blockly.g:6871:2: ( RULE_ID )
            // InternalModel2Blockly.g:6872:3: RULE_ID
            {
             before(grammarAccess.getAttributeAccess().getNameIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getAttributeAccess().getNameIDTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__NameAssignment_1"


    // $ANTLR start "rule__Attribute__TypeAssignment_3"
    // InternalModel2Blockly.g:6881:1: rule__Attribute__TypeAssignment_3 : ( ruleAttributeType ) ;
    public final void rule__Attribute__TypeAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:6885:1: ( ( ruleAttributeType ) )
            // InternalModel2Blockly.g:6886:2: ( ruleAttributeType )
            {
            // InternalModel2Blockly.g:6886:2: ( ruleAttributeType )
            // InternalModel2Blockly.g:6887:3: ruleAttributeType
            {
             before(grammarAccess.getAttributeAccess().getTypeAttributeTypeParserRuleCall_3_0()); 
            pushFollow(FOLLOW_2);
            ruleAttributeType();

            state._fsp--;

             after(grammarAccess.getAttributeAccess().getTypeAttributeTypeParserRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__TypeAssignment_3"


    // $ANTLR start "rule__Attribute__CardinalityAssignment_4"
    // InternalModel2Blockly.g:6896:1: rule__Attribute__CardinalityAssignment_4 : ( ruleCardinality ) ;
    public final void rule__Attribute__CardinalityAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:6900:1: ( ( ruleCardinality ) )
            // InternalModel2Blockly.g:6901:2: ( ruleCardinality )
            {
            // InternalModel2Blockly.g:6901:2: ( ruleCardinality )
            // InternalModel2Blockly.g:6902:3: ruleCardinality
            {
             before(grammarAccess.getAttributeAccess().getCardinalityCardinalityParserRuleCall_4_0()); 
            pushFollow(FOLLOW_2);
            ruleCardinality();

            state._fsp--;

             after(grammarAccess.getAttributeAccess().getCardinalityCardinalityParserRuleCall_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__CardinalityAssignment_4"


    // $ANTLR start "rule__Attribute__DefaultValueAssignment_5_1"
    // InternalModel2Blockly.g:6911:1: rule__Attribute__DefaultValueAssignment_5_1 : ( RULE_STRING ) ;
    public final void rule__Attribute__DefaultValueAssignment_5_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:6915:1: ( ( RULE_STRING ) )
            // InternalModel2Blockly.g:6916:2: ( RULE_STRING )
            {
            // InternalModel2Blockly.g:6916:2: ( RULE_STRING )
            // InternalModel2Blockly.g:6917:3: RULE_STRING
            {
             before(grammarAccess.getAttributeAccess().getDefaultValueSTRINGTerminalRuleCall_5_1_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getAttributeAccess().getDefaultValueSTRINGTerminalRuleCall_5_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__DefaultValueAssignment_5_1"


    // $ANTLR start "rule__Attribute__MinAssignment_6_1"
    // InternalModel2Blockly.g:6926:1: rule__Attribute__MinAssignment_6_1 : ( RULE_STRING ) ;
    public final void rule__Attribute__MinAssignment_6_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:6930:1: ( ( RULE_STRING ) )
            // InternalModel2Blockly.g:6931:2: ( RULE_STRING )
            {
            // InternalModel2Blockly.g:6931:2: ( RULE_STRING )
            // InternalModel2Blockly.g:6932:3: RULE_STRING
            {
             before(grammarAccess.getAttributeAccess().getMinSTRINGTerminalRuleCall_6_1_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getAttributeAccess().getMinSTRINGTerminalRuleCall_6_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__MinAssignment_6_1"


    // $ANTLR start "rule__Attribute__MaxAssignment_7_1"
    // InternalModel2Blockly.g:6941:1: rule__Attribute__MaxAssignment_7_1 : ( RULE_STRING ) ;
    public final void rule__Attribute__MaxAssignment_7_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:6945:1: ( ( RULE_STRING ) )
            // InternalModel2Blockly.g:6946:2: ( RULE_STRING )
            {
            // InternalModel2Blockly.g:6946:2: ( RULE_STRING )
            // InternalModel2Blockly.g:6947:3: RULE_STRING
            {
             before(grammarAccess.getAttributeAccess().getMaxSTRINGTerminalRuleCall_7_1_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getAttributeAccess().getMaxSTRINGTerminalRuleCall_7_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__MaxAssignment_7_1"


    // $ANTLR start "rule__Attribute__ImageUrlAssignment_8_1"
    // InternalModel2Blockly.g:6956:1: rule__Attribute__ImageUrlAssignment_8_1 : ( RULE_STRING ) ;
    public final void rule__Attribute__ImageUrlAssignment_8_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:6960:1: ( ( RULE_STRING ) )
            // InternalModel2Blockly.g:6961:2: ( RULE_STRING )
            {
            // InternalModel2Blockly.g:6961:2: ( RULE_STRING )
            // InternalModel2Blockly.g:6962:3: RULE_STRING
            {
             before(grammarAccess.getAttributeAccess().getImageUrlSTRINGTerminalRuleCall_8_1_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getAttributeAccess().getImageUrlSTRINGTerminalRuleCall_8_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__ImageUrlAssignment_8_1"


    // $ANTLR start "rule__Attribute__ImageWidthAssignment_9_1"
    // InternalModel2Blockly.g:6971:1: rule__Attribute__ImageWidthAssignment_9_1 : ( RULE_INT ) ;
    public final void rule__Attribute__ImageWidthAssignment_9_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:6975:1: ( ( RULE_INT ) )
            // InternalModel2Blockly.g:6976:2: ( RULE_INT )
            {
            // InternalModel2Blockly.g:6976:2: ( RULE_INT )
            // InternalModel2Blockly.g:6977:3: RULE_INT
            {
             before(grammarAccess.getAttributeAccess().getImageWidthINTTerminalRuleCall_9_1_0()); 
            match(input,RULE_INT,FOLLOW_2); 
             after(grammarAccess.getAttributeAccess().getImageWidthINTTerminalRuleCall_9_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__ImageWidthAssignment_9_1"


    // $ANTLR start "rule__Attribute__ImageHeightAssignment_10_1"
    // InternalModel2Blockly.g:6986:1: rule__Attribute__ImageHeightAssignment_10_1 : ( RULE_INT ) ;
    public final void rule__Attribute__ImageHeightAssignment_10_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:6990:1: ( ( RULE_INT ) )
            // InternalModel2Blockly.g:6991:2: ( RULE_INT )
            {
            // InternalModel2Blockly.g:6991:2: ( RULE_INT )
            // InternalModel2Blockly.g:6992:3: RULE_INT
            {
             before(grammarAccess.getAttributeAccess().getImageHeightINTTerminalRuleCall_10_1_0()); 
            match(input,RULE_INT,FOLLOW_2); 
             after(grammarAccess.getAttributeAccess().getImageHeightINTTerminalRuleCall_10_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__ImageHeightAssignment_10_1"


    // $ANTLR start "rule__Attribute__ImageAltAssignment_11_1"
    // InternalModel2Blockly.g:7001:1: rule__Attribute__ImageAltAssignment_11_1 : ( RULE_STRING ) ;
    public final void rule__Attribute__ImageAltAssignment_11_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:7005:1: ( ( RULE_STRING ) )
            // InternalModel2Blockly.g:7006:2: ( RULE_STRING )
            {
            // InternalModel2Blockly.g:7006:2: ( RULE_STRING )
            // InternalModel2Blockly.g:7007:3: RULE_STRING
            {
             before(grammarAccess.getAttributeAccess().getImageAltSTRINGTerminalRuleCall_11_1_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getAttributeAccess().getImageAltSTRINGTerminalRuleCall_11_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__ImageAltAssignment_11_1"


    // $ANTLR start "rule__Attribute__RequiredAssignment_12"
    // InternalModel2Blockly.g:7016:1: rule__Attribute__RequiredAssignment_12 : ( ( 'required' ) ) ;
    public final void rule__Attribute__RequiredAssignment_12() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:7020:1: ( ( ( 'required' ) ) )
            // InternalModel2Blockly.g:7021:2: ( ( 'required' ) )
            {
            // InternalModel2Blockly.g:7021:2: ( ( 'required' ) )
            // InternalModel2Blockly.g:7022:3: ( 'required' )
            {
             before(grammarAccess.getAttributeAccess().getRequiredRequiredKeyword_12_0()); 
            // InternalModel2Blockly.g:7023:3: ( 'required' )
            // InternalModel2Blockly.g:7024:4: 'required'
            {
             before(grammarAccess.getAttributeAccess().getRequiredRequiredKeyword_12_0()); 
            match(input,92,FOLLOW_2); 
             after(grammarAccess.getAttributeAccess().getRequiredRequiredKeyword_12_0()); 

            }

             after(grammarAccess.getAttributeAccess().getRequiredRequiredKeyword_12_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__RequiredAssignment_12"


    // $ANTLR start "rule__Attribute__IdAssignment_13"
    // InternalModel2Blockly.g:7035:1: rule__Attribute__IdAssignment_13 : ( ( 'modelId' ) ) ;
    public final void rule__Attribute__IdAssignment_13() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:7039:1: ( ( ( 'modelId' ) ) )
            // InternalModel2Blockly.g:7040:2: ( ( 'modelId' ) )
            {
            // InternalModel2Blockly.g:7040:2: ( ( 'modelId' ) )
            // InternalModel2Blockly.g:7041:3: ( 'modelId' )
            {
             before(grammarAccess.getAttributeAccess().getIdModelIdKeyword_13_0()); 
            // InternalModel2Blockly.g:7042:3: ( 'modelId' )
            // InternalModel2Blockly.g:7043:4: 'modelId'
            {
             before(grammarAccess.getAttributeAccess().getIdModelIdKeyword_13_0()); 
            match(input,93,FOLLOW_2); 
             after(grammarAccess.getAttributeAccess().getIdModelIdKeyword_13_0()); 

            }

             after(grammarAccess.getAttributeAccess().getIdModelIdKeyword_13_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__IdAssignment_13"


    // $ANTLR start "rule__Attribute__UniqueAssignment_14"
    // InternalModel2Blockly.g:7054:1: rule__Attribute__UniqueAssignment_14 : ( ( 'unique' ) ) ;
    public final void rule__Attribute__UniqueAssignment_14() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:7058:1: ( ( ( 'unique' ) ) )
            // InternalModel2Blockly.g:7059:2: ( ( 'unique' ) )
            {
            // InternalModel2Blockly.g:7059:2: ( ( 'unique' ) )
            // InternalModel2Blockly.g:7060:3: ( 'unique' )
            {
             before(grammarAccess.getAttributeAccess().getUniqueUniqueKeyword_14_0()); 
            // InternalModel2Blockly.g:7061:3: ( 'unique' )
            // InternalModel2Blockly.g:7062:4: 'unique'
            {
             before(grammarAccess.getAttributeAccess().getUniqueUniqueKeyword_14_0()); 
            match(input,94,FOLLOW_2); 
             after(grammarAccess.getAttributeAccess().getUniqueUniqueKeyword_14_0()); 

            }

             after(grammarAccess.getAttributeAccess().getUniqueUniqueKeyword_14_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__UniqueAssignment_14"


    // $ANTLR start "rule__Attribute__NonUniqueAssignment_15"
    // InternalModel2Blockly.g:7073:1: rule__Attribute__NonUniqueAssignment_15 : ( ( 'nonUnique' ) ) ;
    public final void rule__Attribute__NonUniqueAssignment_15() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:7077:1: ( ( ( 'nonUnique' ) ) )
            // InternalModel2Blockly.g:7078:2: ( ( 'nonUnique' ) )
            {
            // InternalModel2Blockly.g:7078:2: ( ( 'nonUnique' ) )
            // InternalModel2Blockly.g:7079:3: ( 'nonUnique' )
            {
             before(grammarAccess.getAttributeAccess().getNonUniqueNonUniqueKeyword_15_0()); 
            // InternalModel2Blockly.g:7080:3: ( 'nonUnique' )
            // InternalModel2Blockly.g:7081:4: 'nonUnique'
            {
             before(grammarAccess.getAttributeAccess().getNonUniqueNonUniqueKeyword_15_0()); 
            match(input,95,FOLLOW_2); 
             after(grammarAccess.getAttributeAccess().getNonUniqueNonUniqueKeyword_15_0()); 

            }

             after(grammarAccess.getAttributeAccess().getNonUniqueNonUniqueKeyword_15_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__NonUniqueAssignment_15"


    // $ANTLR start "rule__Attribute__OrderedAssignment_16"
    // InternalModel2Blockly.g:7092:1: rule__Attribute__OrderedAssignment_16 : ( ( 'ordered' ) ) ;
    public final void rule__Attribute__OrderedAssignment_16() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:7096:1: ( ( ( 'ordered' ) ) )
            // InternalModel2Blockly.g:7097:2: ( ( 'ordered' ) )
            {
            // InternalModel2Blockly.g:7097:2: ( ( 'ordered' ) )
            // InternalModel2Blockly.g:7098:3: ( 'ordered' )
            {
             before(grammarAccess.getAttributeAccess().getOrderedOrderedKeyword_16_0()); 
            // InternalModel2Blockly.g:7099:3: ( 'ordered' )
            // InternalModel2Blockly.g:7100:4: 'ordered'
            {
             before(grammarAccess.getAttributeAccess().getOrderedOrderedKeyword_16_0()); 
            match(input,96,FOLLOW_2); 
             after(grammarAccess.getAttributeAccess().getOrderedOrderedKeyword_16_0()); 

            }

             after(grammarAccess.getAttributeAccess().getOrderedOrderedKeyword_16_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__OrderedAssignment_16"


    // $ANTLR start "rule__Attribute__UnorderedAssignment_17"
    // InternalModel2Blockly.g:7111:1: rule__Attribute__UnorderedAssignment_17 : ( ( 'unordered' ) ) ;
    public final void rule__Attribute__UnorderedAssignment_17() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:7115:1: ( ( ( 'unordered' ) ) )
            // InternalModel2Blockly.g:7116:2: ( ( 'unordered' ) )
            {
            // InternalModel2Blockly.g:7116:2: ( ( 'unordered' ) )
            // InternalModel2Blockly.g:7117:3: ( 'unordered' )
            {
             before(grammarAccess.getAttributeAccess().getUnorderedUnorderedKeyword_17_0()); 
            // InternalModel2Blockly.g:7118:3: ( 'unordered' )
            // InternalModel2Blockly.g:7119:4: 'unordered'
            {
             before(grammarAccess.getAttributeAccess().getUnorderedUnorderedKeyword_17_0()); 
            match(input,97,FOLLOW_2); 
             after(grammarAccess.getAttributeAccess().getUnorderedUnorderedKeyword_17_0()); 

            }

             after(grammarAccess.getAttributeAccess().getUnorderedUnorderedKeyword_17_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__UnorderedAssignment_17"


    // $ANTLR start "rule__Attribute__UiAssignment_18"
    // InternalModel2Blockly.g:7130:1: rule__Attribute__UiAssignment_18 : ( ruleUiOptions ) ;
    public final void rule__Attribute__UiAssignment_18() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:7134:1: ( ( ruleUiOptions ) )
            // InternalModel2Blockly.g:7135:2: ( ruleUiOptions )
            {
            // InternalModel2Blockly.g:7135:2: ( ruleUiOptions )
            // InternalModel2Blockly.g:7136:3: ruleUiOptions
            {
             before(grammarAccess.getAttributeAccess().getUiUiOptionsParserRuleCall_18_0()); 
            pushFollow(FOLLOW_2);
            ruleUiOptions();

            state._fsp--;

             after(grammarAccess.getAttributeAccess().getUiUiOptionsParserRuleCall_18_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__UiAssignment_18"


    // $ANTLR start "rule__Containment__TypeAssignment_1"
    // InternalModel2Blockly.g:7145:1: rule__Containment__TypeAssignment_1 : ( ( RULE_ID ) ) ;
    public final void rule__Containment__TypeAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:7149:1: ( ( ( RULE_ID ) ) )
            // InternalModel2Blockly.g:7150:2: ( ( RULE_ID ) )
            {
            // InternalModel2Blockly.g:7150:2: ( ( RULE_ID ) )
            // InternalModel2Blockly.g:7151:3: ( RULE_ID )
            {
             before(grammarAccess.getContainmentAccess().getTypeClassDefCrossReference_1_0()); 
            // InternalModel2Blockly.g:7152:3: ( RULE_ID )
            // InternalModel2Blockly.g:7153:4: RULE_ID
            {
             before(grammarAccess.getContainmentAccess().getTypeClassDefIDTerminalRuleCall_1_0_1()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getContainmentAccess().getTypeClassDefIDTerminalRuleCall_1_0_1()); 

            }

             after(grammarAccess.getContainmentAccess().getTypeClassDefCrossReference_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Containment__TypeAssignment_1"


    // $ANTLR start "rule__Containment__NameAssignment_2"
    // InternalModel2Blockly.g:7164:1: rule__Containment__NameAssignment_2 : ( RULE_ID ) ;
    public final void rule__Containment__NameAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:7168:1: ( ( RULE_ID ) )
            // InternalModel2Blockly.g:7169:2: ( RULE_ID )
            {
            // InternalModel2Blockly.g:7169:2: ( RULE_ID )
            // InternalModel2Blockly.g:7170:3: RULE_ID
            {
             before(grammarAccess.getContainmentAccess().getNameIDTerminalRuleCall_2_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getContainmentAccess().getNameIDTerminalRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Containment__NameAssignment_2"


    // $ANTLR start "rule__Containment__LowerAssignment_3_1"
    // InternalModel2Blockly.g:7179:1: rule__Containment__LowerAssignment_3_1 : ( RULE_INT ) ;
    public final void rule__Containment__LowerAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:7183:1: ( ( RULE_INT ) )
            // InternalModel2Blockly.g:7184:2: ( RULE_INT )
            {
            // InternalModel2Blockly.g:7184:2: ( RULE_INT )
            // InternalModel2Blockly.g:7185:3: RULE_INT
            {
             before(grammarAccess.getContainmentAccess().getLowerINTTerminalRuleCall_3_1_0()); 
            match(input,RULE_INT,FOLLOW_2); 
             after(grammarAccess.getContainmentAccess().getLowerINTTerminalRuleCall_3_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Containment__LowerAssignment_3_1"


    // $ANTLR start "rule__Containment__UpperAssignment_3_3"
    // InternalModel2Blockly.g:7194:1: rule__Containment__UpperAssignment_3_3 : ( RULE_INT ) ;
    public final void rule__Containment__UpperAssignment_3_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:7198:1: ( ( RULE_INT ) )
            // InternalModel2Blockly.g:7199:2: ( RULE_INT )
            {
            // InternalModel2Blockly.g:7199:2: ( RULE_INT )
            // InternalModel2Blockly.g:7200:3: RULE_INT
            {
             before(grammarAccess.getContainmentAccess().getUpperINTTerminalRuleCall_3_3_0()); 
            match(input,RULE_INT,FOLLOW_2); 
             after(grammarAccess.getContainmentAccess().getUpperINTTerminalRuleCall_3_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Containment__UpperAssignment_3_3"


    // $ANTLR start "rule__Containment__UiAssignment_4"
    // InternalModel2Blockly.g:7209:1: rule__Containment__UiAssignment_4 : ( ruleUiOptions ) ;
    public final void rule__Containment__UiAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:7213:1: ( ( ruleUiOptions ) )
            // InternalModel2Blockly.g:7214:2: ( ruleUiOptions )
            {
            // InternalModel2Blockly.g:7214:2: ( ruleUiOptions )
            // InternalModel2Blockly.g:7215:3: ruleUiOptions
            {
             before(grammarAccess.getContainmentAccess().getUiUiOptionsParserRuleCall_4_0()); 
            pushFollow(FOLLOW_2);
            ruleUiOptions();

            state._fsp--;

             after(grammarAccess.getContainmentAccess().getUiUiOptionsParserRuleCall_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Containment__UiAssignment_4"


    // $ANTLR start "rule__Reference__TypeAssignment_1"
    // InternalModel2Blockly.g:7224:1: rule__Reference__TypeAssignment_1 : ( ( RULE_ID ) ) ;
    public final void rule__Reference__TypeAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:7228:1: ( ( ( RULE_ID ) ) )
            // InternalModel2Blockly.g:7229:2: ( ( RULE_ID ) )
            {
            // InternalModel2Blockly.g:7229:2: ( ( RULE_ID ) )
            // InternalModel2Blockly.g:7230:3: ( RULE_ID )
            {
             before(grammarAccess.getReferenceAccess().getTypeClassDefCrossReference_1_0()); 
            // InternalModel2Blockly.g:7231:3: ( RULE_ID )
            // InternalModel2Blockly.g:7232:4: RULE_ID
            {
             before(grammarAccess.getReferenceAccess().getTypeClassDefIDTerminalRuleCall_1_0_1()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getReferenceAccess().getTypeClassDefIDTerminalRuleCall_1_0_1()); 

            }

             after(grammarAccess.getReferenceAccess().getTypeClassDefCrossReference_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Reference__TypeAssignment_1"


    // $ANTLR start "rule__Reference__NameAssignment_2"
    // InternalModel2Blockly.g:7243:1: rule__Reference__NameAssignment_2 : ( RULE_ID ) ;
    public final void rule__Reference__NameAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:7247:1: ( ( RULE_ID ) )
            // InternalModel2Blockly.g:7248:2: ( RULE_ID )
            {
            // InternalModel2Blockly.g:7248:2: ( RULE_ID )
            // InternalModel2Blockly.g:7249:3: RULE_ID
            {
             before(grammarAccess.getReferenceAccess().getNameIDTerminalRuleCall_2_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getReferenceAccess().getNameIDTerminalRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Reference__NameAssignment_2"


    // $ANTLR start "rule__Reference__CardinalityAssignment_3"
    // InternalModel2Blockly.g:7258:1: rule__Reference__CardinalityAssignment_3 : ( ruleCardinality ) ;
    public final void rule__Reference__CardinalityAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:7262:1: ( ( ruleCardinality ) )
            // InternalModel2Blockly.g:7263:2: ( ruleCardinality )
            {
            // InternalModel2Blockly.g:7263:2: ( ruleCardinality )
            // InternalModel2Blockly.g:7264:3: ruleCardinality
            {
             before(grammarAccess.getReferenceAccess().getCardinalityCardinalityParserRuleCall_3_0()); 
            pushFollow(FOLLOW_2);
            ruleCardinality();

            state._fsp--;

             after(grammarAccess.getReferenceAccess().getCardinalityCardinalityParserRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Reference__CardinalityAssignment_3"


    // $ANTLR start "rule__Reference__OppositeNameAssignment_4_1"
    // InternalModel2Blockly.g:7273:1: rule__Reference__OppositeNameAssignment_4_1 : ( RULE_ID ) ;
    public final void rule__Reference__OppositeNameAssignment_4_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:7277:1: ( ( RULE_ID ) )
            // InternalModel2Blockly.g:7278:2: ( RULE_ID )
            {
            // InternalModel2Blockly.g:7278:2: ( RULE_ID )
            // InternalModel2Blockly.g:7279:3: RULE_ID
            {
             before(grammarAccess.getReferenceAccess().getOppositeNameIDTerminalRuleCall_4_1_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getReferenceAccess().getOppositeNameIDTerminalRuleCall_4_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Reference__OppositeNameAssignment_4_1"


    // $ANTLR start "rule__Reference__RequiredAssignment_5"
    // InternalModel2Blockly.g:7288:1: rule__Reference__RequiredAssignment_5 : ( ( 'required' ) ) ;
    public final void rule__Reference__RequiredAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:7292:1: ( ( ( 'required' ) ) )
            // InternalModel2Blockly.g:7293:2: ( ( 'required' ) )
            {
            // InternalModel2Blockly.g:7293:2: ( ( 'required' ) )
            // InternalModel2Blockly.g:7294:3: ( 'required' )
            {
             before(grammarAccess.getReferenceAccess().getRequiredRequiredKeyword_5_0()); 
            // InternalModel2Blockly.g:7295:3: ( 'required' )
            // InternalModel2Blockly.g:7296:4: 'required'
            {
             before(grammarAccess.getReferenceAccess().getRequiredRequiredKeyword_5_0()); 
            match(input,92,FOLLOW_2); 
             after(grammarAccess.getReferenceAccess().getRequiredRequiredKeyword_5_0()); 

            }

             after(grammarAccess.getReferenceAccess().getRequiredRequiredKeyword_5_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Reference__RequiredAssignment_5"


    // $ANTLR start "rule__Reference__UniqueAssignment_6"
    // InternalModel2Blockly.g:7307:1: rule__Reference__UniqueAssignment_6 : ( ( 'unique' ) ) ;
    public final void rule__Reference__UniqueAssignment_6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:7311:1: ( ( ( 'unique' ) ) )
            // InternalModel2Blockly.g:7312:2: ( ( 'unique' ) )
            {
            // InternalModel2Blockly.g:7312:2: ( ( 'unique' ) )
            // InternalModel2Blockly.g:7313:3: ( 'unique' )
            {
             before(grammarAccess.getReferenceAccess().getUniqueUniqueKeyword_6_0()); 
            // InternalModel2Blockly.g:7314:3: ( 'unique' )
            // InternalModel2Blockly.g:7315:4: 'unique'
            {
             before(grammarAccess.getReferenceAccess().getUniqueUniqueKeyword_6_0()); 
            match(input,94,FOLLOW_2); 
             after(grammarAccess.getReferenceAccess().getUniqueUniqueKeyword_6_0()); 

            }

             after(grammarAccess.getReferenceAccess().getUniqueUniqueKeyword_6_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Reference__UniqueAssignment_6"


    // $ANTLR start "rule__Reference__NonUniqueAssignment_7"
    // InternalModel2Blockly.g:7326:1: rule__Reference__NonUniqueAssignment_7 : ( ( 'nonUnique' ) ) ;
    public final void rule__Reference__NonUniqueAssignment_7() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:7330:1: ( ( ( 'nonUnique' ) ) )
            // InternalModel2Blockly.g:7331:2: ( ( 'nonUnique' ) )
            {
            // InternalModel2Blockly.g:7331:2: ( ( 'nonUnique' ) )
            // InternalModel2Blockly.g:7332:3: ( 'nonUnique' )
            {
             before(grammarAccess.getReferenceAccess().getNonUniqueNonUniqueKeyword_7_0()); 
            // InternalModel2Blockly.g:7333:3: ( 'nonUnique' )
            // InternalModel2Blockly.g:7334:4: 'nonUnique'
            {
             before(grammarAccess.getReferenceAccess().getNonUniqueNonUniqueKeyword_7_0()); 
            match(input,95,FOLLOW_2); 
             after(grammarAccess.getReferenceAccess().getNonUniqueNonUniqueKeyword_7_0()); 

            }

             after(grammarAccess.getReferenceAccess().getNonUniqueNonUniqueKeyword_7_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Reference__NonUniqueAssignment_7"


    // $ANTLR start "rule__Reference__OrderedAssignment_8"
    // InternalModel2Blockly.g:7345:1: rule__Reference__OrderedAssignment_8 : ( ( 'ordered' ) ) ;
    public final void rule__Reference__OrderedAssignment_8() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:7349:1: ( ( ( 'ordered' ) ) )
            // InternalModel2Blockly.g:7350:2: ( ( 'ordered' ) )
            {
            // InternalModel2Blockly.g:7350:2: ( ( 'ordered' ) )
            // InternalModel2Blockly.g:7351:3: ( 'ordered' )
            {
             before(grammarAccess.getReferenceAccess().getOrderedOrderedKeyword_8_0()); 
            // InternalModel2Blockly.g:7352:3: ( 'ordered' )
            // InternalModel2Blockly.g:7353:4: 'ordered'
            {
             before(grammarAccess.getReferenceAccess().getOrderedOrderedKeyword_8_0()); 
            match(input,96,FOLLOW_2); 
             after(grammarAccess.getReferenceAccess().getOrderedOrderedKeyword_8_0()); 

            }

             after(grammarAccess.getReferenceAccess().getOrderedOrderedKeyword_8_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Reference__OrderedAssignment_8"


    // $ANTLR start "rule__Reference__UnorderedAssignment_9"
    // InternalModel2Blockly.g:7364:1: rule__Reference__UnorderedAssignment_9 : ( ( 'unordered' ) ) ;
    public final void rule__Reference__UnorderedAssignment_9() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:7368:1: ( ( ( 'unordered' ) ) )
            // InternalModel2Blockly.g:7369:2: ( ( 'unordered' ) )
            {
            // InternalModel2Blockly.g:7369:2: ( ( 'unordered' ) )
            // InternalModel2Blockly.g:7370:3: ( 'unordered' )
            {
             before(grammarAccess.getReferenceAccess().getUnorderedUnorderedKeyword_9_0()); 
            // InternalModel2Blockly.g:7371:3: ( 'unordered' )
            // InternalModel2Blockly.g:7372:4: 'unordered'
            {
             before(grammarAccess.getReferenceAccess().getUnorderedUnorderedKeyword_9_0()); 
            match(input,97,FOLLOW_2); 
             after(grammarAccess.getReferenceAccess().getUnorderedUnorderedKeyword_9_0()); 

            }

             after(grammarAccess.getReferenceAccess().getUnorderedUnorderedKeyword_9_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Reference__UnorderedAssignment_9"


    // $ANTLR start "rule__Reference__UiAssignment_10"
    // InternalModel2Blockly.g:7383:1: rule__Reference__UiAssignment_10 : ( ruleUiOptions ) ;
    public final void rule__Reference__UiAssignment_10() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:7387:1: ( ( ruleUiOptions ) )
            // InternalModel2Blockly.g:7388:2: ( ruleUiOptions )
            {
            // InternalModel2Blockly.g:7388:2: ( ruleUiOptions )
            // InternalModel2Blockly.g:7389:3: ruleUiOptions
            {
             before(grammarAccess.getReferenceAccess().getUiUiOptionsParserRuleCall_10_0()); 
            pushFollow(FOLLOW_2);
            ruleUiOptions();

            state._fsp--;

             after(grammarAccess.getReferenceAccess().getUiUiOptionsParserRuleCall_10_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Reference__UiAssignment_10"


    // $ANTLR start "rule__ValueInput__TypeAssignment_1"
    // InternalModel2Blockly.g:7398:1: rule__ValueInput__TypeAssignment_1 : ( ( RULE_ID ) ) ;
    public final void rule__ValueInput__TypeAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:7402:1: ( ( ( RULE_ID ) ) )
            // InternalModel2Blockly.g:7403:2: ( ( RULE_ID ) )
            {
            // InternalModel2Blockly.g:7403:2: ( ( RULE_ID ) )
            // InternalModel2Blockly.g:7404:3: ( RULE_ID )
            {
             before(grammarAccess.getValueInputAccess().getTypeClassDefCrossReference_1_0()); 
            // InternalModel2Blockly.g:7405:3: ( RULE_ID )
            // InternalModel2Blockly.g:7406:4: RULE_ID
            {
             before(grammarAccess.getValueInputAccess().getTypeClassDefIDTerminalRuleCall_1_0_1()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getValueInputAccess().getTypeClassDefIDTerminalRuleCall_1_0_1()); 

            }

             after(grammarAccess.getValueInputAccess().getTypeClassDefCrossReference_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ValueInput__TypeAssignment_1"


    // $ANTLR start "rule__ValueInput__NameAssignment_2"
    // InternalModel2Blockly.g:7417:1: rule__ValueInput__NameAssignment_2 : ( RULE_ID ) ;
    public final void rule__ValueInput__NameAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:7421:1: ( ( RULE_ID ) )
            // InternalModel2Blockly.g:7422:2: ( RULE_ID )
            {
            // InternalModel2Blockly.g:7422:2: ( RULE_ID )
            // InternalModel2Blockly.g:7423:3: RULE_ID
            {
             before(grammarAccess.getValueInputAccess().getNameIDTerminalRuleCall_2_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getValueInputAccess().getNameIDTerminalRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ValueInput__NameAssignment_2"


    // $ANTLR start "rule__ValueInput__ShadowTypeAssignment_3_1"
    // InternalModel2Blockly.g:7432:1: rule__ValueInput__ShadowTypeAssignment_3_1 : ( ( RULE_ID ) ) ;
    public final void rule__ValueInput__ShadowTypeAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:7436:1: ( ( ( RULE_ID ) ) )
            // InternalModel2Blockly.g:7437:2: ( ( RULE_ID ) )
            {
            // InternalModel2Blockly.g:7437:2: ( ( RULE_ID ) )
            // InternalModel2Blockly.g:7438:3: ( RULE_ID )
            {
             before(grammarAccess.getValueInputAccess().getShadowTypeClassDefCrossReference_3_1_0()); 
            // InternalModel2Blockly.g:7439:3: ( RULE_ID )
            // InternalModel2Blockly.g:7440:4: RULE_ID
            {
             before(grammarAccess.getValueInputAccess().getShadowTypeClassDefIDTerminalRuleCall_3_1_0_1()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getValueInputAccess().getShadowTypeClassDefIDTerminalRuleCall_3_1_0_1()); 

            }

             after(grammarAccess.getValueInputAccess().getShadowTypeClassDefCrossReference_3_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ValueInput__ShadowTypeAssignment_3_1"


    // $ANTLR start "rule__ValueInput__UiAssignment_4"
    // InternalModel2Blockly.g:7451:1: rule__ValueInput__UiAssignment_4 : ( ruleUiOptions ) ;
    public final void rule__ValueInput__UiAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:7455:1: ( ( ruleUiOptions ) )
            // InternalModel2Blockly.g:7456:2: ( ruleUiOptions )
            {
            // InternalModel2Blockly.g:7456:2: ( ruleUiOptions )
            // InternalModel2Blockly.g:7457:3: ruleUiOptions
            {
             before(grammarAccess.getValueInputAccess().getUiUiOptionsParserRuleCall_4_0()); 
            pushFollow(FOLLOW_2);
            ruleUiOptions();

            state._fsp--;

             after(grammarAccess.getValueInputAccess().getUiUiOptionsParserRuleCall_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ValueInput__UiAssignment_4"


    // $ANTLR start "rule__Cardinality__LowerAssignment_1"
    // InternalModel2Blockly.g:7466:1: rule__Cardinality__LowerAssignment_1 : ( RULE_INT ) ;
    public final void rule__Cardinality__LowerAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:7470:1: ( ( RULE_INT ) )
            // InternalModel2Blockly.g:7471:2: ( RULE_INT )
            {
            // InternalModel2Blockly.g:7471:2: ( RULE_INT )
            // InternalModel2Blockly.g:7472:3: RULE_INT
            {
             before(grammarAccess.getCardinalityAccess().getLowerINTTerminalRuleCall_1_0()); 
            match(input,RULE_INT,FOLLOW_2); 
             after(grammarAccess.getCardinalityAccess().getLowerINTTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Cardinality__LowerAssignment_1"


    // $ANTLR start "rule__Cardinality__UpperAssignment_3"
    // InternalModel2Blockly.g:7481:1: rule__Cardinality__UpperAssignment_3 : ( RULE_INT ) ;
    public final void rule__Cardinality__UpperAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:7485:1: ( ( RULE_INT ) )
            // InternalModel2Blockly.g:7486:2: ( RULE_INT )
            {
            // InternalModel2Blockly.g:7486:2: ( RULE_INT )
            // InternalModel2Blockly.g:7487:3: RULE_INT
            {
             before(grammarAccess.getCardinalityAccess().getUpperINTTerminalRuleCall_3_0()); 
            match(input,RULE_INT,FOLLOW_2); 
             after(grammarAccess.getCardinalityAccess().getUpperINTTerminalRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Cardinality__UpperAssignment_3"


    // $ANTLR start "rule__UiOptions__WidgetAssignment_0_1"
    // InternalModel2Blockly.g:7496:1: rule__UiOptions__WidgetAssignment_0_1 : ( ruleUiWidget ) ;
    public final void rule__UiOptions__WidgetAssignment_0_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:7500:1: ( ( ruleUiWidget ) )
            // InternalModel2Blockly.g:7501:2: ( ruleUiWidget )
            {
            // InternalModel2Blockly.g:7501:2: ( ruleUiWidget )
            // InternalModel2Blockly.g:7502:3: ruleUiWidget
            {
             before(grammarAccess.getUiOptionsAccess().getWidgetUiWidgetEnumRuleCall_0_1_0()); 
            pushFollow(FOLLOW_2);
            ruleUiWidget();

            state._fsp--;

             after(grammarAccess.getUiOptionsAccess().getWidgetUiWidgetEnumRuleCall_0_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UiOptions__WidgetAssignment_0_1"


    // $ANTLR start "rule__UiOptions__UiLabelAssignment_1_1"
    // InternalModel2Blockly.g:7511:1: rule__UiOptions__UiLabelAssignment_1_1 : ( RULE_STRING ) ;
    public final void rule__UiOptions__UiLabelAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:7515:1: ( ( RULE_STRING ) )
            // InternalModel2Blockly.g:7516:2: ( RULE_STRING )
            {
            // InternalModel2Blockly.g:7516:2: ( RULE_STRING )
            // InternalModel2Blockly.g:7517:3: RULE_STRING
            {
             before(grammarAccess.getUiOptionsAccess().getUiLabelSTRINGTerminalRuleCall_1_1_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getUiOptionsAccess().getUiLabelSTRINGTerminalRuleCall_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UiOptions__UiLabelAssignment_1_1"


    // $ANTLR start "rule__UiOptions__HelpAssignment_2_1"
    // InternalModel2Blockly.g:7526:1: rule__UiOptions__HelpAssignment_2_1 : ( RULE_STRING ) ;
    public final void rule__UiOptions__HelpAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:7530:1: ( ( RULE_STRING ) )
            // InternalModel2Blockly.g:7531:2: ( RULE_STRING )
            {
            // InternalModel2Blockly.g:7531:2: ( RULE_STRING )
            // InternalModel2Blockly.g:7532:3: RULE_STRING
            {
             before(grammarAccess.getUiOptionsAccess().getHelpSTRINGTerminalRuleCall_2_1_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getUiOptionsAccess().getHelpSTRINGTerminalRuleCall_2_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UiOptions__HelpAssignment_2_1"


    // $ANTLR start "rule__UiOptions__PlaceholderAssignment_3_1"
    // InternalModel2Blockly.g:7541:1: rule__UiOptions__PlaceholderAssignment_3_1 : ( RULE_STRING ) ;
    public final void rule__UiOptions__PlaceholderAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:7545:1: ( ( RULE_STRING ) )
            // InternalModel2Blockly.g:7546:2: ( RULE_STRING )
            {
            // InternalModel2Blockly.g:7546:2: ( RULE_STRING )
            // InternalModel2Blockly.g:7547:3: RULE_STRING
            {
             before(grammarAccess.getUiOptionsAccess().getPlaceholderSTRINGTerminalRuleCall_3_1_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getUiOptionsAccess().getPlaceholderSTRINGTerminalRuleCall_3_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UiOptions__PlaceholderAssignment_3_1"


    // $ANTLR start "rule__UiOptions__GroupAssignment_4_1"
    // InternalModel2Blockly.g:7556:1: rule__UiOptions__GroupAssignment_4_1 : ( RULE_STRING ) ;
    public final void rule__UiOptions__GroupAssignment_4_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:7560:1: ( ( RULE_STRING ) )
            // InternalModel2Blockly.g:7561:2: ( RULE_STRING )
            {
            // InternalModel2Blockly.g:7561:2: ( RULE_STRING )
            // InternalModel2Blockly.g:7562:3: RULE_STRING
            {
             before(grammarAccess.getUiOptionsAccess().getGroupSTRINGTerminalRuleCall_4_1_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getUiOptionsAccess().getGroupSTRINGTerminalRuleCall_4_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UiOptions__GroupAssignment_4_1"


    // $ANTLR start "rule__UiOptions__OrderAssignment_5_1"
    // InternalModel2Blockly.g:7571:1: rule__UiOptions__OrderAssignment_5_1 : ( RULE_INT ) ;
    public final void rule__UiOptions__OrderAssignment_5_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:7575:1: ( ( RULE_INT ) )
            // InternalModel2Blockly.g:7576:2: ( RULE_INT )
            {
            // InternalModel2Blockly.g:7576:2: ( RULE_INT )
            // InternalModel2Blockly.g:7577:3: RULE_INT
            {
             before(grammarAccess.getUiOptionsAccess().getOrderINTTerminalRuleCall_5_1_0()); 
            match(input,RULE_INT,FOLLOW_2); 
             after(grammarAccess.getUiOptionsAccess().getOrderINTTerminalRuleCall_5_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UiOptions__OrderAssignment_5_1"


    // $ANTLR start "rule__UiOptions__UiReadonlyAssignment_6"
    // InternalModel2Blockly.g:7586:1: rule__UiOptions__UiReadonlyAssignment_6 : ( ( 'readonly' ) ) ;
    public final void rule__UiOptions__UiReadonlyAssignment_6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:7590:1: ( ( ( 'readonly' ) ) )
            // InternalModel2Blockly.g:7591:2: ( ( 'readonly' ) )
            {
            // InternalModel2Blockly.g:7591:2: ( ( 'readonly' ) )
            // InternalModel2Blockly.g:7592:3: ( 'readonly' )
            {
             before(grammarAccess.getUiOptionsAccess().getUiReadonlyReadonlyKeyword_6_0()); 
            // InternalModel2Blockly.g:7593:3: ( 'readonly' )
            // InternalModel2Blockly.g:7594:4: 'readonly'
            {
             before(grammarAccess.getUiOptionsAccess().getUiReadonlyReadonlyKeyword_6_0()); 
            match(input,98,FOLLOW_2); 
             after(grammarAccess.getUiOptionsAccess().getUiReadonlyReadonlyKeyword_6_0()); 

            }

             after(grammarAccess.getUiOptionsAccess().getUiReadonlyReadonlyKeyword_6_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UiOptions__UiReadonlyAssignment_6"


    // $ANTLR start "rule__UiOptions__UiHiddenAssignment_7"
    // InternalModel2Blockly.g:7605:1: rule__UiOptions__UiHiddenAssignment_7 : ( ( 'hidden' ) ) ;
    public final void rule__UiOptions__UiHiddenAssignment_7() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:7609:1: ( ( ( 'hidden' ) ) )
            // InternalModel2Blockly.g:7610:2: ( ( 'hidden' ) )
            {
            // InternalModel2Blockly.g:7610:2: ( ( 'hidden' ) )
            // InternalModel2Blockly.g:7611:3: ( 'hidden' )
            {
             before(grammarAccess.getUiOptionsAccess().getUiHiddenHiddenKeyword_7_0()); 
            // InternalModel2Blockly.g:7612:3: ( 'hidden' )
            // InternalModel2Blockly.g:7613:4: 'hidden'
            {
             before(grammarAccess.getUiOptionsAccess().getUiHiddenHiddenKeyword_7_0()); 
            match(input,99,FOLLOW_2); 
             after(grammarAccess.getUiOptionsAccess().getUiHiddenHiddenKeyword_7_0()); 

            }

             after(grammarAccess.getUiOptionsAccess().getUiHiddenHiddenKeyword_7_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UiOptions__UiHiddenAssignment_7"


    // $ANTLR start "rule__UiOptions__VariantAssignment_8_1"
    // InternalModel2Blockly.g:7624:1: rule__UiOptions__VariantAssignment_8_1 : ( ruleUiVariant ) ;
    public final void rule__UiOptions__VariantAssignment_8_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:7628:1: ( ( ruleUiVariant ) )
            // InternalModel2Blockly.g:7629:2: ( ruleUiVariant )
            {
            // InternalModel2Blockly.g:7629:2: ( ruleUiVariant )
            // InternalModel2Blockly.g:7630:3: ruleUiVariant
            {
             before(grammarAccess.getUiOptionsAccess().getVariantUiVariantEnumRuleCall_8_1_0()); 
            pushFollow(FOLLOW_2);
            ruleUiVariant();

            state._fsp--;

             after(grammarAccess.getUiOptionsAccess().getVariantUiVariantEnumRuleCall_8_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UiOptions__VariantAssignment_8_1"


    // $ANTLR start "rule__UiOptions__ReferenceLabelFieldAssignment_9_1"
    // InternalModel2Blockly.g:7639:1: rule__UiOptions__ReferenceLabelFieldAssignment_9_1 : ( RULE_ID ) ;
    public final void rule__UiOptions__ReferenceLabelFieldAssignment_9_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:7643:1: ( ( RULE_ID ) )
            // InternalModel2Blockly.g:7644:2: ( RULE_ID )
            {
            // InternalModel2Blockly.g:7644:2: ( RULE_ID )
            // InternalModel2Blockly.g:7645:3: RULE_ID
            {
             before(grammarAccess.getUiOptionsAccess().getReferenceLabelFieldIDTerminalRuleCall_9_1_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getUiOptionsAccess().getReferenceLabelFieldIDTerminalRuleCall_9_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UiOptions__ReferenceLabelFieldAssignment_9_1"


    // $ANTLR start "rule__SimpleType__TypeNameAssignment"
    // InternalModel2Blockly.g:7654:1: rule__SimpleType__TypeNameAssignment : ( ruleSimpleTypeName ) ;
    public final void rule__SimpleType__TypeNameAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:7658:1: ( ( ruleSimpleTypeName ) )
            // InternalModel2Blockly.g:7659:2: ( ruleSimpleTypeName )
            {
            // InternalModel2Blockly.g:7659:2: ( ruleSimpleTypeName )
            // InternalModel2Blockly.g:7660:3: ruleSimpleTypeName
            {
             before(grammarAccess.getSimpleTypeAccess().getTypeNameSimpleTypeNameEnumRuleCall_0()); 
            pushFollow(FOLLOW_2);
            ruleSimpleTypeName();

            state._fsp--;

             after(grammarAccess.getSimpleTypeAccess().getTypeNameSimpleTypeNameEnumRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SimpleType__TypeNameAssignment"


    // $ANTLR start "rule__EnumType__LiteralsAssignment_2"
    // InternalModel2Blockly.g:7669:1: rule__EnumType__LiteralsAssignment_2 : ( ruleEnumLiteral ) ;
    public final void rule__EnumType__LiteralsAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:7673:1: ( ( ruleEnumLiteral ) )
            // InternalModel2Blockly.g:7674:2: ( ruleEnumLiteral )
            {
            // InternalModel2Blockly.g:7674:2: ( ruleEnumLiteral )
            // InternalModel2Blockly.g:7675:3: ruleEnumLiteral
            {
             before(grammarAccess.getEnumTypeAccess().getLiteralsEnumLiteralParserRuleCall_2_0()); 
            pushFollow(FOLLOW_2);
            ruleEnumLiteral();

            state._fsp--;

             after(grammarAccess.getEnumTypeAccess().getLiteralsEnumLiteralParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EnumType__LiteralsAssignment_2"


    // $ANTLR start "rule__EnumType__LiteralsAssignment_3_1"
    // InternalModel2Blockly.g:7684:1: rule__EnumType__LiteralsAssignment_3_1 : ( ruleEnumLiteral ) ;
    public final void rule__EnumType__LiteralsAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:7688:1: ( ( ruleEnumLiteral ) )
            // InternalModel2Blockly.g:7689:2: ( ruleEnumLiteral )
            {
            // InternalModel2Blockly.g:7689:2: ( ruleEnumLiteral )
            // InternalModel2Blockly.g:7690:3: ruleEnumLiteral
            {
             before(grammarAccess.getEnumTypeAccess().getLiteralsEnumLiteralParserRuleCall_3_1_0()); 
            pushFollow(FOLLOW_2);
            ruleEnumLiteral();

            state._fsp--;

             after(grammarAccess.getEnumTypeAccess().getLiteralsEnumLiteralParserRuleCall_3_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EnumType__LiteralsAssignment_3_1"


    // $ANTLR start "rule__EnumLiteral__NameAssignment_0"
    // InternalModel2Blockly.g:7699:1: rule__EnumLiteral__NameAssignment_0 : ( RULE_ID ) ;
    public final void rule__EnumLiteral__NameAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:7703:1: ( ( RULE_ID ) )
            // InternalModel2Blockly.g:7704:2: ( RULE_ID )
            {
            // InternalModel2Blockly.g:7704:2: ( RULE_ID )
            // InternalModel2Blockly.g:7705:3: RULE_ID
            {
             before(grammarAccess.getEnumLiteralAccess().getNameIDTerminalRuleCall_0_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getEnumLiteralAccess().getNameIDTerminalRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EnumLiteral__NameAssignment_0"


    // $ANTLR start "rule__EnumLiteral__LabelAssignment_1_1"
    // InternalModel2Blockly.g:7714:1: rule__EnumLiteral__LabelAssignment_1_1 : ( RULE_STRING ) ;
    public final void rule__EnumLiteral__LabelAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:7718:1: ( ( RULE_STRING ) )
            // InternalModel2Blockly.g:7719:2: ( RULE_STRING )
            {
            // InternalModel2Blockly.g:7719:2: ( RULE_STRING )
            // InternalModel2Blockly.g:7720:3: RULE_STRING
            {
             before(grammarAccess.getEnumLiteralAccess().getLabelSTRINGTerminalRuleCall_1_1_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getEnumLiteralAccess().getLabelSTRINGTerminalRuleCall_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EnumLiteral__LabelAssignment_1_1"


    // $ANTLR start "rule__ConstraintDef__NameAssignment_1"
    // InternalModel2Blockly.g:7729:1: rule__ConstraintDef__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__ConstraintDef__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:7733:1: ( ( RULE_ID ) )
            // InternalModel2Blockly.g:7734:2: ( RULE_ID )
            {
            // InternalModel2Blockly.g:7734:2: ( RULE_ID )
            // InternalModel2Blockly.g:7735:3: RULE_ID
            {
             before(grammarAccess.getConstraintDefAccess().getNameIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getConstraintDefAccess().getNameIDTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConstraintDef__NameAssignment_1"


    // $ANTLR start "rule__ConstraintDef__TargetAssignment_3"
    // InternalModel2Blockly.g:7744:1: rule__ConstraintDef__TargetAssignment_3 : ( ( RULE_ID ) ) ;
    public final void rule__ConstraintDef__TargetAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:7748:1: ( ( ( RULE_ID ) ) )
            // InternalModel2Blockly.g:7749:2: ( ( RULE_ID ) )
            {
            // InternalModel2Blockly.g:7749:2: ( ( RULE_ID ) )
            // InternalModel2Blockly.g:7750:3: ( RULE_ID )
            {
             before(grammarAccess.getConstraintDefAccess().getTargetClassDefCrossReference_3_0()); 
            // InternalModel2Blockly.g:7751:3: ( RULE_ID )
            // InternalModel2Blockly.g:7752:4: RULE_ID
            {
             before(grammarAccess.getConstraintDefAccess().getTargetClassDefIDTerminalRuleCall_3_0_1()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getConstraintDefAccess().getTargetClassDefIDTerminalRuleCall_3_0_1()); 

            }

             after(grammarAccess.getConstraintDefAccess().getTargetClassDefCrossReference_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConstraintDef__TargetAssignment_3"


    // $ANTLR start "rule__ConstraintDef__PredecessorAssignment_7"
    // InternalModel2Blockly.g:7763:1: rule__ConstraintDef__PredecessorAssignment_7 : ( ( RULE_ID ) ) ;
    public final void rule__ConstraintDef__PredecessorAssignment_7() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:7767:1: ( ( ( RULE_ID ) ) )
            // InternalModel2Blockly.g:7768:2: ( ( RULE_ID ) )
            {
            // InternalModel2Blockly.g:7768:2: ( ( RULE_ID ) )
            // InternalModel2Blockly.g:7769:3: ( RULE_ID )
            {
             before(grammarAccess.getConstraintDefAccess().getPredecessorClassDefCrossReference_7_0()); 
            // InternalModel2Blockly.g:7770:3: ( RULE_ID )
            // InternalModel2Blockly.g:7771:4: RULE_ID
            {
             before(grammarAccess.getConstraintDefAccess().getPredecessorClassDefIDTerminalRuleCall_7_0_1()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getConstraintDefAccess().getPredecessorClassDefIDTerminalRuleCall_7_0_1()); 

            }

             after(grammarAccess.getConstraintDefAccess().getPredecessorClassDefCrossReference_7_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConstraintDef__PredecessorAssignment_7"


    // $ANTLR start "rule__ValidationDef__NameAssignment_1"
    // InternalModel2Blockly.g:7782:1: rule__ValidationDef__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__ValidationDef__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:7786:1: ( ( RULE_ID ) )
            // InternalModel2Blockly.g:7787:2: ( RULE_ID )
            {
            // InternalModel2Blockly.g:7787:2: ( RULE_ID )
            // InternalModel2Blockly.g:7788:3: RULE_ID
            {
             before(grammarAccess.getValidationDefAccess().getNameIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getValidationDefAccess().getNameIDTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ValidationDef__NameAssignment_1"


    // $ANTLR start "rule__ValidationDef__TargetAssignment_3"
    // InternalModel2Blockly.g:7797:1: rule__ValidationDef__TargetAssignment_3 : ( ( RULE_ID ) ) ;
    public final void rule__ValidationDef__TargetAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:7801:1: ( ( ( RULE_ID ) ) )
            // InternalModel2Blockly.g:7802:2: ( ( RULE_ID ) )
            {
            // InternalModel2Blockly.g:7802:2: ( ( RULE_ID ) )
            // InternalModel2Blockly.g:7803:3: ( RULE_ID )
            {
             before(grammarAccess.getValidationDefAccess().getTargetClassDefCrossReference_3_0()); 
            // InternalModel2Blockly.g:7804:3: ( RULE_ID )
            // InternalModel2Blockly.g:7805:4: RULE_ID
            {
             before(grammarAccess.getValidationDefAccess().getTargetClassDefIDTerminalRuleCall_3_0_1()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getValidationDefAccess().getTargetClassDefIDTerminalRuleCall_3_0_1()); 

            }

             after(grammarAccess.getValidationDefAccess().getTargetClassDefCrossReference_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ValidationDef__TargetAssignment_3"


    // $ANTLR start "rule__ValidationDef__KindAssignment_5"
    // InternalModel2Blockly.g:7816:1: rule__ValidationDef__KindAssignment_5 : ( ruleValidationKind ) ;
    public final void rule__ValidationDef__KindAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:7820:1: ( ( ruleValidationKind ) )
            // InternalModel2Blockly.g:7821:2: ( ruleValidationKind )
            {
            // InternalModel2Blockly.g:7821:2: ( ruleValidationKind )
            // InternalModel2Blockly.g:7822:3: ruleValidationKind
            {
             before(grammarAccess.getValidationDefAccess().getKindValidationKindEnumRuleCall_5_0()); 
            pushFollow(FOLLOW_2);
            ruleValidationKind();

            state._fsp--;

             after(grammarAccess.getValidationDefAccess().getKindValidationKindEnumRuleCall_5_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ValidationDef__KindAssignment_5"


    // $ANTLR start "rule__ValidationDef__ExpressionAssignment_6"
    // InternalModel2Blockly.g:7831:1: rule__ValidationDef__ExpressionAssignment_6 : ( RULE_STRING ) ;
    public final void rule__ValidationDef__ExpressionAssignment_6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:7835:1: ( ( RULE_STRING ) )
            // InternalModel2Blockly.g:7836:2: ( RULE_STRING )
            {
            // InternalModel2Blockly.g:7836:2: ( RULE_STRING )
            // InternalModel2Blockly.g:7837:3: RULE_STRING
            {
             before(grammarAccess.getValidationDefAccess().getExpressionSTRINGTerminalRuleCall_6_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getValidationDefAccess().getExpressionSTRINGTerminalRuleCall_6_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ValidationDef__ExpressionAssignment_6"


    // $ANTLR start "rule__ValidationDef__MessageAssignment_7_1"
    // InternalModel2Blockly.g:7846:1: rule__ValidationDef__MessageAssignment_7_1 : ( RULE_STRING ) ;
    public final void rule__ValidationDef__MessageAssignment_7_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:7850:1: ( ( RULE_STRING ) )
            // InternalModel2Blockly.g:7851:2: ( RULE_STRING )
            {
            // InternalModel2Blockly.g:7851:2: ( RULE_STRING )
            // InternalModel2Blockly.g:7852:3: RULE_STRING
            {
             before(grammarAccess.getValidationDefAccess().getMessageSTRINGTerminalRuleCall_7_1_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getValidationDefAccess().getMessageSTRINGTerminalRuleCall_7_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ValidationDef__MessageAssignment_7_1"


    // $ANTLR start "rule__WorkspaceConfig__OptionsAssignment_2"
    // InternalModel2Blockly.g:7861:1: rule__WorkspaceConfig__OptionsAssignment_2 : ( ruleWorkspaceOption ) ;
    public final void rule__WorkspaceConfig__OptionsAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:7865:1: ( ( ruleWorkspaceOption ) )
            // InternalModel2Blockly.g:7866:2: ( ruleWorkspaceOption )
            {
            // InternalModel2Blockly.g:7866:2: ( ruleWorkspaceOption )
            // InternalModel2Blockly.g:7867:3: ruleWorkspaceOption
            {
             before(grammarAccess.getWorkspaceConfigAccess().getOptionsWorkspaceOptionParserRuleCall_2_0()); 
            pushFollow(FOLLOW_2);
            ruleWorkspaceOption();

            state._fsp--;

             after(grammarAccess.getWorkspaceConfigAccess().getOptionsWorkspaceOptionParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WorkspaceConfig__OptionsAssignment_2"


    // $ANTLR start "rule__StringOption__KeyAssignment_0"
    // InternalModel2Blockly.g:7876:1: rule__StringOption__KeyAssignment_0 : ( RULE_ID ) ;
    public final void rule__StringOption__KeyAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:7880:1: ( ( RULE_ID ) )
            // InternalModel2Blockly.g:7881:2: ( RULE_ID )
            {
            // InternalModel2Blockly.g:7881:2: ( RULE_ID )
            // InternalModel2Blockly.g:7882:3: RULE_ID
            {
             before(grammarAccess.getStringOptionAccess().getKeyIDTerminalRuleCall_0_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getStringOptionAccess().getKeyIDTerminalRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__StringOption__KeyAssignment_0"


    // $ANTLR start "rule__StringOption__ValueAssignment_2"
    // InternalModel2Blockly.g:7891:1: rule__StringOption__ValueAssignment_2 : ( RULE_STRING ) ;
    public final void rule__StringOption__ValueAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:7895:1: ( ( RULE_STRING ) )
            // InternalModel2Blockly.g:7896:2: ( RULE_STRING )
            {
            // InternalModel2Blockly.g:7896:2: ( RULE_STRING )
            // InternalModel2Blockly.g:7897:3: RULE_STRING
            {
             before(grammarAccess.getStringOptionAccess().getValueSTRINGTerminalRuleCall_2_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getStringOptionAccess().getValueSTRINGTerminalRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__StringOption__ValueAssignment_2"


    // $ANTLR start "rule__IntOption__KeyAssignment_0"
    // InternalModel2Blockly.g:7906:1: rule__IntOption__KeyAssignment_0 : ( RULE_ID ) ;
    public final void rule__IntOption__KeyAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:7910:1: ( ( RULE_ID ) )
            // InternalModel2Blockly.g:7911:2: ( RULE_ID )
            {
            // InternalModel2Blockly.g:7911:2: ( RULE_ID )
            // InternalModel2Blockly.g:7912:3: RULE_ID
            {
             before(grammarAccess.getIntOptionAccess().getKeyIDTerminalRuleCall_0_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getIntOptionAccess().getKeyIDTerminalRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IntOption__KeyAssignment_0"


    // $ANTLR start "rule__IntOption__ValueAssignment_2"
    // InternalModel2Blockly.g:7921:1: rule__IntOption__ValueAssignment_2 : ( RULE_INT ) ;
    public final void rule__IntOption__ValueAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:7925:1: ( ( RULE_INT ) )
            // InternalModel2Blockly.g:7926:2: ( RULE_INT )
            {
            // InternalModel2Blockly.g:7926:2: ( RULE_INT )
            // InternalModel2Blockly.g:7927:3: RULE_INT
            {
             before(grammarAccess.getIntOptionAccess().getValueINTTerminalRuleCall_2_0()); 
            match(input,RULE_INT,FOLLOW_2); 
             after(grammarAccess.getIntOptionAccess().getValueINTTerminalRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IntOption__ValueAssignment_2"


    // $ANTLR start "rule__BoolOption__KeyAssignment_0"
    // InternalModel2Blockly.g:7936:1: rule__BoolOption__KeyAssignment_0 : ( RULE_ID ) ;
    public final void rule__BoolOption__KeyAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:7940:1: ( ( RULE_ID ) )
            // InternalModel2Blockly.g:7941:2: ( RULE_ID )
            {
            // InternalModel2Blockly.g:7941:2: ( RULE_ID )
            // InternalModel2Blockly.g:7942:3: RULE_ID
            {
             before(grammarAccess.getBoolOptionAccess().getKeyIDTerminalRuleCall_0_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getBoolOptionAccess().getKeyIDTerminalRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BoolOption__KeyAssignment_0"


    // $ANTLR start "rule__BoolOption__ValueAssignment_2"
    // InternalModel2Blockly.g:7951:1: rule__BoolOption__ValueAssignment_2 : ( ruleBoolVal ) ;
    public final void rule__BoolOption__ValueAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:7955:1: ( ( ruleBoolVal ) )
            // InternalModel2Blockly.g:7956:2: ( ruleBoolVal )
            {
            // InternalModel2Blockly.g:7956:2: ( ruleBoolVal )
            // InternalModel2Blockly.g:7957:3: ruleBoolVal
            {
             before(grammarAccess.getBoolOptionAccess().getValueBoolValEnumRuleCall_2_0()); 
            pushFollow(FOLLOW_2);
            ruleBoolVal();

            state._fsp--;

             after(grammarAccess.getBoolOptionAccess().getValueBoolValEnumRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BoolOption__ValueAssignment_2"


    // $ANTLR start "rule__ObjectOption__KeyAssignment_0"
    // InternalModel2Blockly.g:7966:1: rule__ObjectOption__KeyAssignment_0 : ( RULE_ID ) ;
    public final void rule__ObjectOption__KeyAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:7970:1: ( ( RULE_ID ) )
            // InternalModel2Blockly.g:7971:2: ( RULE_ID )
            {
            // InternalModel2Blockly.g:7971:2: ( RULE_ID )
            // InternalModel2Blockly.g:7972:3: RULE_ID
            {
             before(grammarAccess.getObjectOptionAccess().getKeyIDTerminalRuleCall_0_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getObjectOptionAccess().getKeyIDTerminalRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ObjectOption__KeyAssignment_0"


    // $ANTLR start "rule__ObjectOption__EntriesAssignment_3"
    // InternalModel2Blockly.g:7981:1: rule__ObjectOption__EntriesAssignment_3 : ( ruleWorkspaceOption ) ;
    public final void rule__ObjectOption__EntriesAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalModel2Blockly.g:7985:1: ( ( ruleWorkspaceOption ) )
            // InternalModel2Blockly.g:7986:2: ( ruleWorkspaceOption )
            {
            // InternalModel2Blockly.g:7986:2: ( ruleWorkspaceOption )
            // InternalModel2Blockly.g:7987:3: ruleWorkspaceOption
            {
             before(grammarAccess.getObjectOptionAccess().getEntriesWorkspaceOptionParserRuleCall_3_0()); 
            pushFollow(FOLLOW_2);
            ruleWorkspaceOption();

            state._fsp--;

             after(grammarAccess.getObjectOptionAccess().getEntriesWorkspaceOptionParserRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ObjectOption__EntriesAssignment_3"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000000002L,0x0000000C00007F80L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x00009E0000000000L,0x0000000007440000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000100000000002L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000800000000002L,0x0000000006000000L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000000002L,0x0000000000040000L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000000002L,0x0000000000400000L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000200300000000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000500000000000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000800000000000L,0x0000000006000000L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x007E300300000000L,0x0000000008000000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x8080400000000000L,0x0000000000000028L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x8080000000000002L,0x0000000000000028L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x000000C000000000L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x00000003F0300000L,0x0000000000008000L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x7E00000002000000L,0x0000000FF0007F81L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000000000000000L,0x0000000C00007F81L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000000000000000L,0x0000000FD0007F91L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x0000000000000000L,0x0000000C00007FC0L});
    public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x0000000001FFF800L});
    public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x000000000E000000L});
    public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_31 = new BitSet(new long[]{0x0000400000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_32 = new BitSet(new long[]{0x0000000000000002L,0x0000000000010000L});
    public static final BitSet FOLLOW_33 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_34 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_35 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_36 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_37 = new BitSet(new long[]{0x0000003C00000000L});
    public static final BitSet FOLLOW_38 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_39 = new BitSet(new long[]{0x0000400000000010L});
    public static final BitSet FOLLOW_40 = new BitSet(new long[]{0x0000000000000012L});

}