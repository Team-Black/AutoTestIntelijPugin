package Action;

import Logic.DescExtractor;
import Logic.TypeBuild;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vcs.VcsBundle;
import com.intellij.openapi.vfs.VirtualFile;
import javassist.NotFoundException;

import java.util.ArrayList;
import java.util.Objects;

import static com.intellij.openapi.actionSystem.CommonDataKeys.VIRTUAL_FILE;

public class StartTestAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        // 1.  Получаем абсолютный путь до нашего проекта
        Messages.showMessageDialog(Objects.requireNonNull(e.getProject()).getBasePath(), "Settings",
                Messages.getInformationIcon());
//        VirtualFile virtualFile = e.getData(VIRTUAL_FILE);
//        String path = virtualFile.getCanonicalPath();
//        String replacePath = path.replace("src", "@");
//        String outPath = replacePath.substring(0, replacePath.indexOf("@"));
//        String javaFileName = virtualFile.getName().replace(".java", "");
//
//        CommandProcessor.getInstance().executeCommand(e.getProject(), () -> {
//            DescExtractor descExtractor;
//            ArrayList<String> list;
//            try {
//                descExtractor = new DescExtractor(outPath, TypeBuild.MAVEN);
//                list = descExtractor.getAllSplittedNameAndDec(javaFileName);
//                Messages.showMessageDialog("Ожидайте выполнения", "Settings",
//                        Messages.getInformationIcon());
//            } catch (NotFoundException notFoundException) {
//                Messages.showMessageDialog("The corresponding .class file does not exist. Compile the project and try again.", "Error",
//                        Messages.getInformationIcon());
//            }
//        },  VcsBundle.message("command.name.open.error.message.view"), null);

        // 2.  Сохраняем его в persistence
        // 3.  Создает папку Generated если её ещё нет
        // 4.  Добавляем относительный путь до сгенерированной папки + название исходно класса + приставка test + расширение java
        // 5.  Сохраняем в persistence
        // 6.  Получаем относительный путь до файла для которого генерируем тесты
        // 7.  Сохраняем в persistence
        // 8.  Полвеоиьб тип проекта ( Maven/ Gragdle / Simple app)
        // 9.  Получаем названия методов и его дескрипторы
        //10.  Сохраняем в persistence
        //11.  Запускаем выполнение JBSE
        //12.  Начинаем отслеживать не появился ли в этой сгенерированной папке наш файл, если появился завершаем
        //13.  Выводим сообщение об успехе

    }
}
