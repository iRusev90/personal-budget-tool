package bg.infa.pbt.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.stereotype.Service;

@Service
public class AppConversionService {
	@Autowired
	private GenericConversionService genericConversionService;
	@Autowired
	private ApplicationContext applicationContext;

	@SuppressWarnings("rawtypes")
	@PostConstruct
	private void init() {
		Map<String, Converter> converters = applicationContext.getBeansOfType(Converter.class);
		for (Entry<String, Converter> entry : converters.entrySet()) {
			genericConversionService.addConverter(entry.getValue());
		}
	}

	public <FROM_SOURCE, TO_TYPE> TO_TYPE convert(FROM_SOURCE from, Class<TO_TYPE> toType) {
		return genericConversionService.convert(from, toType);
	}

	public <T, K> List<T> convertList(List<K> items, Class<T> toType) {
		List<T> toList = new ArrayList<>();
		convertCollection(items, toList, toType);
		return toList;
	}

	public <T, K> Set<T> convertSet(Set<K> items, Class<T> toType) {
		Set<T> toSet = new LinkedHashSet<>();
		convertCollection(items, toSet, toType);
		return toSet;
	}

	private <T, K> void convertCollection(Collection<K> items, Collection<T> toItems, Class<T> toType) {
		for (K item : items) {
			T convertedItem = convert(item, toType);
			toItems.add(convertedItem);
		}
	}
}