import java.util.function.*;

public class Functional {

    //functional method of calculating 2x
    int apply (int x) {
            return 2*x;
    }

    //gets the square root of a given number
    Double absApply (Function<Integer,Double> f, int x) {
        return f.apply(x < 0 ? -x : x);
    }

    Function<Integer,Double> absSqrt = x -> Math.sqrt(x);

}
