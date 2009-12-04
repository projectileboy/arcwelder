package com.bitbakery.arcwelder.highlighter;

import com.bitbakery.arcwelder.lexer.ArcLexer;
import com.bitbakery.arcwelder.lexer.ArcTokenTypes;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.SyntaxHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * Defines Arc tokens and elements which can have custom font and color to enhance readability
 */
public class ArcSyntaxHighlighter extends SyntaxHighlighterBase {
    private static Map<IElementType, TextAttributesKey> ATTRIBUTES = new HashMap<IElementType, TextAttributesKey>();

    // (1) Define IDs
    // TODO - i18n all these guys...
    @NonNls
    public static final String COMMENT_ID = "Comment";
    @NonNls
    public static final String DOCSTRING_ID = "Docstring";
    @NonNls
    public static final String NUMBER_ID = "Numeric literal";
    @NonNls
    public static final String STRING_ID = "String literal";
    @NonNls
    public static final String CHAR_ID = "Character literal";
    @NonNls
    public static final String BOOLEAN_ID = "Boolean literal";
    @NonNls
    public static final String DEF_ID = "Def";
    @NonNls
    public static final String MAC_ID = "Mac";
    @NonNls
    public static final String SPECIAL_CHARACTER_ID = "Special characters";
    @NonNls
    public static final String MACRO_TEMPLATE_ID = "Macro template characters ( , ,@ ' ` )";
    @NonNls
    public static final String SPECIAL_FORM_ID = "Special forms (if, let, etc.)";
    @NonNls
    public static final String SQUARE_BRACKET_ID = "Square brackets [ ]";
    @NonNls
    public static final String PAREN_ID = "Parens ( )";
    @NonNls
    public static final String BAD_CHARACTER_ID = "Invalid character";


    // (2) Register TextAttributes
    private static void createKey(String id, TextAttributesKey prototype) {
        TextAttributesKey.createTextAttributesKey(id, prototype.getDefaultAttributes());
    }

    static {
        createKey(COMMENT_ID, SyntaxHighlighterColors.LINE_COMMENT);
        createKey(DOCSTRING_ID, SyntaxHighlighterColors.DOC_COMMENT);
        createKey(NUMBER_ID, SyntaxHighlighterColors.NUMBER);
        createKey(STRING_ID, SyntaxHighlighterColors.STRING);
        createKey(CHAR_ID, SyntaxHighlighterColors.STRING);
        createKey(BOOLEAN_ID, SyntaxHighlighterColors.NUMBER);
        createKey(DEF_ID, SyntaxHighlighterColors.KEYWORD);
        createKey(MAC_ID, SyntaxHighlighterColors.KEYWORD);
        createKey(SPECIAL_CHARACTER_ID, SyntaxHighlighterColors.KEYWORD);
        createKey(MACRO_TEMPLATE_ID, SyntaxHighlighterColors.KEYWORD);
        createKey(SPECIAL_FORM_ID, SyntaxHighlighterColors.KEYWORD);
        createKey(SQUARE_BRACKET_ID, SyntaxHighlighterColors.BRACKETS);
        createKey(PAREN_ID, SyntaxHighlighterColors.PARENTHS);
    }


    // (3) Define TextAttributesKeys
    public static TextAttributesKey COMMENT = TextAttributesKey.createTextAttributesKey(COMMENT_ID);
    public static TextAttributesKey DOCSTRING = TextAttributesKey.createTextAttributesKey(DOCSTRING_ID);
    public static TextAttributesKey NUMBER = TextAttributesKey.createTextAttributesKey(NUMBER_ID);
    public static TextAttributesKey STRING = TextAttributesKey.createTextAttributesKey(STRING_ID);
    public static TextAttributesKey CHAR = TextAttributesKey.createTextAttributesKey(CHAR_ID);
    public static TextAttributesKey BOOLEAN = TextAttributesKey.createTextAttributesKey(BOOLEAN_ID);
    public static TextAttributesKey DEF = TextAttributesKey.createTextAttributesKey(DEF_ID);
    public static TextAttributesKey MAC = TextAttributesKey.createTextAttributesKey(MAC_ID);
    public static TextAttributesKey SPECIAL_CHARACTER = TextAttributesKey.createTextAttributesKey(SPECIAL_CHARACTER_ID);
    public static TextAttributesKey MACRO_TEMPLATE_CHARACTER = TextAttributesKey.createTextAttributesKey(MACRO_TEMPLATE_ID);
    public static TextAttributesKey SPECIAL_FORM = TextAttributesKey.createTextAttributesKey(SPECIAL_FORM_ID);
    public static TextAttributesKey PAREN = TextAttributesKey.createTextAttributesKey(PAREN_ID);
    public static TextAttributesKey SQUARE_BRACKET = TextAttributesKey.createTextAttributesKey(SQUARE_BRACKET_ID);

