package rnd.tool.code.mig.impl.modern;

import rnd.tool.code.gen.impl.modern.SpringRepoGenerator;
import rnd.tool.code.mig.core.AbstractMigrator;
import rnd.tool.code.model.ClassInfo;
import rnd.tool.code.parse.impl.modern.DaoParser;

public class Dao2Repo extends AbstractMigrator<ClassInfo> {

	public Dao2Repo() {
		this.classPar = new DaoParser();
		this.classGen = new SpringRepoGenerator();
	}

}
