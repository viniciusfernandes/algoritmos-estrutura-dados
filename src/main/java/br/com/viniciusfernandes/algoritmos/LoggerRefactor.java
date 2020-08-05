package br.com.viniciusfernandes.algoritmos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LoggerRefactor {
	private static final Set<String> avoidFiles = new HashSet<>();
	private static final List<File> processedFiles = new ArrayList<>(20);
	private static final String rootDir = "/home/vinicius/Ambiente-Trabalho/Projetos/Viavarejo/vv-viamais-condicao-pagamento-api";

	static {
		avoidFiles.add("GatewayService.java");
		avoidFiles.add("EnviarSimulacaoService.java");
		avoidFiles.add("AbstractService.java");
	}

	private static void gerarLogFalha(StringBuilder stringFile, String className) {
		final int ind = stringFile.toString().lastIndexOf(";");
		final StringBuilder log = new StringBuilder();
		log.append("\n\t.doOnError(ex-> logError(\"").append(gerarMensagemFalha(className)).append("\", requestContext.getDto(), ex));");

		stringFile.replace(ind, ind + 1, log.toString());
	}

	private static String gerarMensagemFalha(String className) {
		final char[] name = className.toCharArray();
		final StringBuilder mensagem = new StringBuilder("Falha na operacao");
		for (int i = 0; i < name.length; i++) {
			if (Character.isUpperCase(name[i])) {
				mensagem.append(" ").append(Character.toLowerCase(name[i]));
			}
			else {
				mensagem.append(name[i]);
			}
		}
		return mensagem.toString();
	}

	private static boolean isJavaService(File file) {
		final String name = file.getName();
		return name.endsWith("Service.java") && !avoidFiles.contains(name);
	}

	public static void main(String[] args) throws Exception {
		scanDir(new File(rootDir));
		print();
	}

	private static void print() {
		System.out.println("----- ARQUIVOS PROCESSADOS -----");
		for (final File file : processedFiles) {
			System.out.println(file.getAbsolutePath());
		}
		System.out.println("----- FIM -----");

	}

	private static String readFile(File javaFile) throws Exception {
		final BufferedReader bf = new BufferedReader(new FileReader(javaFile));
		String line = null;
		final StringBuilder stringFile = new StringBuilder();
		String className = null;
		boolean foundClassName = false;
		while ((line = bf.readLine()) != null) {
			if (line.contains("package")) {
				line = line + "\n" + "import br.com.viavarejo.viamais.simulador.app.core.AbstractService;\n";
			}
			if (!foundClassName && line.contains("public class ")) {
				className = line.split("\\s+")[2];
				line = line.replace(className, className + " extends AbstractService ");
				foundClassName = true;
			}
			stringFile.append(line).append("\n");
		}
		bf.close();
		gerarLogFalha(stringFile, className);
		return stringFile.toString();
	}

	private static void scanDir(File file) throws Exception {
		if (file.isDirectory()) {
			for (final String fileName : file.list()) {
				scanDir(new File(file.getAbsolutePath() + "/" + fileName));
			}
		}
		else if (isJavaService(file)) {
			processedFiles.add(file);
			final String fileContent = readFile(file);
			writeFile(file, fileContent);
		}
	}

	private static void writeFile(File javaFile, String fileContent) throws Exception {
		final BufferedWriter writer = new BufferedWriter(new FileWriter(javaFile));
		writer.write(fileContent);
		writer.close();

	}
}
