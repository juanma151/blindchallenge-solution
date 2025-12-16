package mysolution;

import blch.framework.BlindChallengeApplication;
import blch.framework.annotations.CurrPkg;
import blch.framework.annotations.Run;

@Run
@CurrPkg
public class SolutionApplication {

    public static void main(String... args) {
        BlindChallengeApplication.run(SolutionApplication.class, args);
    }
    
}