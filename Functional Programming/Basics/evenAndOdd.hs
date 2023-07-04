main :: IO()
main = do
    print (isEven 8)
    print (isEvenWithIf 8)
    print (isEvenWithGuards 8)
    print (isOdd 7)
    print (isOddWithIf 7)
    print (isOddWithGuards 7)


    
isEven :: Integer -> Bool
isEven n = n `mod` 2 == 0

isOdd :: Integer -> Bool
isOdd x = x `mod` 2 /= 0

-- IsEven with If
isEvenWithIf :: Integer -> Bool
isEvenWithIf n = if (mod n 2 == 0) then True else False

-- IsEven with Guards
isEvenWithGuards :: Integer -> Bool
isEvenWithGuards n 
    | n `mod` 2 == 0 = True
    | otherwise = False

-- IsEven with If
isOddWithIf :: Integer -> Bool
isOddWithIf n = if (mod n 2 /= 0) then True else False

-- IsEven with Guards
isOddWithGuards :: Integer -> Bool
isOddWithGuards n 
    | n `mod` 2 /= 0 = True
    | otherwise = False