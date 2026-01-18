package app;

import blch.framework.BlindChallengeApplication;
import blch.framework.annotations.CurrPkg;
import blch.framework.annotations.Run;

@Run(packages = {"solution", "challenges"})
@CurrPkg
public class BlindChallengeApp {

  public static void main(String... args) {
    BlindChallengeApplication.run(BlindChallengeApp.class, args);
  }
}
