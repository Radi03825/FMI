main :: IO()
main = do
    print (factorial 7)
    print (factorialWithIf 7)
    print (factorialWithPM 7)
    print (factorialWithOtherFunction 7)



-- Factorial with Guards
factorial :: Integer -> Integer
factorial x 
    | x == 0 || x == 1 = 1
    | x > 1 = x * factorial (x - 1)
    | otherwise = error "Number should be positive"

-- Factorial with if
factorialWithIf :: Integer -> Integer
factorialWithIf n = if (n == 0 || n == 1) then 1 else if (n > 1) then n * factorialWithIf (n - 1) else error "Number should be positive"

-- Factorial with Pattern Matching
factorialWithPM :: Integer -> Integer
factorialWithPM 0 = 1
factorialWithPM n = n * factorialWithPM (n - 1)

-- Factorial with inner funcion
factorialWithInnerFunction :: Integer -> Integer
factorialWithInnerFunction n = factInner n 1 1
    where
        factInner ::Integer -> Integer -> Integer -> Integer
        factInner n count product
            | count > n = product
            | otherwise = factInner n (count + 1) (count * product)