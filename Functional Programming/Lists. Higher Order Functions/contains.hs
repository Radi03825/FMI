main :: IO()
main = do
    print (contains 3 [1, 2, 3] == True)
    print (contains 3 [2.5, 3.2, 7.7] == False)
    print (contains 5 [5..15] == True)



contains :: (Eq a) => a -> [a] -> Bool
contains _ [] = False
contains n (x:xs) = n == x || contains n xs
