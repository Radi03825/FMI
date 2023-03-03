main :: IO()
main = do
    print (isLeapYear 2020)
    print (isLeapYearWithGuards 2020)

isLeapYear :: Integer -> Bool
isLeapYear y = (y `mod` 400 == 0) || (y `mod` 4 == 0 && y `mod` 100 /= 0)

isLeapYearWithGuards :: Integer -> Bool
isLeapYearWithGuards y 
    | mod y 400 == 0 = True
    | mod y 100 == 0 = False
    | mod y 4 == 0 = True
    | otherwise = False