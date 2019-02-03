package ru.cherkasov.w2d2;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class JavaWorkCompile {

    /**
     * Метод чтения данных с клавиатуры
     *
     * @return строка с телом метода
     */
    public String getBodyMethod() {
        StringBuilder bodyMethod = new StringBuilder();
        try (Scanner scanner = new Scanner(System.in)) {
            String temp;
            while (!(temp = scanner.nextLine()).isEmpty()) {
                bodyMethod.append(temp);
            }
        }
        return bodyMethod.toString();
    }

    /**
     * Компиляция метода с введеной реализацией
     * метода doWork() с клавиатуры
     *
     * @param bodyMethod тело метода
     */
    public void compileWork(String bodyMethod) {
        StringBuilder sb = new StringBuilder();
        String className = "WorkerImpl";
        sb.append("package ru.cherkasov.w2d2; ")
                .append("public class WorkerImpl implements Worker { ")
                .append("@Override ")
                .append("public void doWork() { ")
                .append(bodyMethod).append(" }}");

        try {
            String filename = "./src/ru/cherkasov/w2d2/" + className + ".java";
            //Сохраняем файл с исходником
            Files.write(Paths.get(filename), sb.toString().getBytes());
            //запускаем компилятор javac
            JavaCompiler javac = ToolProvider.getSystemJavaCompiler();
            //Собираем параметры
            String[] javacOpts = {filename};
            javac.run(null, null, null, javacOpts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
