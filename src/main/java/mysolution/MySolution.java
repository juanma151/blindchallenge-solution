package mysolution;

import blch.framework.annotations.Solution;
import blch.framework.datamodel.utils.IntendedError;
import blch.framework.external.interfaces.SolutionData;


@Solution
public class MySolution implements SolutionData {

    @Override
    public Object execute(Object... args) {
        throw new RuntimeException("HOLA");
    }

}