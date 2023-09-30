
● Will return a descending sorted list of all the cryptos, comparing the normalized range (i.e. (max-min)/min)
http://localhost:8080/api/recommendations/cryptos?sort_by=normalized_range.desc

● Will return the oldest/newest/min/max values for a requested crypto
http://localhost:8080/api/recommendations/cryptos/{crypto_symbol_here}/values?filter=min
{crypto_symbol_here} could be BTC, DOGE, LTC, ETH, XRP

● Will return the crypto with the highest normalized range for a specified day
http://localhost:8080/api/recommendations/crypto?field=normalized_range:max&date=2023-10-21