main :: IO()
main = do
    print (power 2 5)
    print (powerIter 3 5)
    print (powerWithGuards 4 2)



power :: Double -> Integer -> Double
power _ 0 = 1
power x 1 = x
power x n  
    | n > 0 = x * power x (n - 1)
    | otherwise = 1.0 / power x (-n) 

powerIter :: Double -> Integer -> Double
powerIter _ 0 = 1
powerIter x n = helper n x
    where
        helper 1 result = result
        helper n result = helper (n - 1) (result * x)

powerWithGuards :: Double -> Integer -> Double
powerWithGuards x y
    | y == 0 = 1
    | y > 0 = x * powerWithGuards x (y - 1)
    | otherwise = 1.0 / powerWithGuards x (-y)