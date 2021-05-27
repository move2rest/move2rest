package rnd.tool.code.parse.impl.modern;

import rnd.tool.code.gen.impl.modern.SpringRepoGenerator;
import rnd.tool.code.model.ClassInfoImpl;
import rnd.tool.code.model.ElementInfoImpl;

public class SpringSingletonParser extends SingletonParser {

	@Override
	protected void processField(ClassInfoImpl classInfo, ElementInfoImpl elem) {

		elem.setInit("");

		// Replace DAO fields with Repository fields
		// TODO : Move to child parser

		String type = elem.getType();
		if (type.endsWith(DaoParser.CLASS_SUFFIX)) {
			type = type.substring(0, type.length() - DaoParser.CLASS_SUFFIX.length());
			type = type + SpringRepoGenerator.CLASS_SUFFIX;
			elem.setType(type);
		}

		String name = elem.getName();
		if (name.endsWith(DaoParser.CLASS_SUFFIX)) {
			name = name.substring(0, name.length() - DaoParser.CLASS_SUFFIX.length());
			name = name + SpringRepoGenerator.CLASS_SUFFIX;
			elem.setName(name);
		}

		super.processField(classInfo, elem);
	}

}