    // (4) Build token --> color map
    static {
        fillMap(ATTRIBUTES, ArcTokenTypes.COMMENTS, COMMENT);
//        fillMap(ATTRIBUTES, DOCSTRING, ArcElementTypes.DOCSTRING);
//        fillMap(ATTRIBUTES, NUMBER, NUMERIC_LITERAL);
        fillMap(ATTRIBUTES, ArcTokenTypes.STRING_LITERALS, STRING);
//        fillMap(ATTRIBUTES, CHAR, CHAR_LITERAL);
//        fillMap(ATTRIBUTES, BOOLEAN_LITERALS, BOOLEAN);
        SyntaxHighlighterBase.fillMap(ATTRIBUTES, DEF, ArcTokenTypes.DEF);
        fillMap(ATTRIBUTES, MAC, ArcTokenTypes.MAC);
//        fillMap(ATTRIBUTES, SPECIAL_CHARACTER, COMPOSER, DOT, TILDE, EQ);
//        fillMap(ATTRIBUTES, MACRO_TEMPLATE_CHARACTER, QUOTE, BACKQUOTE, COMMA, COMMA_AT);
//        fillMap(ATTRIBUTES, SPECIAL_FORM, FN, IF, LET, WITH, QUOTE_KEYWORD, DO);
//        fillMap(ATTRIBUTES, PAREN, LEFT_PAREN, RIGHT_PAREN);
//        fillMap(ATTRIBUTES, SQUARE_BRACKET, LEFT_SQUARE, RIGHT_SQUARE);
    }

    @NotNull
    public Lexer getHighlightingLexer() {
        return new ArcLexer();
    }

