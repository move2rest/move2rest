package rnd.tool.code.mig.impl.modern;

import rnd.tool.code.gen.impl.modern.SpringServiceGenerator;
import rnd.tool.code.mig.core.AbstractMigrator;
import rnd.tool.code.model.ClassInfo;
import rnd.tool.code.parse.impl.modern.SpringSingletonParser;

public class Singleton2SpringService extends AbstractMigrator<ClassInfo> {

	public Singleton2SpringService() {
		this.classPar = new SpringSingletonParser();
		this.classGen = new SpringServiceGenerator();
	}

}
