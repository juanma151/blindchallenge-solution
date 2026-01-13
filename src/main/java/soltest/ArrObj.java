package soltest;

import java.util.ArrayList;
import java.util.List;

public class ArrObj extends ArrayList<Object> {
    private static final long serialVersionUID = -4740730975324856629L;

    private ArrObj() {
    }

    public static ArrObj arr() {
	return new ArrObj();
    }

    public static ArrObj arr(Object... args) {
	return arr(List.of(args));
    }

    public static ArrObj arr(List<?> args) {
	ArrObj arr = new ArrObj();
	return arr.addl(args);
    }

    public ArrObj addl(Object... args) {
	return addl(List.of(args));
    }

    public ArrObj addl(List<?> args) {
	this.addAll(args);
	return this;
    }

    public ArrObj addf(Object... args) {
	return addf(List.of(args));
    }

    public ArrObj addf(List<?> args) {
	if (this.size() == 0) {
	    this.addAll(args);
	} else {
	    this.addAll(0, args);
	}
	return this;
    }

    public ArrObj addi(int index, Object... args) {
	return addi(index, List.of(args));
    }

    public ArrObj addi(int index, List<?> args) {
	if (index < 0) {
	    this.addAll(0, args);
	} else if (index >= this.size()) {
	    this.addAll(args);
	} else {
	    this.addAll(index, args);
	}

	return this;
    }

    public ArrObj set(int index, Object obj) {
	if (index<0) {
	    this.addFirst(obj);
	} 
	else if ( index >= this.size()) {
	    this.add(obj);
	}
	else {
	    this.add(index, obj);
	}
	return this;
    }

    public ArrObj rf() {
	return rf(1);
    }

    public ArrObj rf(int len) {
	len = Math.min(len, this.size());
	this.removeRange(0, len);
	return this;
    }

    public ArrObj rl() {
	return rl(1);
    }

    public ArrObj rl(int len) {
	len = this.size() - Math.min(len, this.size());
	this.removeRange(len, this.size());
	return this;
    }

    public ArrObj clr() {
	this.clear();
	return this;
    }

    public int len() {
	return this.size();
    }

    public Object f() {
	return this.getFirst();
    }

    public Object l() {
	return this.getLast();
    }

    public Object i(int index) {
	if (index < 0 || index >= this.size()) {
	    return null;
	} else {
	    return this.get(index);
	}
    }
}
