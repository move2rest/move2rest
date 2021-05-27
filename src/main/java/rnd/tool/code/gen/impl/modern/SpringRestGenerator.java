package rnd.tool.code.gen.impl.modern;

import java.io.Writer;
import java.util.List;
import java.util.Set;

import rnd.tool.code.gen.core.impl.ClassInfoRestGenerator;
import rnd.tool.code.model.ClassInfo;
import rnd.tool.code.model.ElementInfo;
import rnd.tool.code.model.MethodInfo;
import rnd.tool.code.model.MethodInfoImpl;
import rnd.tool.code.parse.core.utils.HttpMethodMapper;

public class SpringRestGenerator extends ClassInfoRestGenerator {

	public static final String CLASS_SUFFIX = "Controller";

	@Override
	public String getClassSuffix() {
		return CLASS_SUFFIX;
	}

	@Override
	protected void writeImports(Writer writer, ClassInfo classInfo) {
		Set<String> imports = classInfo.getImports();
		imports.add("import org.springframework.web.bind.annotation.RestController;");
		imports.add("import org.springframework.web.bind.annotation.GetMapping;");
		imports.add("import org.springframework.web.bind.annotation.PutMapping;");
		imports.add("import org.springframework.web.bind.annotation.DeleteMapping;");
		imports.add("import org.springframework.web.bind.annotation.PostMapping;");
		imports.add("import org.springframework.web.bind.annotation.RequestMapping;");
		imports.add("import org.springframework.web.bind.annotation.PathVariable;");
		imports.add("import org.springframework.beans.factory.annotation.Autowired;");
		super.writeImports(writer, classInfo);
	}

	@Override
	protected void writeClass(Writer writer, ClassInfo classInfo) {
		String pathName = classInfo.getName().toLowerCase();
		writeln(writer, "@RestController");
		writeln(writer, "@RequestMapping(\"/" + pathName + "/\")");
		super.writeClass(writer, classInfo);
	}

	@Override
	protected void writeField(Writer writer, ClassInfo classInfo, ElementInfo field) {
		writeln(writer, "\t@Autowired");
		super.writeField(writer, classInfo, field);
	}

	@Override
	protected void writeMethod(Writer writer, ClassInfo classInfo, MethodInfo methodInfo) {

		// Add HTTP method
		((MethodInfoImpl) methodInfo).setHttpMethod(HttpMethodMapper.map(methodInfo.getName()));
		String httpMethod = methodInfo.getHttpMethod().toString();
		if (httpMethod != null) {

			write(writer, "\t@" + httpMethod);
			write(writer, "Mapping(\"");

			// Add path param
			if (hasParam(methodInfo)) {
				if (methodInfo.getParams().size() > 0) {
					ElementInfo param = methodInfo.getParams().get(0);
					write(writer, "/{" + param.getName() + "}");
				}
			}

			writeln(writer, "\")");
		}

		super.writeMethod(writer, classInfo, methodInfo);
	}

	@Override
	protected void writeParams(Writer writer, MethodInfo methodInfo) {
		List<ElementInfo> params = methodInfo.getParams();
		for (int i = 0; i < params.size(); i++) {
			ElementInfo pinfo = params.get(i);
			String type = pinfo.getType();
			String name = pinfo.getName();
			write(writer, i == 0 ? "" : ", ");
			if (hasParam(methodInfo)) {
				write(writer, "@PathVariable(\"" + name + "\") ");
			} else {
				write(writer, "@RequestParam(\"" + name + "\") ");

			}
			write(writer, type + " " + name);
		}
	}

}