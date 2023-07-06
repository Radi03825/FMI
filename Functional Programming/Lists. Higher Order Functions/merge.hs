main :: IO()
main = do
    print (merge [1,3,7,9] [2,4,5,6,10])



merge :: (Ord a) => [a] -> [a] -> [a]
merge [] bs = bs
merge as [] = as
merge as@(a:ta) bs@(b:tb) =
  if (a < b) then a:merge ta bs
  else b:merge as tb
