package Templates;
import java.awt.Color;
public class Colors {
	// TEMA VSCODE 0
	// TEMA GITHUB 1
	// TEMA ECPLIPSE 2
	private static int ide = 1;
	private static int language = 0;
	public static Color LIGHTCOLOR    = ColorsChoosen.getLightColor(ide);
	public static Color MEDIUMCOLOR1  = ColorsChoosen.getMediumColor1(ide);
	public static Color MEDIUMCOLOR2  = ColorsChoosen.getMediumColor2(ide);
	public static Color DARKCOLOR     = ColorsChoosen.getDarkColor(ide);
	public static Color COLOR1        = ColorsChoosen.getColor1(ide);
	public static Color COLOR2        = ColorsChoosen.getColor2(ide);
	public static Color COLOR3        = ColorsChoosen.getColor3(ide);
	public static Color RED           = ColorsChoosen.getRed(language);
	public static Color WHITE         = ColorsChoosen.getWhite(language);
	public static Color KEYWORD1      = ColorsChoosen.getKeyWord1(language);
	public static Color KEYWORD2      = ColorsChoosen.getKeyWord2(language);
	public static Color KEYWORD3      = ColorsChoosen.getKeyWord3(language);
	public static Color KEYWORD4      = ColorsChoosen.getKeyWord4(language);
	public static Color KEYWORD5      = ColorsChoosen.getKeyWord5(language);
	public static Color NUMBER        = ColorsChoosen.getNumber(language);
	public static Color STRING        = ColorsChoosen.getString(language);
	public static Color CHAR          = ColorsChoosen.getChar(language);
	public static Color FNC           = ColorsChoosen.getFNC(language);
	public static Color USEFNC        = ColorsChoosen.getUseFNC(language);
	public static Color COMMENT       = ColorsChoosen.getComment(language);
	public static Color VARIABLE      = ColorsChoosen.getVariable(language);
	public static Color USEVARIABLE   = ColorsChoosen.getUseVariable(language);
	public static Color PAR1          = ColorsChoosen.getPar1(language);
	public static Color PAR2          = ColorsChoosen.getPar2(language);
	public static Color PAR3          = ColorsChoosen.getPar3(language);
	public static Color[] bracketPair = {PAR1, PAR2, PAR3};
	public static Color ERROR         = ColorsChoosen.getError(language);
}