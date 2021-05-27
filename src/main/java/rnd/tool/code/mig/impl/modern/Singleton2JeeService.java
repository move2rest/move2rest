package rnd.tool.code.mig.impl.modern;

import rnd.tool.code.gen.impl.modern.JeeServiceGenerator;
import rnd.tool.code.mig.core.AbstractMigrator;
import rnd.tool.code.model.ClassInfo;
import rnd.tool.code.parse.impl.modern.SingletonParser;

public class Singleton2JeeService extends AbstractMigrator<ClassInfo> {

	public Singleton2JeeService() {
		this.classPar = new SingletonParser();
		this.classGen = new JeeServiceGenerator();
	}

}
