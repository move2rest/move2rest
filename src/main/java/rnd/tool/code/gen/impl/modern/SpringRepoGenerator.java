package rnd.tool.code.gen.impl.modern;

import java.io.Writer;

import rnd.tool.code.gen.core.impl.ClassInfoClassGenerator;
import rnd.tool.code.model.ClassInfo;
import rnd.tool.code.model.ClassInfoImpl;

public class SpringRepoGenerator extends ClassInfoClassGenerator {

	public static final String CLASS_SUFFIX = "Repository";

	@Override
	public String getClassSuffix() {
		return CLASS_SUFFIX;
	}

	@Override
	protected void writeImports(Writer writer, ClassInfo classInfo) {
		classInfo.getImports().add("import org.springframework.stereotype.Repository;");
		classInfo.getImports().add("import org.springframework.data.repository.CrudRepository;");
		super.writeImports(writer, classInfo);
	}

	@Override
	protected void writeClass(Writer writer, ClassInfo classInfo) {
		((ClassInfoImpl) classInfo).addExtension(new ClassInfoImpl("CrudRepository"));
		writeln(writer, "@Repository");
		super.writeClass(writer, classInfo);
	}

}