    @NotNull
    public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
        return pack(ATTRIBUTES.get(tokenType));
    }


}
/*














extends SyntaxHighlighterBase implements ClojureTokenTypes {
  38
  39   private static final Map<IElementType, TextAttributesKey> ATTRIBUTES = new HashMap<IElementType, TextAttributesKey>();
  40
  41
  42   static final TokenSet sNUMBERS = TokenSet.create(
  43       INTEGER_LITERAL, LONG_LITERAL, BIG_INT_LITERAL, FLOAT_LITERAL, DOUBLE_LITERAL, BIG_DECIMAL_LITERAL, RATIO
  44   );
  45
  46   static final TokenSet sLINE_COMMENTS = TokenSet.create(
  47       ClojureTokenTypes.LINE_COMMENT
  48   );
  49
  50   static final TokenSet sBAD_CHARACTERS = TokenSet.create(
  51       ClojureTokenTypes.BAD_CHARACTER
  52   );
  53
  54   static final TokenSet sLITERALS = TokenSet.create(ClojureTokenTypes.TRUE, ClojureTokenTypes.FALSE, ClojureTokenTypes.NIL);
  55
  56   static final TokenSet sSTRINGS = ClojureTokenTypes.STRINGS;
  57
  58   static final TokenSet sCHARS = TokenSet.create(ClojureTokenTypes.CHAR_LITERAL);
  59
  60   static final TokenSet sPARENTS = TokenSet.create(
  61       ClojureTokenTypes.LEFT_PAREN,
  62       ClojureTokenTypes.RIGHT_PAREN
  63   );
  64
  65   static final TokenSet sBRACES = TokenSet.create(
  66       ClojureTokenTypes.LEFT_SQUARE,
  67       ClojureTokenTypes.RIGHT_SQUARE,
  68       ClojureTokenTypes.LEFT_CURLY,
  69       ClojureTokenTypes.RIGHT_CURLY
  70   );
  71
  72   public static final TokenSet sATOMS = symS;
  73
  74   public static final TokenSet sKEYS = TokenSet.create(
  75       ClojureTokenTypes.COLON_SYMBOL
  76   );
  77
  78   @NotNull
  79   public Lexer getHighlightingLexer() {
  80     return new ClojureFlexLexer();
  81   }
  82
  83   @NotNull
  84   public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
  85     return pack(ATTRIBUTES.get(tokenType));
  86   }
  87
  88   @NonNls
  89   static final String LINE_COMMENT_ID = "Clojure Line comment";
  90   @NonNls
  91   static final String KEY_ID = "Clojure Keyword";
  92   @NonNls
  93   static final String DEF_ID = "First symbol in list";
  94   @NonNls
  95   static final String ATOM_ID = "Clojure Atom";
  96   @NonNls
  97   static final String NUMBER_ID = "Clojure Numbers";
  98   @NonNls
  99   static final String STRING_ID = "Clojure Strings";
 100   @NonNls
 101   static final String BAD_CHARACTER_ID = "Bad character";
 102   @NonNls
 103   static final String BRACES_ID = "Clojure Braces";
 104   @NonNls
 105   static final String PAREN_ID = "Clojure Parentheses";
 106   @NonNls
 107   static final String LITERAL_ID = "Clojure Literal";
 108   @NonNls
 109   static final String CHAR_ID = "Clojure Character";
 110
 111   public static final TextAttributes ATOM_ATTRIB = HighlighterColors.TEXT.getDefaultAttributes().clone();
 112
 113
 114   // Registering TextAttributes
 115   static {
 116     TextAttributesKey.createTextAttributesKey(LINE_COMMENT_ID, SyntaxHighlighterColors.LINE_COMMENT.getDefaultAttributes());
 117     TextAttributesKey.createTextAttributesKey(KEY_ID, HighlightInfoType.STATIC_FIELD.getAttributesKey().getDefaultAttributes());
 118     TextAttributesKey.createTextAttributesKey(DEF_ID, SyntaxHighlighterColors.KEYWORD.getDefaultAttributes());
 119     TextAttributesKey.createTextAttributesKey(NUMBER_ID, SyntaxHighlighterColors.NUMBER.getDefaultAttributes());
 120     TextAttributesKey.createTextAttributesKey(STRING_ID, SyntaxHighlighterColors.STRING.getDefaultAttributes());
 121     TextAttributesKey.createTextAttributesKey(BRACES_ID, SyntaxHighlighterColors.BRACES.getDefaultAttributes());
 122     TextAttributesKey.createTextAttributesKey(PAREN_ID, SyntaxHighlighterColors.PARENTHS.getDefaultAttributes());
 123     TextAttributesKey.createTextAttributesKey(LITERAL_ID, SyntaxHighlighterColors.KEYWORD.getDefaultAttributes());
 124     TextAttributesKey.createTextAttributesKey(CHAR_ID, SyntaxHighlighterColors.STRING.getDefaultAttributes());
 125     TextAttributesKey.createTextAttributesKey(BAD_CHARACTER_ID, HighlighterColors.BAD_CHARACTER.getDefaultAttributes());
 126
 127     final Color deepBlue = SyntaxHighlighterColors.KEYWORD.getDefaultAttributes().getForegroundColor();
 128     ATOM_ATTRIB.setForegroundColor(deepBlue);
 129     TextAttributesKey.createTextAttributesKey(ATOM_ID, ATOM_ATTRIB);
 130   }
 131
 132   public static TextAttributesKey LINE_COMMENT = TextAttributesKey.createTextAttributesKey(LINE_COMMENT_ID);
 133   public static TextAttributesKey KEY = TextAttributesKey.createTextAttributesKey(KEY_ID);
 134   public static TextAttributesKey DEF = TextAttributesKey.createTextAttributesKey(DEF_ID);
 135   public static TextAttributesKey ATOM = TextAttributesKey.createTextAttributesKey(ATOM_ID);
 136   public static TextAttributesKey NUMBER = TextAttributesKey.createTextAttributesKey(NUMBER_ID);
 137   public static TextAttributesKey STRING = TextAttributesKey.createTextAttributesKey(STRING_ID);
 138   public static TextAttributesKey BRACES = TextAttributesKey.createTextAttributesKey(BRACES_ID);
 139   public static TextAttributesKey PARENTS = TextAttributesKey.createTextAttributesKey(PAREN_ID);
 140   public static TextAttributesKey LITERAL = TextAttributesKey.createTextAttributesKey(LITERAL_ID);
 141   public static TextAttributesKey CHAR = TextAttributesKey.createTextAttributesKey(CHAR_ID);
 142   public static TextAttributesKey BAD_CHARACTER = TextAttributesKey.createTextAttributesKey(BAD_CHARACTER_ID);
 143
 144
 145   static {
 146     fillMap(ATTRIBUTES, sLINE_COMMENTS, LINE_COMMENT);
 147     fillMap(ATTRIBUTES, sKEYS, KEY);
 148     fillMap(ATTRIBUTES, sATOMS, ATOM);
 149     fillMap(ATTRIBUTES, sNUMBERS, NUMBER);
 150     fillMap(ATTRIBUTES, sSTRINGS, STRING);
 151     fillMap(ATTRIBUTES, sBRACES, BRACES);
 152     fillMap(ATTRIBUTES, sPARENTS, PARENTS);
 153     fillMap(ATTRIBUTES, sLITERALS, LITERAL);
 154     fillMap(ATTRIBUTES, sCHARS, CHAR);
 155   }
 156
}

 */

