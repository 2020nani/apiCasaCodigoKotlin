package br.com.zup.validacoescustomizadas

import br.com.zup.cadastraautores.AutorRepository
import io.micronaut.core.annotation.AnnotationValue
import io.micronaut.validation.validator.constraints.ConstraintValidator
import io.micronaut.validation.validator.constraints.ConstraintValidatorContext
import javax.inject.Singleton
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass


@MustBeDocumented
@Target(AnnotationTarget.CONSTRUCTOR, AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [EmailUnicoValidator::class])
annotation class EmailUnico(
    val message: String = "Ja existe objeto cadastrado com este email",
    val groups: Array<KClass<Any>> = [],
    val payload: Array<KClass<Payload>> = []
)

@Singleton
class EmailUnicoValidator(
    val autorRepository: AutorRepository
) : ConstraintValidator<EmailUnico, String> {

    override fun isValid(
        value: String?,
        annotationMetadata: AnnotationValue<EmailUnico>,
        context: ConstraintValidatorContext
    ): Boolean {
        if(value == null) return true

        if(autorRepository.existsByEmail(value)){
            return false
        }
        return true
    }

}

