modules=(domain infrastructure presentation usecase)

for v in "${modules[@]}"
do
  pushd $v
  ../gradlew dependencies --write-locks
  popd
done

