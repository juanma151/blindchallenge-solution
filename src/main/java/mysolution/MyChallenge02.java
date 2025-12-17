package mysolution;

import blch.framework.annotations.Challenge;
import blch.framework.external.ChallengeBase;

import static blch.framework.dsl.WireDsl.*;


@Challenge(id = "0002")
public class MyChallenge02 extends ChallengeBase {

    @Override
    public void challenge() {
        args(
                v("COMMAND"),
                v("HOLA"),
                v(45)
        );
        expected(v(0));
    }
}
