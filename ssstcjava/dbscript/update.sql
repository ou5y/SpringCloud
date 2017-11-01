ALTER TABLE `ssstc`.`market_shopping`
  ADD INDEX `create_time_index` (`create_time` ASC);

ALTER TABLE `ssstc`.`market_customer`
  ADD INDEX `create_time_index` (`create_time` ASC);