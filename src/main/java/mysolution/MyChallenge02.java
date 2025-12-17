
package mysolution;

import blch.framework.annotations.Challenge;
import blch.framework.datamodel.A;
import blch.framework.datamodel.ErrorValue;
import blch.framework.datamodel.WireValue;
import blch.framework.datamodel.V;
import blch.framework.external.interfaces.ChallengeData;

import java.util.ArrayList;
import java.util.List;


@Challenge(id="0002")
public class MyChallenge02 implements ChallengeData{

    @Override
    public List<WireValue> getArgs() {
        List<WireValue> args = new ArrayList<>();
        args.add(new V("COMMAND"));
        args.add(new V("HOLA"));
        args.add(new V(45));
        
        return args;
    }

    @Override
    public WireValue getExpected() {
        return new V(0);
    }
}
