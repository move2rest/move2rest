package rnd.tool.code.gen.impl.modern;

import java.io.Writer;
import java.util.List;
import java.util.Set;

import rnd.tool.code.gen.core.impl.ClassInfoRestGenerator;
import rnd.tool.code.model.ClassInfo;
import rnd.tool.code.model.ElementInfo;
import rnd.tool.code.model.MethodInfo;
import rnd.tool.code.model.MethodInfo.HttpMethod;
import rnd.tool.code.model.MethodInfoImpl;
import rnd.tool.code.parse.core.utils.HttpMethodMapper;

public class JeeRestGenerator extends ClassInfoRestGenerator {

	public static final String CLASS_SUFFIX = "Resource";

	@Override
	public String getClassSuffix() {
		return CLASS_SUFFIX;
	}

	@Override
	protected void writeImports(Writer writer, ClassInfo classInfo) {
		Set<String> imports = classInfo.getImports();
		imports.add("import javax.inject.Inject;");
		imports.add("import javax.ws.rs.Consumes;");
		imports.add("import javax.ws.rs.Produces;");
		imports.add("import javax.ws.rs.GET;");
		imports.add("import javax.ws.rs.PUT;");
		imports.add("import javax.ws.rs.POST;");
		imports.add("import javax.ws.rs.DELETE;");
		imports.add("import javax.ws.rs.Path;");
		imports.add("import javax.ws.rs.PathParam;");
		imports.add("import javax.ws.rs.FormParam;");
		imports.add("import javax.ws.rs.core.MediaType;");
		super.writeImports(writer, classInfo);
	}

	@Override
	protected void writeClass(Writer writer, ClassInfo classInfo) {
		String pathName = classInfo.getName().toLowerCase();
		writeln(writer, "@Path(\"/" + pathName + "/\")");
		super.writeClass(writer, classInfo);
	}
	
	@Override
	protected void writeField(Writer writer, ClassInfo classInfo, ElementInfo field) {
		writeln(writer, "\t@Inject");
		super.writeField(writer, classInfo, field);
	}

	@Override
	protected void writeMethod(Writer writer, ClassInfo classInfo, MethodInfo methodInfo) {

		// Add HTTP Method
		((MethodInfoImpl) methodInfo).setHttpMethod(HttpMethodMapper.map(methodInfo.getName()));
		HttpMethod httpMethod = methodInfo.getHttpMethod();
		if (httpMethod != null) {
			writeln(writer, "\t@" + httpMethod.toString().toUpperCase());
		}

		// Add path param
		if (hasParam(methodInfo)) {
			StringBuilder path = new StringBuilder();
			write(writer, "\t@Path(\"" + path.toString().toLowerCase());
			if (methodInfo.getParams().size() > 0) {
				ElementInfo param = methodInfo.getParams().get(0);
				write(writer, "/{" + param.getName() + "}");
			}
			writeln(writer, "\")");
		}

		// Add @Consumes for PUT,POST
		if (hasBody(methodInfo)) {
			writeln(writer, "\t@Consumes(MediaType.APPLICATION_JSON)");
		}

		// Add @Produces for all methods
		writeln(writer, "\t@Produces(MediaType.APPLICATION_JSON)");
		super.writeMethod(writer, classInfo, methodInfo);
	}

	@Override
	protected void writeParams(Writer writer, MethodInfo methodInfo) {
		List<ElementInfo> params = methodInfo.getParams();
		for (int i = 0; i < params.size(); i++) {
			ElementInfo param = params.get(i);
			String type = param.getType();
			String name = param.getName();
			write(writer, i == 0 ? "" : ", ");
			if (hasParam(methodInfo)) {
				write(writer, "@PathParam(\"" + name + "\") ");
			} else {
				write(writer, "@FormParam(\"" + name + "\") ");
			}
			write(writer, type + " " + name);
		}
	}

}