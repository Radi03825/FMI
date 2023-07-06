import Data.List

main :: IO()
main = do
    print (isAscending 0 == True)
    print (isAscending 12 == True)
    print (isAscending 321 == False)
    print (isAscending 132 == False)
    print (isAscending 1234567 == True)



isAscending :: Int -> Bool
isAscending num = (sort (show num)) == (show num)
