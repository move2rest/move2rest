package rnd.tool.code.parse.impl.modern;

import java.util.Map.Entry;
import java.util.Set;

import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.MethodDeclaration;

import rnd.tool.code.model.ClassInfoImpl;
import rnd.tool.code.model.ElementInfoImpl;
import rnd.tool.code.model.MethodInfo;
import rnd.tool.code.model.MethodInfo.HttpMethod;
import rnd.tool.code.parse.core.impl.ClassInfoClassParser;
import rnd.tool.code.parse.core.utils.HttpMethodMapper;

public class ServletParser extends ClassInfoClassParser {

	public static final String CLASS_SUFFIX = "Servlet";

	@Override
	public String getClassSuffix() {
		return CLASS_SUFFIX;
	}

	@Override
	protected void parseImports(ClassInfoImpl classInfo) {
		super.parseImports(classInfo);
		Set<String> imports = classInfo.getImports();
		imports.remove("import javax.servlet.annotation.WebServlet;");
		imports.remove("import javax.servlet.annotation.WebInitParam;");
		imports.remove("import javax.servlet.http.HttpServlet;");
		imports.remove("import javax.servlet.http.HttpServletRequest;");
		imports.remove("import javax.servlet.http.HttpServletResponse;");
		imports.remove("import javax.servlet.ServletException;");
		imports.remove("import java.io.IOException;");
	}

	@Override
	protected void processField(ClassInfoImpl classInfo, ElementInfoImpl elem) {
		elem.setInit("");
		super.processField(classInfo, elem);
	}

	@Override
	protected void parseMethods(ClassInfoImpl classInfo) {
		super.parseMethods(classInfo);
		HttpMethodMapper.map(classInfo);
	}

	@Override
	protected MethodInfo processMethod(ClassInfoImpl classInfo, Entry<String, MethodDeclaration> method) {

		String className = classInfo.getName();
		String name = method.getKey();

		MethodDeclaration methodDec = sourceMethodMap.get(name);
		methodDec.setParameters(new NodeList<>());

		if ("doGet".equals(name)) {
			return addMethod(classInfo, className, methodDec, "get" + className, HttpMethod.Get);
		}
		if ("doPut".equals(name)) {
			return addMethod(classInfo, className, methodDec, "put" + className, HttpMethod.Put);
		}
		if ("doPost".equals(name)) {
			return addMethod(classInfo, className, methodDec, "post" + className, HttpMethod.Post);
		}
		if ("doDelete".equals(name)) {
			return addMethod(classInfo, className, methodDec, "delete" + className, HttpMethod.Delete);
		} else {
			return super.processMethod(classInfo, method);
		}
	}

}
