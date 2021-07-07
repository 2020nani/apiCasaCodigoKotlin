package br.com.zup.cadastraautores.configxml

import io.micronaut.context.annotation.BootstrapContextCompatible
import br.com.zup.cadastraautores.configxml.XhtmlMediaTypeCodec
import com.fasterxml.jackson.databind.ObjectMapper
import io.micronaut.runtime.ApplicationConfiguration
import io.micronaut.http.codec.CodecConfiguration
import io.micronaut.jackson.codec.JacksonMediaTypeCodec
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.SerializationFeature
import io.micronaut.core.annotation.Nullable
import io.micronaut.http.MediaType
import io.micronaut.jackson.codec.JacksonFeatures
import io.micronaut.xml.jackson.codec.XmlMediaTypeCodec
import javax.inject.Named
import javax.inject.Singleton

@Named("xml")
@Singleton
@BootstrapContextCompatible
class XhtmlMediaTypeCodec
/**
 * @param xmlMapper                Object mapper for xml. If null, retrieved from beanContext
 * @param applicationConfiguration The common application configurations
 * @param codecConfiguration       The configuration for the codec
 */
    (
    @Named(CONFIGURATION_QUALIFIER) xmlMapper: ObjectMapper?,
    applicationConfiguration: ApplicationConfiguration?,
    @Named(CONFIGURATION_QUALIFIER) @Nullable codecConfiguration: CodecConfiguration?
) : JacksonMediaTypeCodec(
    xmlMapper, applicationConfiguration, codecConfiguration,
    MediaType.of("application/xhtml+xml")
) {
    override fun cloneWithFeatures(jacksonFeatures: JacksonFeatures): JacksonMediaTypeCodec {
        val objectMapper = objectMapper.copy()
        jacksonFeatures.deserializationFeatures.forEach { (f: DeserializationFeature?, state: Boolean?) ->
            objectMapper.configure(
                f,
                state!!
            )
        }
        jacksonFeatures.serializationFeatures.forEach { (f: SerializationFeature?, state: Boolean?) ->
            objectMapper.configure(
                f,
                state!!
            )
        }
        return XmlMediaTypeCodec(objectMapper, applicationConfiguration, codecConfiguration)
    }

    companion object {
        const val CONFIGURATION_QUALIFIER = "xml"
    }
}