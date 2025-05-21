package ar.com.grupoesfera.repartir.steps.miembros;

import ar.com.grupoesfera.repartir.steps.CucumberSteps;
import ar.com.grupoesfera.repartir.steps.Step;
import io.cucumber.java.es.Entonces;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.assertj.core.api.Assertions.assertThat;
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
    public void deberiaVisualizarDentroDelListadoElGrupoConElNombreIndicado(int idGrupo, String usuario) {
        var grupoTR = driver.findElements(By.cssSelector("app-grupos table tr"));
        assertThat(grupoTR).hasSizeGreaterThan(1);
        var campoTDs = grupoTR.get(1).findElements(By.tagName("td"));
        String miembrosTexto = campoTDs.get(3).getText();
        assertThat(miembrosTexto).contains(usuario);
    }

    /*@Step("completa con el monto de $ {string}")
    public void completaConElMontoDe(String monto) {

        var montoInput = driver.findElement(By.id("montoGastoNuevoInput"));
        montoInput.clear();
        montoInput.sendKeys(monto);
    }

    @Step("guarda el gasto")
    public void guardaElGasto() {

        var agregarGastoButton = driver.findElement(By.id("guardarGastoNuevoButton"));
        agregarGastoButton.click();
    }

    @Step("ve la confirmación {string}")
    public void veLaConfirmación(String mensaje) {

        var wait = new WebDriverWait(driver, 2);
        var mensajesToast = wait.until(visibilityOfElementLocated(By.id("mensajesToast")));
        assertThat(mensajesToast.getText())
                .contains("Éxito")
                .contains(mensaje);
    }

    @Step("ve el total del grupo #{int} actualizado a {string}")
    public void veElTotalDelGrupoActualidadoA$(int idGrupo, String monto) {

        var grupoTR = driver.findElements(By.cssSelector("app-grupos table tr"));

        var campoTDs = grupoTR.get(1).findElements(By.tagName("td"));
        assertThat(campoTDs.get(0).getText()).isNotEmpty();
        assertThat(campoTDs.get(2).getText()).isEqualTo(monto);
    }*/
}
