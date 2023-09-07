main :: IO()
main = do
    print (sumOddLC 5 50)
    print (sumOddHOF 5 50)
    print (sumOddLC 7 77)
    print (sumOddHOF 7 77)



sumOddLC :: Int -> Int -> Int
sumOddLC start end = sum [ x | x <- [min start end .. max start end], odd x]

sumOddHOF :: Int -> Int -> Int
sumOddHOF start end = sum (filter odd [min start end .. max start end])
