package rnd.tool.code.parse.impl.modern;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.github.javaparser.ast.visitor.ModifierVisitor;
import com.github.javaparser.ast.visitor.Visitable;

import rnd.tool.code.model.ClassInfoImpl;
import rnd.tool.code.model.ElementInfo;
import rnd.tool.code.model.ElementInfoImpl;
import rnd.tool.code.model.MethodInfo;
import rnd.tool.code.model.MethodInfoImpl;
import rnd.tool.code.parse.core.impl.ClassInfoClassParser;

public class RequestParamParser extends ClassInfoClassParser {

	public RequestParamParser() {
	}

	@Override
	protected MethodInfo processMethod(ClassInfoImpl classInfo, Entry<String, MethodDeclaration> method) {
		MethodInfo methodInfo = super.processMethod(classInfo, method);
		extractParams(method.getValue(), (MethodInfoImpl) methodInfo);
		return methodInfo;
	}

	private void extractParams(MethodDeclaration methodDec, MethodInfoImpl methodInfo) {

		List<ElementInfo> params = new ArrayList<>();

		methodDec.accept(new ModifierVisitor<Object>() {
			public Visitable visit(MethodCallExpr methCallExpr, Object arg) {

				// Replace call to method request.getParameter() with new request parameter
				if (methCallExpr.getNameAsString().equals("getParameter")) {
					StringLiteralExpr ste = (StringLiteralExpr) methCallExpr.getArguments().get(0);
					params.add(new ElementInfoImpl("String", ste.getValue()));
					return new NameExpr(ste.getValue());
				}
				return super.visit(methCallExpr, arg);
			}
		}, null);

		methodInfo.setParams(params);
		methodInfo.setDefinition(methodDec.getBody().get().toString().replace("\r\n", "\r\n\t"));
	}

}
