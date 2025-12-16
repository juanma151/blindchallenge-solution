package mysolution;

import blch.framework.annotations.Solution;
import blch.framework.solution.ISolution;


@Solution(id="JMA-TEST", name="Solución de prueba de Juanma")
public class MySolution implements ISolution {

    @Override
    public Object execute(Object... args) {
        return 0;
    }

}