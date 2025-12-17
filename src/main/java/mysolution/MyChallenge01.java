package mysolution;

import blch.framework.annotations.Challenge;
import blch.framework.datamodel.ErrorValue;
import blch.framework.datamodel.WireValue;
import blch.framework.datamodel.V;
import blch.framework.external.ChallengeBase;

import static blch.framework.dsl.WireDsl.*;

import java.util.ArrayList;
import java.util.List;

@Challenge(id = "0001", name = "AAA")
public class MyChallenge01 extends ChallengeBase {

    @Override
    public void challenge() {
        args(
                v("COMMAND"),
                v(33)
        );
        expected(
                error("HOLA")
        );     
    }
}
