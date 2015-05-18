package baminsurances.util;

import java.text.Collator;
import java.text.ParseException;
import java.text.RuleBasedCollator;
import java.util.Comparator;

/**
 * A comparator that properly compares strings containing Norwegian characters.
 * <p>
 * The rules for the collator are taken from<a href=
 * "http://docs.oracle.com/javase/7/docs/api/java/text/RuleBasedCollator.html">
 * Oracle's documentation of RuleBasedCollator</a>.
 * @author Martin Jackson
 */
public class NorwegianComparator implements Comparator<String> {
    private Collator coll;
    private String norwegian =
            "< a, A < b, B < c, C < d, D < e, E < f, F < g, G < h, H < i, I" +
            "< j, J < k, K < l, L < m, M < n, N < o, O < p, P < q, Q < r, R" +
            "< s, S < t, T < u, U < v, V < w, W < x, X < y, Y < z, Z" +
            "< \u00E6, \u00C6" +     // æ, Æ
            "< \u00F8, \u00D8" +     // ø, Ø
            "< \u00E5 = a\u030A," +  // å
            "  \u00C5 = A\u030A;" +  // Å
            "  aa, AA";
    
    public NorwegianComparator() {
        try {
            coll = new RuleBasedCollator(norwegian);
        } catch (ParseException pe) {
            pe.printStackTrace();
        }
    }
    
    @Override
    public int compare(String o1, String o2) {
        return coll.compare(o1, o2);
    }
}
