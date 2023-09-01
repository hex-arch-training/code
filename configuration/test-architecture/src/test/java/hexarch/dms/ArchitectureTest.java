package hexarch.dms;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import com.tngtech.archunit.library.Architectures;

@AnalyzeClasses(packages = "hexarch.dms")
class ArchitectureTest {

    @ArchTest
    static final ArchRule hexArchitectureIsRespected = Architectures
            .onionArchitecture()
            .domainModels("..domain..")
            .domainServices("..domain..")
            .applicationServices("..application..")
            .adapter("web", "..adapter.in.web..")
            .adapter("db", "..adapter.out.db..")
            .adapter("out event", "..adapter.out.event..")
            .adapter("in event", "..adapter.in.event..")
            .adapter("out preparation", "..adapter.out.preparation..");

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

    @ArchTest
    static final ArchRule dependenciesBetweenPreparationAndVerificationAreRespected = ArchRuleDefinition
            .noClasses()
            .that()
            .resideInAnyPackage("..preparation.adapter..", "..preparation.application..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..verification..");
}
