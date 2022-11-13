package hexarch.dms;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;

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

    @ArchTest
    static final ArchRule dependenciesOfComponentPreparationAreRespected = ArchRuleDefinition
            .noClasses()
            .that()
            .resideInAnyPackage("..preparation..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..configuration..");

    @ArchTest
    static final ArchRule dependenciesOfComponentVerificationAreRespected = ArchRuleDefinition
            .noClasses()
            .that()
            .resideInAnyPackage("..verification..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..configuration..");

}
