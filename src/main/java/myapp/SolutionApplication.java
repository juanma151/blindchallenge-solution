package myapp;


import blch.framework.BlindChallengeApplication;
import blch.framework.annotations.CurrPkg;
import blch.framework.annotations.Run;


@Run(packages={"mysolution_ok","mychallenges"})
@CurrPkg
public class SolutionApplication {

    public static void main(String... args) {
        BlindChallengeApplication.run(SolutionApplication.class, args);
    }
    
}