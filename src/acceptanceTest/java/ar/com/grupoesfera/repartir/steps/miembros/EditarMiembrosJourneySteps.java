package ar.com.grupoesfera.repartir.steps.miembros;

import ar.com.grupoesfera.repartir.steps.CucumberSteps;
import ar.com.grupoesfera.repartir.steps.Step;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class EditarMiembrosJourneySteps extends CucumberSteps {

    @Step("existe el grupo #{int} {string}")
    public void existeElGrupo(int idGrupo, String nombre) {

        baseDeDatos.tieneElGrupo(idGrupo, nombre);
    }

    @Step("el usuario selecciona editar miembros al grupo #{int}")
    public void elUsuarioSeleccionaEditarMiembrosAlGrupo(int idGrupo) {

        var wait = new WebDriverWait(driver, 15);
        var agregarGastoButton = wait.until(visibilityOfElementLocated(By.id("editarMiembrosGruposButton-" + idGrupo)));
        agregarGastoButton.click();
    }

    @Step("agrega al usuario {string}")
    public void agregaAlUsuario(String usuario) {
        System.out.println("deaa-agregaAlUsuario");
        var miembrosInput = driver.findElement(By.id("miembrosGrupoNuevoInput"));
        miembrosInput.sendKeys(usuario);
        miembrosInput.sendKeys(Keys.ENTER);

        driver.findElement(By.id("guardarMiembroNuevoButton")).click();

        var wait = new WebDriverWait(driver, 5);
        wait.until(visibilityOfElementLocated(By.id("mensajesToast")));
    }

    @Step("deberia visualizar dentro del listado al grupo #{int} con el miembro {string}")
    public void deberiaVisualizarDentroDelListadoAlGrupoConElMiembroIndicado(int idGrupo, String usuario) {
        var grupoTR = driver.findElements(By.cssSelector("app-grupos table tr"));
        assertThat(grupoTR).hasSizeGreaterThan(1);
        var campoTDs = grupoTR.get(1).findElements(By.tagName("td"));
        String miembrosTexto = campoTDs.get(3).getText();
        assertThat(miembrosTexto).contains(usuario);
    }

    @Y("debería ser informado que no puede realizar esa acción")
    public void deberiaSerInformadoQueNoPuedeRealizarEsaAccion() {

        var wait = new WebDriverWait(driver, 2);
        var mensajesToast = wait.withMessage("Mostro Toast")
                .until(visibilityOfElementLocated(By.id("mensajesToast")));
        wait.withMessage("Título del Toast es 'Error'")
                .until(textToBePresentInElement(mensajesToast, "Error"));
        assertThat(mensajesToast.getText())
                .as("Descripción del Toast")
                .contains("No se puede guardar");
    }

    @Step("deberia visualizar dentro del listado al grupo #{int} con el miembro {string} una sola vez")
    public void deberiaVisualizarDentroDelListadoAlGrupoConElMiembroIndicadoUnaSolaVez(int idGrupo, String usuario) {
        var grupoTR = driver.findElements(By.cssSelector("app-grupos table tr"));
        assertThat(grupoTR).hasSizeGreaterThan(1);
        var campoTDs = grupoTR.get(1).findElements(By.tagName("td"));
        String miembrosTexto = campoTDs.get(3).getText();
        System.out.println("miembrosTexto: " + miembrosTexto);
        assertThat(miembrosTexto).contains(usuario);
        int ocurrencias = miembrosTexto.split("raul", -1).length - 1;
        assertThat(ocurrencias).isEqualTo(1);
    }
}
