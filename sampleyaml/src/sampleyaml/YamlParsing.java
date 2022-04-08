package sampleyaml;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;
import java.util.ListIterator;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;

public class YamlParsing {

	private static final long serialVersionUID = 1L;

	public static byte[] convertObjectToByte(Object obj) {
		byte[] resultByte = new byte[] {};
		try (ByteArrayOutputStream out = new ByteArrayOutputStream();
				ObjectOutputStream os = new ObjectOutputStream(out)) {

			os.writeObject(obj);
			resultByte = out.toByteArray();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return resultByte;
	}

	public static void main(String[] args) throws Exception {

		try {
			File file = new File("/home/inc10/Desktop/book1.yaml");

			YAMLFactory yaml = new YAMLFactory();

			YAMLParser yamlParser = yaml.createParser(file);

			ObjectMapper objectMapper = new ObjectMapper(yaml);

			List<BookData> docs = objectMapper.readValues(yamlParser, new TypeReference<BookData>() {}).readAll();
			ListIterator<BookData> iter = docs.listIterator();

			while (iter.hasNext()) {

				BookData config = objectMapper.readValue(YamlParsing.convertObjectToByte(iter.next()), BookData.class);

				System.out.println("Application config info " + config.toString());
				System.out.println("List values:" + docs);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
}
