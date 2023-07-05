main :: IO()
main = do
    --We apply 2 * x first to 2, then to (2 * 2) and so on. 
    --Thus, we get: (((((2 * 2) * 2) * 2) * 2) * 2) => ((((4 * 2) * 2) * 2) * 2) => (((8 * 2) * 2) * 2) => ((16 * 2) * 2) => (32 * 2) => 64
    print ((applyN (\x -> 2 * x) 5) 2 == 64)
    print ((applyN (\x -> div x 10) 2) 100 == 1)



applyN :: (Eq a) => (Num a) => (a -> a) -> a -> (a -> a)
applyN _ 0 = (\ x -> x)
applyN f n = (\ x -> (applyN f (n - 1) (f x)))
