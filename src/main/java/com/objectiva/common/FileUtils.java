package com.objectiva.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(FileUtils.class);
	private static final String CONFIG_PROPERTIES = "config.properties";
	// FIXME all the following configurations can be configured in database if
	// possible
	private static final String XML_PATH = "xml_path";
	private static final String XML_PATH_DEFAULT = "src/main/java/EmpProfiles.xml";
	private static final String FIXED_SALARY = "fixed_salary";
	private static final String FIXED_SALARY_DEFAULT = "6000";
	private static final String ONE_HOUR_SALARY = "one_hour_salary";
	private static final String ONE_HOUR_SALARY_DEFAULT = "35";
	private static final String LEVEL_ONE_COMMISSION_RATE = "level_one_commission_rate";
	private static final String LEVEL_ONE_COMMISSION_RATE_DEFAULT = "0.05";
	private static final String LEVEL_TWO_COMMISSION_RATE = "level_two_commission_rate";
	private static final String LEVEL_TWO_COMMISSION_RATE_DEFAULT = "0.08";
	private static final String SALE_BASIC_SALARY = "sale_basic_salary";
	private static final String SALE_BASIC_SALARY_DEFAULT = "3000";

	private FileUtils() {
		// prevent construction
	}

	public static String getXMLPath() {
		String filePath = getParamsFromProperties(XML_PATH, XML_PATH_DEFAULT);
		return StringUtils.isBlank(filePath) ? XML_PATH_DEFAULT : filePath;
	}

	public static String getFixedSalary() {
		String fixedSalary = getParamsFromProperties(FIXED_SALARY, FIXED_SALARY_DEFAULT);
		return StringUtils.isBlank(fixedSalary) ? FIXED_SALARY_DEFAULT : fixedSalary;
	}
	
	public static String getOneHourSalary() {
		String oneHourSalary = getParamsFromProperties(ONE_HOUR_SALARY, ONE_HOUR_SALARY_DEFAULT);
		return StringUtils.isBlank(oneHourSalary) ? ONE_HOUR_SALARY_DEFAULT : oneHourSalary;
	}
	
	public static String getLevelOneCommissionRate() {
		String levelOneCommissionRate = getParamsFromProperties(LEVEL_ONE_COMMISSION_RATE, LEVEL_ONE_COMMISSION_RATE_DEFAULT);
		return StringUtils.isBlank(levelOneCommissionRate) ? LEVEL_ONE_COMMISSION_RATE_DEFAULT : levelOneCommissionRate;
	}
	
	public static String getLevelTwoCommissionRate() {
		String levelTwoCommissionRate = getParamsFromProperties(LEVEL_TWO_COMMISSION_RATE, LEVEL_TWO_COMMISSION_RATE_DEFAULT);
		return StringUtils.isBlank(levelTwoCommissionRate) ? LEVEL_TWO_COMMISSION_RATE_DEFAULT : levelTwoCommissionRate;
	}
	
	public static String getSaleBasicSalary() {
		String saleBasicSalary = getParamsFromProperties(SALE_BASIC_SALARY, SALE_BASIC_SALARY_DEFAULT);
		return StringUtils.isBlank(saleBasicSalary) ? SALE_BASIC_SALARY_DEFAULT : saleBasicSalary;
	}

	private static String getParamsFromProperties(String pKey, String pDefVal) {
		String resultVal = pDefVal;
		InputStream inputStream = FileUtils.class.getClassLoader().getResourceAsStream(CONFIG_PROPERTIES);
		Properties p = new Properties();
		try {
			p.load(inputStream);
			resultVal = p.getProperty(pKey, pDefVal);
		} catch (IOException e) {
			LOGGER.warn("Failed to load {}.", CONFIG_PROPERTIES, e);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					LOGGER.warn("Exception occurs when closing stream.");
				}
			}
		}
		return resultVal;
	}

}
