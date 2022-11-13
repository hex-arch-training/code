package hexarch.dms;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import com.tngtech.archunit.library.Architectures;

@AnalyzeClasses(packages = "hexarch.dms")
class ArchitectureTest {

    @ArchTest
    static final ArchRule dependenciesOfComponentSharedAreRespected = ArchRuleDefinition
            .noClasses()
            .that()
            .resideInAnyPackage("..shared..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..preparation..", "..verification..", "..configuration..");

}
